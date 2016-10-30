/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.marksmana.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Giang
 */
@Entity
@Table(name = "t_scorelog")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ScoreLog.findAll", query = "SELECT s FROM ScoreLog s"),
    @NamedQuery(name = "ScoreLog.findById", query = "SELECT s FROM ScoreLog s WHERE s.id = :id"),
    @NamedQuery(name = "ScoreLog.findByScores", query = "SELECT s FROM ScoreLog s WHERE s.scores = :scores"),
    @NamedQuery(name = "ScoreLog.findByRemarks", query = "SELECT s FROM ScoreLog s WHERE s.remarks = :remarks"),
    @NamedQuery(name = "ScoreLog.findBySchoolYear", query = "SELECT s FROM ScoreLog s WHERE s.schoolYear = :schoolYear")})
public class ScoreLog implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Basic(optional = false)
    private Long id;
    @Basic(optional = false)
    private String scores;
    @Basic(optional = false)
    private String remarks;
    @Basic(optional = false)
    @Column(name = "school_year")
    private String schoolYear;
    @JoinColumn(name = "studentId", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Student studentId;

    public ScoreLog() {
    }

    public ScoreLog(Long id) {
        this.id = id;
    }

    public ScoreLog(Long id, String scores, String remarks, String schoolYear) {
        this.id = id;
        this.scores = scores;
        this.remarks = remarks;
        this.schoolYear = schoolYear;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getScores() {
        return scores;
    }

    public void setScores(String scores) {
        this.scores = scores;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getSchoolYear() {
        return schoolYear;
    }

    public void setSchoolYear(String schoolYear) {
        this.schoolYear = schoolYear;
    }

    public Student getStudentId() {
        return studentId;
    }

    public void setStudentId(Student studentId) {
        this.studentId = studentId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ScoreLog)) {
            return false;
        }
        ScoreLog other = (ScoreLog) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.marksmana.entities.ScoreLog[ id=" + id + " ]";
    }
    
}
