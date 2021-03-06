/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.marksmana.entities;

import java.io.Serializable;
import javax.persistence.Basic;
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
@Table(name = "t_score")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Score.findAll", query = "SELECT s FROM Score s"),
    @NamedQuery(name = "Score.findById", query = "SELECT s FROM Score s WHERE s.id = :id"),
    @NamedQuery(name = "Score.findByStudent", query = "SELECT s FROM Score s WHERE s.studentId = :studentId"),
    @NamedQuery(name = "Score.findBySubject", query = "SELECT s FROM Score s WHERE s.subjectId = :subjectId"),
    @NamedQuery(name = "Score.findBySubjectAndClass", query = "SELECT s FROM Score s WHERE s.subjectId = :subjectId AND s.studentId.classId = :classId"),
    @NamedQuery(name = "Score.findByStudentAndSubject", query = "SELECT s FROM Score s WHERE s.studentId = :studentId AND s.subjectId = :subjectId"),
    @NamedQuery(name = "Score.findByScore", query = "SELECT s FROM Score s WHERE s.score = :score"),
    @NamedQuery(name = "Score.findByCoefficient", query = "SELECT s FROM Score s WHERE s.coefficient = :coefficient")})
public class Score implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Basic(optional = false)
    private Long id;
    @Basic(optional = false)
    private double score;
    @Basic(optional = false)
    private short coefficient=1;
    @JoinColumn(name = "studentId", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Student studentId;
    @JoinColumn(name = "subjectId", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Subject subjectId;
    @JoinColumn(name = "teacherId", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Teacher teacherId;

    public Score() {
    }

    public Score(Long id) {
        this.id = id;
    }

    public Score(Long id, double score, short coefficient) {
        this.id = id;
        this.score = score;
        this.coefficient = coefficient;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public short getCoefficient() {
        return coefficient;
    }

    public void setCoefficient(short coefficient) {
        this.coefficient = coefficient;
    }

    public Student getStudentId() {
        return studentId;
    }

    public void setStudentId(Student studentId) {
        this.studentId = studentId;
    }

    public Subject getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Subject subjectId) {
        this.subjectId = subjectId;
    }

    public Teacher getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Teacher teacherId) {
        this.teacherId = teacherId;
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
        if (!(object instanceof Score)) {
            return false;
        }
        Score other = (Score) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.marksmana.entities.Score[ id=" + id + " ]";
    }
    
}
