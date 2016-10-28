/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.marksmana.utils.scorerecords;

import java.io.Serializable;

/**
 *
 * @author Giang
 */
class Score implements Serializable{
    private double score;
    private int coefficient;
    private String teacher;
    private String subject;

    public Score(double score, int coefficient, String teacher, String subject) {
        this.score = score;
        this.coefficient = coefficient;
        this.teacher = teacher;
        this.subject = subject;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public int getCoefficient() {
        return coefficient;
    }

    public void setCoefficient(int coefficient) {
        this.coefficient = coefficient;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }
    
}
