/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.marksmana.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Giang
 */
@Entity
@Table(name = "t_subject")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Subject.findAll", query = "SELECT s FROM Subject s"),
    @NamedQuery(name = "Subject.findById", query = "SELECT s FROM Subject s WHERE s.id = :id"),
    //@NamedQuery(name = "Subject.findByTeacher", query = "SELECT s FROM Subject s WHERE s.teacherList.id = :teacherId"),
    //@NamedQuery(name = "Subject.findByBulk", query = "SELECT s FROM Subject s WHERE s.bulkList.id = :bulkId"),
    @NamedQuery(name = "Subject.findByName", query = "SELECT s FROM Subject s WHERE s.name = :name"),
    @NamedQuery(name = "Subject.findByInfo", query = "SELECT s FROM Subject s WHERE s.info = :info")})
public class Subject implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    private Integer id;
    @Basic(optional = false)
    private String name;
    @Basic(optional = false)
    private String info;
    @JoinTable(name = "t_teacher_subject", joinColumns = {
        @JoinColumn(name = "subjectId", referencedColumnName = "id")}, inverseJoinColumns = {
        @JoinColumn(name = "teacherId", referencedColumnName = "id")})
    @ManyToMany
    private List<Teacher> teacherList;
    @ManyToMany(mappedBy = "subjectList")
    private List<Bulk> bulkList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "subjectId")
    private List<Score> scoreList;

    public Subject() {
    }

    public Subject(Integer id) {
        this.id = id;
    }

    public Subject(Integer id, String name, String info) {
        this.id = id;
        this.name = name;
        this.info = info;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    @XmlTransient
    public List<Teacher> getTeacherList() {
        return teacherList;
    }

    public void setTeacherList(List<Teacher> teacherList) {
        this.teacherList = teacherList;
    }

    @XmlTransient
    public List<Bulk> getBulkList() {
        return bulkList;
    }

    public void setBulkList(List<Bulk> bulkList) {
        this.bulkList = bulkList;
    }

    @XmlTransient
    public List<Score> getScoreList() {
        return scoreList;
    }

    public void setScoreList(List<Score> scoreList) {
        this.scoreList = scoreList;
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
        if (!(object instanceof Subject)) {
            return false;
        }
        Subject other = (Subject) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.marksmana.entities.Subject[ id=" + id + " ]";
    }
    
}
