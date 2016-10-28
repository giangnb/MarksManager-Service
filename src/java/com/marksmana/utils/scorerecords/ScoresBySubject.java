/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.marksmana.utils.scorerecords;

import java.util.ArrayList;

/**
 *
 * @author Giang
 */
class ScoresBySubject extends ArrayList<Score>{
    private String subject;
    private double avgScore;

    public ScoresBySubject(String subject) {
        this.subject = subject;
        this.avgScore = 0;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public double getAvgScore() {
        calculate();
        return avgScore;
    }
    
    public void add(double score, int coefficient, String teacher) {
        this.add(new Score(score, coefficient, teacher, subject));
    }
    
    protected void calculate() {
//        double sum = 0;
//        int count = 0;
//        for (Score s : this) {
//            sum+=s.getScore();
//            count+=s.getCoefficient();
//        }
//        if (count<=0) {
//            avgScore = 0;
//        } else {
//            avgScore = (double)((double)sum/(double)count);
//        }
    }
}
