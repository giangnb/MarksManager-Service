/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.marksmana.ws;

import com.marksmana.entities.Bulk;
import com.marksmana.entities.Clazz;
import java.util.List;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;

/**
 * For managing Classes and Bulk
 * @author Giang
 */
//@WebService(serviceName = "ClassesService")
public class ClassesService {

    // <editor-fold defaultstate="collapsed" desc="Classes Managing"> 
    @WebMethod(operationName = "getClasses")
    public List<Clazz> getClasses() {
        EntityManager em = Persistence.createEntityManagerFactory("MarksManager-ServicePU").createEntityManager();
        return em.createNamedQuery("Clazz.findAll").getResultList();
    }
    
    @WebMethod(operationName = "getClassById")
    public Clazz getClassById(@WebParam(name = "id")int id) {
        EntityManager em = Persistence.createEntityManagerFactory("MarksManager-ServicePU").createEntityManager();
        return em.find(Clazz.class, id);
    }
    
    @WebMethod(operationName = "addClass")
    public int addClass(@WebParam(name = "class")Clazz c) {
        EntityManager em = Persistence.createEntityManagerFactory("MarksManager-ServicePU").createEntityManager();
        if (c.getId() != null) {
            if (em.find(Clazz.class, c.getId()) != null) {
                return 0;
            }
        }
        em.getTransaction().begin();
        try {
            em.persist(c);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            return 0;
        }
        return 1;
    }
    
    @WebMethod(operationName = "updateClass")
    public int updateClass(@WebParam(name = "class")Clazz c) {
        EntityManager em = Persistence.createEntityManagerFactory("MarksManager-ServicePU").createEntityManager();
        if (c.getId() == null) {
            return 0;
        }
        Clazz cla = em.find(Clazz.class, c.getId());
        if (cla == null) {
            return 0;
        }
        em.getTransaction().begin();
        try {
            cla.setBulkId(c.getBulkId());
            cla.setInfo(c.getInfo());
            cla.setName(c.getName());
            cla.setStudentList(c.getStudentList());
            cla.setTeacherId(c.getTeacherId());
            
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            return 0;
        }
        return 1;
    }
    
    @WebMethod(operationName = "removeClass")
    public int removeClass(@WebParam(name = "id")int id) {
        EntityManager em = Persistence.createEntityManagerFactory("MarksManager-ServicePU").createEntityManager();
        Clazz cla = em.find(Clazz.class, id);
        if (cla == null) {
            return 0;
        }
        em.getTransaction().begin();
        try {
            cla.getStudentList().clear();
            em.detach(cla);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            return 0;
        }
        return 1;
    }
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Bulks Managing"> 
    @WebMethod(operationName = "getBulks")
    public List<Bulk> getBulks() {
        EntityManager em = Persistence.createEntityManagerFactory("MarksManager-ServicePU").createEntityManager();
        return em.createNamedQuery("Bulk.findAll").getResultList();
    }
    
    @WebMethod(operationName = "getBulkById")
    public Bulk getBulkById(@WebParam(name = "id")int id) {
        EntityManager em = Persistence.createEntityManagerFactory("MarksManager-ServicePU").createEntityManager();
        return em.find(Bulk.class, id);
    }
    
    @WebMethod(operationName = "addBulk")
    public int addBulk(@WebParam(name = "bulk")Bulk b) {
        EntityManager em = Persistence.createEntityManagerFactory("MarksManager-ServicePU").createEntityManager();
        if (b.getId() != null) {
            if (em.find(Bulk.class, b.getId()) != null) {
                return 0;
            }
        }
        em.getTransaction().begin();
        try {
            em.persist(b);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            return 0;
        }
        return 1;
    }
    
    @WebMethod(operationName = "updateBulk")
    public int updateBulk(@WebParam(name = "bulk")Bulk b) {
        EntityManager em = Persistence.createEntityManagerFactory("MarksManager-ServicePU").createEntityManager();
        if (b.getId() == null) {
            return 0;
        }
        Bulk bu = em.find(Bulk.class, b.getId());
        if (bu == null) {
            return 0;
        }
        em.getTransaction().begin();
        try {
            bu.setClazzList(b.getClazzList());
            bu.setInfo(b.getInfo());
            bu.setName(b.getName());
            bu.setSubjectList(b.getSubjectList());
            
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            return 0;
        }
        return 1;
    }
    
    @WebMethod(operationName = "removeBulk")
    public int removeBulk(@WebParam(name = "id")int id) {
        EntityManager em = Persistence.createEntityManagerFactory("MarksManager-ServicePU").createEntityManager();
        Bulk bu = em.find(Bulk.class, id);
        if (bu == null) {
            return 0;
        }
        em.getTransaction().begin();
        try {
            bu.getClazzList().clear();
            bu.getSubjectList().clear();
            em.detach(bu);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            return 0;
        }
        return 1;
    }
    // </editor-fold>
    
}
