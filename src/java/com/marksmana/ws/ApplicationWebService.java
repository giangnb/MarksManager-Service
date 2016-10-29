/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.marksmana.ws;

import com.marksmana.entities.Admin;
import com.marksmana.entities.Bulk;
import com.marksmana.entities.Clazz;
import com.marksmana.entities.Properties;
import com.marksmana.entities.Score;
import com.marksmana.entities.ScoreLog;
import com.marksmana.entities.Student;
import com.marksmana.entities.Subject;
import com.marksmana.entities.Teacher;
import com.marksmana.info.Information;
import com.marksmana.utils.Encrypt;
import com.marksmana.utils.Json;
import com.marksmana.utils.scorerecords.ScoresRecordValue;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

/**
 *
 * @author Giang
 */
@WebService(serviceName = "ApplicationWebService")
public class ApplicationWebService {

    // <editor-fold defaultstate="collapsed" desc="Student Management"> 
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
            em.remove(stu);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            return 0;
        }
        return 1;
    }
    // </editor-fold>
    
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
        Teacher t = em.find(Teacher.class, id);
        return t.getSubjectList();
    }

    @WebMethod(operationName = "getSubjectsByBulk")
    public List<Subject> getSubjectsByBulk(@WebParam(name = "bulkId") int id) {
        EntityManager em = Persistence.createEntityManagerFactory("MarksManager-ServicePU").createEntityManager();
        Bulk b = em.find(Bulk.class, id);
        return b.getSubjectList();
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
            em.remove(sub);
            
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
                    em.remove(score);
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
                    em.remove(sc);
                }
                
                trans.commit();
            } catch (Exception ex) {
                trans.rollback();
            }
        }
    }
    // </editor-fold>
    
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
            em.remove(cla);
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
            em.remove(bu);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            return 0;
        }
        return 1;
    }
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Admin Account"> 
    /**
     * Authenticate Administrator's account
     * @param user Administrator login id 
     * @param pass Password (raw)
     * @return Admin object
     */
    @WebMethod(operationName = "adminLogin")
    public Admin adminLogin(@WebParam(name = "user") String user, @WebParam(name = "rawPass") String pass) {
        EntityManager em = Persistence.createEntityManagerFactory("MarksManager-ServicePU").createEntityManager();
        pass=Encrypt.hash(pass);
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
                em.remove(a);
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
            em.remove(p);
            em.getTransaction().commit();
        } catch (Exception ex) {
            em.getTransaction().rollback();
            return 0;
        }
        return 1;
    }
    // </editor-fold>
    
}
