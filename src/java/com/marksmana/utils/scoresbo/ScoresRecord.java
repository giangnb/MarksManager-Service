/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.marksmana.utils.scoresbo;

import com.marksmana.entities.Score;
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
public class ScoresRecord {

    public String clazz;
    public String schoolYear="";
    public String remarks="";
    public ScoresGroup[] scores;
    public double grossAvg=0;
    private ArrayList<ScoresGroup> list;

    public ScoresRecord() {
        list = new ArrayList<ScoresGroup>();
        scores = new ScoresGroup[1];
    }

    public ScoresRecord(List<Score> s) {
        for (Score score : s) {
            addScore(score);
        }
    }

    private void preare() {
        for (ScoresGroup sg : list) {
            sg.calculateAvg();
        }
        scores = list.toArray(new ScoresGroup[list.size()]);
        double sum=0, count=0;
        for (ScoresGroup sc : scores) {
            count++;
            sum+=sc.avg;
        }
        grossAvg = sum/count;
    }
    
    public void addScore(Score s) {
        addScore(s.getScore(), s.getCoefficient(), s.getTeacherId().getName(), s.getSubjectId().getName());
    }

    public void addScore(double score, int coeff, String teacher, String subject) {
        boolean found = false;
        while (!found) {
            for (ScoresGroup sg : list) {
                if (sg.subject.equals(subject)) {
                    sg.addScore(score, coeff, teacher);
                    found = true;
                    break;
                }
            }
            if (!found) {
                list.add(new ScoresGroup(subject));
            }
        }
    }

    public String toJson() {
        preare();
        String s = "{}";

        try {
            s = Json.SerializeObject(this);
        } catch (Exception ex) {
            Logger.getLogger(ScoresRecord.class.getName()).log(Level.SEVERE, null, ex);
        }

        return s;
    }

//    public static void main(String[] args) {
//        try {
//            ScoresRecord r = new ScoresRecord();
//            Teacher t = new Teacher();
//            t.setName("jsdfh TTT");
//            Subject subj = new Subject(1, "111hgsd", "iii");
//            Subject subj2 = new Subject(1, "222hgsd", "iii");
//            Score s = new Score();
//            s.setTeacherId(t);
//            s.setCoefficient(Short.parseShort("1"));
//            s.setScore(7.8);
//            s.setSubjectId(subj);
//            r.addScore(s.getScore(), s.getCoefficient(), s.getTeacherId().getName(), s.getSubjectId().getName());
//            s.setScore(8.7);
//            s.setSubjectId(subj2);
//            r.addScore(s.getScore(), s.getCoefficient(), s.getTeacherId().getName(), s.getSubjectId().getName());
//            System.out.println(r.toJson());
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//    }

}
