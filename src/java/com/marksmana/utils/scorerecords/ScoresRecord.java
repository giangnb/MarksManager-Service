/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.marksmana.utils.scorerecords;

import com.marksmana.entities.Subject;
import com.marksmana.entities.Teacher;
import com.marksmana.utils.Json;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Giang
 */
class ScoresRecord extends ArrayList<ScoresBySubject>{
    public ScoresRecord() {
    }
    
    public void add(String subject) {
        this.add(new ScoresBySubject(subject));
    }
    
    public void calculateAvgScore() {
        for (ScoresBySubject s : this) {
            s.getAvgScore();
        }
    }
    
    public String toJson() throws Exception {
        return Json.SerializeObject(this);
    }
    
//    public static void main(String[] args) {
//        ScoresRecordValue val = new ScoresRecordValue();
//        ArrayList<com.marksmana.entities.Score> list = new ArrayList<>();
//        com.marksmana.entities.Score s1 = new com.marksmana.entities.Score();
//        com.marksmana.entities.Score s2 = new com.marksmana.entities.Score();
//        com.marksmana.entities.Score s3 = new com.marksmana.entities.Score();
//        com.marksmana.entities.Score s4 = new com.marksmana.entities.Score();
//        Teacher t = new Teacher();
//        t.setName("jhfd");
//        Subject sj = new Subject();
//        sj.setName("s1");
//        s1.setScore(7.8d);
//        s1.setCoefficient(Short.parseShort("1"));
//        s1.setSubjectId(sj);
//        s1.setTeacherId(t);
//        list.add(s1);
//        s2.setScore(8.9d);
//        s2.setCoefficient(Short.parseShort("2"));
//        s2.setSubjectId(sj);
//        s2.setTeacherId(t);
//        list.add(s2);
//        Subject sj2 = new Subject();
//        sj2.setName("s2");
//        s3.setScore(6.3d);
//        s3.setCoefficient(Short.parseShort("1"));
//        s3.setSubjectId(sj2);
//        s3.setTeacherId(t);
//        list.add(s3);
//        s4.setScore(9.9d);
//        s4.setCoefficient(Short.parseShort("2"));
//        s4.setSubjectId(sj2);
//        s4.setTeacherId(t);
//        list.add(s4);
//        try {
//            System.out.println(val.addScoresList(list));
//        } catch (Exception ex) {
//            Logger.getLogger(ScoresRecord.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
}
