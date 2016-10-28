/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.marksmana.ws;

import com.marksmana.entities.Properties;
import com.marksmana.entities.Score;
import com.marksmana.entities.ScoreLog;
import com.marksmana.entities.Student;
import com.marksmana.utils.Json;
import com.marksmana.utils.scorerecords.ScoresRecordValue;
import java.util.ArrayList;
import java.util.List;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

/**
 * For managing Score and ScoreLog
 * @author Giang
 */
//@WebService(serviceName = "ScoresService")
public class ScoresService {

    // <editor-fold defaultstate="collapsed" desc="Scores Managing"> 
    @WebMethod(operationName = "getScoresBySubject")
    public List<Score> getScoresBySubject(@WebParam(name = "subjectId") int id) {
        EntityManager em = Persistence.createEntityManagerFactory("MarksManager-ServicePU").createEntityManager();
        return em.createNamedQuery("Score.findBySubject")
                .setParameter("subjectId", id).getResultList();
    }

    @WebMethod(operationName = "getScoresByClass")
    public List<Score> getScoresByClass(@WebParam(name = "classId") int id) {
        EntityManager em = Persistence.createEntityManagerFactory("MarksManager-ServicePU").createEntityManager();
        return em.createNamedQuery("Score.findByClass")
                .setParameter("classId", id).getResultList();
    }

    @WebMethod(operationName = "addScores")
    public Score[] addScores(@WebParam(name = "scores") Score[] s) {
        EntityManager em = Persistence.createEntityManagerFactory("MarksManager-ServicePU").createEntityManager();
        EntityTransaction trans = em.getTransaction();
        ArrayList<Score> errors = new ArrayList<Score>();
        for (Score score : s) {
            trans.begin();
            try {
                em.persist(score);
                trans.commit();
            } catch (Exception ex) {
                errors.add(score);
                trans.rollback();
            }
        }
        return errors.toArray(new Score[errors.size()]);
    }

    @WebMethod(operationName = "updateScores")
    public Score[] updateScores(@WebParam(name = "scores") Score[] s) {
        EntityManager em = Persistence.createEntityManagerFactory("MarksManager-ServicePU").createEntityManager();
        EntityTransaction trans = em.getTransaction();
        ArrayList<Score> errors = new ArrayList<Score>();
        for (Score score : s) {
            trans.begin();
            try {
                em.persist(score);
                trans.commit();
            } catch (Exception ex) {
                errors.add(score);
                trans.rollback();
            }
        }
        return errors.toArray(new Score[errors.size()]);
    }

    @WebMethod(operationName = "removeScores")
    public Score[] removeScores(@WebParam(name = "ids") int[] ids) {
        EntityManager em = Persistence.createEntityManagerFactory("MarksManager-ServicePU").createEntityManager();
        EntityTransaction trans = em.getTransaction();
        ArrayList<Score> errors = new ArrayList<Score>();
        for (int id : ids) {
            Score score = em.find(Score.class, id);
            if (score != null) {
                trans.begin();
                try {
                    em.detach(score);
                    trans.commit();
                } catch (Exception ex) {
                    errors.add(score);
                    trans.rollback();
                }
            }
        }
        return errors.toArray(new Score[errors.size()]);
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Scores Archiving"> 
    @WebMethod(operationName = "archiveToLog")
    public void archiveToLog(@WebParam(name = "classId") int id) {
        EntityManager em = Persistence.createEntityManagerFactory("MarksManager-ServicePU").createEntityManager();
        List<Student> list = em.createNamedQuery("Student.findByClass")
                .setParameter("classId", id).getResultList();
        EntityTransaction trans = em.getTransaction();
        for (Student s : list) {
            ScoresRecordValue recordVal = null;
            
            trans.begin();
            try {
                // Add to ScoreLog
                //Old:String json = Json.SerializeObject(s);
                recordVal = new ScoresRecordValue();
                String json = recordVal.addScoresList(s.getScoreList());
                ScoreLog log = new ScoreLog();
                log.setSchoolYear(em.find(Properties.class, "school_year").getValue());
                log.setScores(json);
                log.setStudentId(s);
                em.persist(log);
                // Remove Scores
                List<Score> score = em.createNamedQuery("Score.findByStudent")
                        .setParameter("studentId", s.getId()).getResultList();
                for (Score sc : score) {
                    em.detach(sc);
                }
                
                trans.commit();
            } catch (Exception ex) {
                trans.rollback();
            }
        }
    }
    // </editor-fold>

}
