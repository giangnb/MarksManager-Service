/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.marksmana.utils.scoresbo;

import java.util.Comparator;

/**
 *
 * @author Giang
 */
class SingleScore implements Comparable<SingleScore>, Comparator<SingleScore>{
    public double score=0d;
    public int coeff=1;
    public String teacher;

    public SingleScore(double score, int coeff, String teacher) {
        this.score = score;
        this.coeff = coeff;
        this.teacher = teacher;
    }

    public SingleScore() {
    }

    @Override
    public int compareTo(SingleScore o) {
        return coeff - o.coeff;
    }

    @Override
    public int compare(SingleScore o1, SingleScore o2) {
        if (o1.score<o2.score)
            return -1;
        else if (o1.score>o2.score)
            return 1;
        else return 0;
    }
}
