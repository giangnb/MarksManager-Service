/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.marksmana.ws;

import com.marksmana.entities.Bulk;
import com.marksmana.entities.Subject;
import com.marksmana.entities.Teacher;
import com.marksmana.info.Information;
import com.marksmana.utils.Json;
import java.util.Date;
import java.util.List;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;

/**
 * For managing Teachers and Subjects
 *
 * @author Giang
 */
//@WebService(serviceName = "SchoolService")
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

    // <editor-fold defaultstate="collapsed" desc="Subjects Managing"> 
    @WebMethod(operationName = "getSubjects")
    public List<Subject> getSubjects() {
        EntityManager em = Persistence.createEntityManagerFactory("MarksManager-ServicePU").createEntityManager();
        return em.createNamedQuery("Subject.findAll").getResultList();
    }

    @WebMethod(operationName = "getSubjectById")
    public Subject getSubjectById(@WebParam(name = "id") int id) {
        EntityManager em = Persistence.createEntityManagerFactory("MarksManager-ServicePU").createEntityManager();
        return em.find(Subject.class, id);
    }

    @WebMethod(operationName = "getSubjectsByTeacher")
    public List<Subject> getSubjectsByTeacher(@WebParam(name = "teacherId") int id) {
        EntityManager em = Persistence.createEntityManagerFactory("MarksManager-ServicePU").createEntityManager();
        return em.createNamedQuery("Subject.findByTeacher")
                .setParameter("teacherId", id).getResultList();
    }

    @WebMethod(operationName = "getSubjectsByBulk")
    public List<Subject> getSubjectsByBulk(@WebParam(name = "bulkId") int id) {
        EntityManager em = Persistence.createEntityManagerFactory("MarksManager-ServicePU").createEntityManager();
        return em.createNamedQuery("Subject.findByBulk")
                .setParameter("bulkId", id).getResultList();
    }

    @WebMethod(operationName = "addSubject")
    public int addSubject(@WebParam(name = "subject") Subject s) {
        EntityManager em = Persistence.createEntityManagerFactory("MarksManager-ServicePU").createEntityManager();
        if (s.getId() != null) {
            if (em.find(Subject.class, s.getId()) != null) {
                return 0;
            }
            s.setId(null);
        }
        em.getTransaction().begin();
        try {
            em.persist(s);
            em.getTransaction().commit();
        } catch (Exception ex) {
            em.getTransaction().rollback();
            return 0;
        }
        return 1;
    }

    @WebMethod(operationName = "updateSubject")
    public int updateSubject(@WebParam(name = "subject") Subject s) {
        EntityManager em = Persistence.createEntityManagerFactory("MarksManager-ServicePU").createEntityManager();
        if (s.getId() == null) {
            return 0;
        }
        Subject sub = em.find(Subject.class, s.getId());
        if (sub == null) {
            return 0;
        }
        em.getTransaction().begin();
        try {
            sub.setBulkList(s.getBulkList());
            sub.setInfo(s.getInfo());
            sub.setName(s.getName());
            sub.setScoreList(s.getScoreList());
            sub.setTeacherList(s.getTeacherList());
            
            em.getTransaction().commit();
        } catch (Exception ex) {
            em.getTransaction().rollback();
            return 0;
        }
        return 1;
    }
    
    @WebMethod(operationName = "removeSubject")
    public int removeSubject(@WebParam(name = "subjectId") int id) {
        EntityManager em = Persistence.createEntityManagerFactory("MarksManager-ServicePU").createEntityManager();
        Subject sub = em.find(Subject.class, id);
        if (sub == null) {
            return 0;
        }
        em.getTransaction().begin();
        try {
            sub.getScoreList().clear();
            sub.getTeacherList().clear();
            em.detach(sub);
            
            em.getTransaction().commit();
        } catch (Exception ex) {
            em.getTransaction().rollback();
            return 0;
        }
        return 1;
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Teachers Login"> 
    /**
     * Authenticate teacher's account
     * @param user Teacher's username
     * @param pass Account password
     * @return Teacher account ID
     */
    @WebMethod(operationName = "teacherLogin")
    public int teacherLogin(@WebParam(name = "userId") String user, @WebParam(name = "password") String pass) {
        EntityManager em = Persistence.createEntityManagerFactory("MarksManager-ServicePU").createEntityManager();
        List<Teacher> results = em.createNamedQuery("Teacher.login")
                .setParameter("pass", pass)
                .setParameter("user", user).getResultList();
        if (results.size() > 0) {
            int id = results.get(0).getId();

            Teacher t = em.find(Teacher.class, id);
            em.getTransaction().begin();
            try {
                Information info;
                try {
                    info = Json.DeserializeObject(t.getInfo(), Information.class);
                } catch (Exception ex) {
                    info = new Information();
                }
                info.put("LastLogin", new Date().getTime() + "");
                t.setInfo(Json.SerializeObject(info));
                em.getTransaction().commit();
            } catch (Exception ex) {
                em.getTransaction().rollback();
            }

            return id;
        }
        return -1;
    }
    // </editor-fold>
    
}
