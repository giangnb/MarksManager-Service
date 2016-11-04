/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.marksmana.api;

import com.marksmana.entities.Properties;
import com.marksmana.entities.Score;
import com.marksmana.entities.ScoreLog;
import com.marksmana.entities.Student;
import com.marksmana.utils.Json;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author Giang
 */
@Path("viewer")
public class GenericResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of GenericResource
     */
    public GenericResource() {
    }

    /**
     * Retrieves representation of an instance of com.web.api.GenericResource
     *
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getProperties() {
        try {
            EntityManager em = Persistence.createEntityManagerFactory("MarksManager-ServicePU").createEntityManager();
            String str = Json.SerializeObject(em.createNamedQuery("Properties.findAll").getResultList());
            return str;
        } catch (Exception ex) {
            // ignore
        }
        return "[]";
    }
    @GET
    @Path("/prop/{key}")
    @Produces(MediaType.APPLICATION_JSON)
    public Properties getPropertyByKey(@PathParam("key") int key) {
        Properties p=null;
        try {
            EntityManager em = Persistence.createEntityManagerFactory("MarksManager-ServicePU").createEntityManager();
            p = em.find(Properties.class, key);
        } catch (Exception ex) {
            // ignore
        }
        return p;
    }

    @GET
    @Path("/student/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Student getStudent(@PathParam("id") int id) {
        EntityManager em = Persistence.createEntityManagerFactory("MarksManager-ServicePU").createEntityManager();
        Student s = em.find(Student.class, id);
        return s!=null?s:null;
    }

    @GET
    @Path("/student/{id}/scores")
    @Produces(MediaType.APPLICATION_JSON)
    public Score[] getScores(@PathParam("id") int id) {
        EntityManager em = Persistence.createEntityManagerFactory("MarksManager-ServicePU").createEntityManager();
        Student s = em.find(Student.class, id);
        if (s==null) return null;
        List<Score> scores = s.getScoreList();
        return scores.toArray(new Score[scores.size()]);
    }
    

    @GET
    @Path("/student/{id}/logs")
    @Produces(MediaType.APPLICATION_JSON)
    public ScoreLog[] getLogs(@PathParam("id") int id) {
        EntityManager em = Persistence.createEntityManagerFactory("MarksManager-ServicePU").createEntityManager();
        Student s = em.find(Student.class, id);
        if (s==null) return null;
        List<ScoreLog> logs = em.find(Student.class, id).getScoreLogList();
        return logs.toArray(new ScoreLog[logs.size()]);
    }

    /**
     * PUT method for updating or creating an instance of GenericResource
     *
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void putJson(String content) {
    }

}
