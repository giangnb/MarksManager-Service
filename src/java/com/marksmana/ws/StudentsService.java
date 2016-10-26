/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.marksmana.ws;

import com.marksmana.entities.Student;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;

/**
 * For managing Student
 * @author Giang
 */
@WebService(serviceName = "StudentsService")
public class StudentsService {

    @WebMethod(operationName = "getStudent")
    public List<Student> getStudents() {
        EntityManager em = Persistence.createEntityManagerFactory("MarksManager-ServicePU").createEntityManager();
        return em.createNamedQuery("Student.findAll").getResultList();
    }

    @WebMethod(operationName = "getStudentById")
    public Student getStudentById(@WebParam(name = "id") int id) {
        EntityManager em = Persistence.createEntityManagerFactory("MarksManager-ServicePU").createEntityManager();
        return em.find(Student.class, id);
    }

    @WebMethod(operationName = "getStudentsByName")
    public List<Student> getStudentsByName(@WebParam(name = "name") String name) {
        EntityManager em = Persistence.createEntityManagerFactory("MarksManager-ServicePU").createEntityManager();
        return em.createNamedQuery("Student.findByName")
                .setParameter("name", "%" + name.replace(" ", "%") + "%")
                .getResultList();
    }

    @WebMethod(operationName = "getStudentsByClass")
    public List<Student> getStudentsByClass(@WebParam(name = "classId") int id) {
        EntityManager em = Persistence.createEntityManagerFactory("MarksManager-ServicePU").createEntityManager();
        return em.createNamedQuery("Student.findByClass")
                .setParameter("classId", id).getResultList();
    }

    @WebMethod(operationName = "addStudent")
    public int addStudent(@WebParam(name = "student")Student s) {
        EntityManager em = Persistence.createEntityManagerFactory("MarksManager-ServicePU").createEntityManager();
        if (s.getId() != null) {
            if (em.find(Student.class, s.getId()) != null) {
                return 0;
            }
        }
        em.getTransaction().begin();
        try {
            em.persist(s);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            return 0;
        }
        return 1;
    }

    @WebMethod(operationName = "updateStudent")
    public int updateStudent(@WebParam(name = "student")Student s) {
        EntityManager em = Persistence.createEntityManagerFactory("MarksManager-ServicePU").createEntityManager();
        if (s.getId() == null) {
            return 0;
        }
        Student stu = em.find(Student.class, s.getId());
        if (stu == null) {
            return 0;
        }
        em.getTransaction().begin();
        try {
            stu.setClassId(s.getClassId());
            stu.setInfo(s.getInfo());
            stu.setName(s.getName());
            stu.setScoreList(s.getScoreList());
            stu.setScoreLogList(s.getScoreLogList());
            
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            return 0;
        }
        return 1;
    }
    
    @WebMethod(operationName = "removeStudent")
    public int removeStudent(@WebParam(name = "id")int id) {
        EntityManager em = Persistence.createEntityManagerFactory("MarksManager-ServicePU").createEntityManager();
        Student stu = em.find(Student.class, id);
        if (stu == null) {
            return 0;
        }
        em.getTransaction().begin();
        try {
            em.detach(stu);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            return 0;
        }
        return 1;
    }

}
