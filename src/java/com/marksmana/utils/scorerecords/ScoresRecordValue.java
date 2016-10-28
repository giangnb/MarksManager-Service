/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.marksmana.utils.scorerecords;

import com.marksmana.utils.Json;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.List;
import java.util.Set;

/**
 *
 * @author Giang
 */
public class ScoresRecordValue {
    public ScoresBySubject[] scores;

    public ScoresRecordValue() {
    }

    public void setScores(ScoresRecord record) {
        scores = record.toArray(new ScoresBySubject[record.size()]);
    }
    
    public String addScoresList(List<com.marksmana.entities.Score> list) throws Exception {
        ArrayList<Score> scoreList = new ArrayList<>();
        for (com.marksmana.entities.Score s : list) {
//            System.out.println(s.getSubjectId().getName());
            scoreList.add(new Score(s.getScore(), s.getCoefficient(), s.getTeacherId().getName(), s.getSubjectId().getName()));
        }
        Hashtable<String, Integer> tbl = new Hashtable<>();
        for (Score s : scoreList) {
            tbl.put(s.getSubject(), 0);
        }
        Set<String> set = tbl.keySet();
        String[] str = set.toArray(new String[set.size()]);
        ScoresBySubject[] sbs = new ScoresBySubject[tbl.size()];
        for (int i = 0; i < tbl.size(); i++) {
            tbl.put(str[i], i);
            sbs[i] = new ScoresBySubject(str[i]);
//            System.out.println(str[i] + " - " + tbl.get(str[i]));
        }
        for (Score s : scoreList) {
            int i = tbl.get(s.getSubject());
            sbs[i].add(s);
        }
        ScoresRecord rec = new ScoresRecord();
        for (ScoresBySubject s : sbs) {
            rec.add(s);
        }
        this.setScores(rec);
        return toJson();
    }
    
    public String toJson() throws Exception {
        return Json.SerializeObject(this);
    }
}
