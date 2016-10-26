/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.marksmana.ws;

import com.marksmana.entities.Subject;
import com.marksmana.entities.Teacher;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * For managing Teachers and Subjects
 *
 * @author Giang
 */
@WebService(serviceName = "SchoolService")
public class SchoolService {

    // <editor-fold defaultstate="collapsed" desc="Teachers Managing"> 
    @WebMethod(operationName = "getTeachers")
    public List<Teacher> getTeachers() {
        EntityManager em = Persistence.createEntityManagerFactory("MarksManager-ServicePU").createEntityManager();
        return em.createNamedQuery("Teacher.findAll").getResultList();
    }

    @WebMethod(operationName = "getTeacherById")
    public Teacher getTeacherById(@WebParam(name = "id") int id) {
        EntityManager em = Persistence.createEntityManagerFactory("MarksManager-ServicePU").createEntityManager();
        return em.find(Teacher.class, id);
    }

    @WebMethod(operationName = "addTeacher")
    public int addTeacher(@WebParam(name = "teacher") Teacher t) {
        EntityManager em = Persistence.createEntityManagerFactory("MarksManager-ServicePU").createEntityManager();
        if (t.getId() != null) {
            if (em.find(Teacher.class, t.getId()) != null) {
                return 0;
            }
        }
        em.getTransaction().begin();
        try {
            em.persist(t);
            em.getTransaction().commit();
        } catch (Exception ex) {
            em.getTransaction().rollback();
            return 0;
        }
        return 1;
    }

    @WebMethod(operationName = "updateTeacher")
    public int updateTeacher(@WebParam(name = "teacher") Teacher t) {
        EntityManager em = Persistence.createEntityManagerFactory("MarksManager-ServicePU").createEntityManager();
        if (t.getId() == null) {
            return 0;
        }
        Teacher teacher = em.find(Teacher.class, t.getId());
        if (teacher == null) {
            return 0;
        }
        em.getTransaction().begin();
        try {            
            teacher.setClazzList(t.getClazzList());
            teacher.setInfo(t.getInfo());
            teacher.setName(t.getName());
            teacher.setPass(t.getPass());
            teacher.setScoreList(t.getScoreList());
            teacher.setSubjectList(t.getSubjectList());
            teacher.setUsername(t.getUsername());
            
            em.getTransaction().commit();
        } catch (Exception ex) {
            em.getTransaction().rollback();
            return 0;
        }
        return 1;
    }
    // </editor-fold>   

    @WebMethod(operationName = "getSubjectsByTeacher")
    public List<Subject> getSubjectsByTeacher(@WebParam(name = "teacherId") int id) {
        EntityManager em = Persistence.createEntityManagerFactory("MarksManager-ServicePU").createEntityManager();
        return em.createNamedQuery("Subject.findByTacher")
                .setParameter("teacherId", id).getResultList();
    }

}
