/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.marksmana.ws;

import com.marksmana.entities.Admin;
import com.marksmana.entities.Properties;
import java.util.Date;
import java.util.List;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;

/**
 *
 * @author Giang
 */
//@WebService(serviceName = "SystemService")
public class SystemService {
    
    // <editor-fold defaultstate="collapsed" desc="Admin Account"> 
    /**
     * Authenticate Administrator's account
     * @param user Administrator login id 
     * @param pass Password
     * @return Admin object
     */
    @WebMethod(operationName = "adminLogin")
    public Admin adminLogin(@WebParam(name = "user") String user, @WebParam(name = "pass") String pass) {
        EntityManager em = Persistence.createEntityManagerFactory("MarksManager-ServicePU").createEntityManager();
        Admin acc = em.find(Admin.class, user);
        em.getTransaction().begin();
        try {
            acc.setLastLogin(new Date().getTime());
            em.getTransaction().commit();
        } catch (Exception ex) {
            em.getTransaction().rollback();
        }
        if (acc.getPass().equals(pass)) {
            return acc;
        }
        return null;
    }

    /**
     * Get administrator account list
     * @param acc Current logged in account
     * @return Admin object list | null if account doesn't have permission
     */
    @WebMethod(operationName = "getAdmins")
    public List<Admin> getAdmins(@WebParam(name = "currentAccount")Admin acc) {
        EntityManager em = Persistence.createEntityManagerFactory("MarksManager-ServicePU").createEntityManager();
        if (em.find(Admin.class, acc.getId()) == null || acc.getProhibited().contains("account")) {
            return null;
        }
        return em.createNamedQuery("Admin.findAll").getResultList();
    }

    /**
     * Add new administrator account
     * @param acc Account object
     * @return 1: success | 0: fail
     */
    @WebMethod(operationName = "addAdmin")
    public int addAdmin(@WebParam(name = "adminAccount") Admin acc) {
        EntityManager em = Persistence.createEntityManagerFactory("MarksManager-ServicePU").createEntityManager();
        Admin a = em.find(Admin.class, acc.getId());
        if (a != null) {
            return 0;
        }
        em.getTransaction().begin();
        try {
            em.persist(acc);
            em.getTransaction().commit();
        } catch (Exception ex) {
            em.getTransaction().rollback();
            return 0;
        }
        return 1;
    }

    /**
     * Edit an administrator account
     * @param acc Account object
     * @return 1: success | 0: fail
     */
    @WebMethod(operationName = "updateAdmin")
    public int updateAdmin(@WebParam(name = "adminAccount") Admin acc) {
        EntityManager em = Persistence.createEntityManagerFactory("MarksManager-ServicePU").createEntityManager();
        Admin a = em.find(Admin.class, acc.getId());
        if (a == null) {
            return 0;
        }
        em.getTransaction().begin();
        try {
            a.setLastChange(new Date().getTime());
            a.setPass(acc.getPass());
            a.setProhibited(acc.getProhibited());

            em.getTransaction().commit();
        } catch (Exception ex) {
            em.getTransaction().rollback();
            return 0;
        }
        return 1;
    }

    /**
     * Delete an administrator account
     * @param id
     * @return 1: success | 0: fail
     */
    @WebMethod(operationName = "removeAdmin")
    public int removeAdmin(@WebParam(name = "adminAccountId") String id) {
        EntityManager em = Persistence.createEntityManagerFactory("MarksManager-ServicePU").createEntityManager();
        Admin a = em.find(Admin.class, id);
        if (a == null) {
            return 0;
        }
        List<Admin> list = em.createNamedQuery("Admin.findAll").getResultList();
        // Find an account that's not selected account and have full priviledge (no prohibition)
        boolean isAdminLeft = false;
        for (Admin acc : list) {
            if (!acc.getId().equals(id)) {
                if (acc.getProhibited().length() < 1) {
                    isAdminLeft = true;
                    break;
                }
            }
        }
        // Only remove account if there's another admin account
        if (isAdminLeft) {
            em.getTransaction().begin();
            try {
                em.detach(a);
                em.getTransaction().commit();
            } catch (Exception ex) {
                em.getTransaction().rollback();
                return 0;
            }
            return 1;
        }
        return 0;
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="System Properties"> 
    @WebMethod(operationName = "getProperties")
    public List<Properties> getProperties() {
        EntityManager em = Persistence.createEntityManagerFactory("MarksManager-ServicePU").createEntityManager();
        return em.createNamedQuery("Properties.findAll").getResultList();
    }
    
    @WebMethod(operationName = "getPropertyByKey")
    public Properties getPropertyByKey(@WebParam(name = "propertyKey")String k) {
        EntityManager em = Persistence.createEntityManagerFactory("MarksManager-ServicePU").createEntityManager();
        return em.find(Properties.class, k);
    }
    
    @WebMethod(operationName = "getPropertyValueByKey")
    public String getPropertyValueByKey(@WebParam(name = "propertyKey")String k) {
        EntityManager em = Persistence.createEntityManagerFactory("MarksManager-ServicePU").createEntityManager();
        return em.find(Properties.class, k).getValue();
    }
    
    @WebMethod(operationName = "addProperty")
    public int addProperty(@WebParam(name = "poperty")Properties p) {
        EntityManager em = Persistence.createEntityManagerFactory("MarksManager-ServicePU").createEntityManager();
        Properties prop = em.find(Properties.class, p.getKey());
        if (prop!=null || p.getKey()==null || p.getKey().equals("")) {
            return 0;
        }
        em.getTransaction().begin();
        try {
            em.persist(p);
            em.getTransaction().commit();
        } catch (Exception ex) {
            em.getTransaction().rollback();
            return 0;
        }
        return 1;
    }
    
    @WebMethod(operationName = "updateProperty")
    public int updateProperty(@WebParam(name = "poperty")Properties p) {
        EntityManager em = Persistence.createEntityManagerFactory("MarksManager-ServicePU").createEntityManager();
        Properties prop = em.find(Properties.class, p.getKey());
        if (prop==null) {
            return 0;
        }
        em.getTransaction().begin();
        try {
            prop.setValue(p.getValue());
            em.getTransaction().commit();
        } catch (Exception ex) {
            em.getTransaction().rollback();
            return 0;
        }
        return 1;
    }
    
    @WebMethod(operationName = "removeProperty")
    public int removeProperty(@WebParam(name = "popertyKey")String k) {
        EntityManager em = Persistence.createEntityManagerFactory("MarksManager-ServicePU").createEntityManager();
        Properties p = em.find(Properties.class, k);
        if (p==null) {
            return 0;
        }
        em.getTransaction().begin();
        try {
            em.detach(p);
            em.getTransaction().commit();
        } catch (Exception ex) {
            em.getTransaction().rollback();
            return 0;
        }
        return 1;
    }
    // </editor-fold>
}
