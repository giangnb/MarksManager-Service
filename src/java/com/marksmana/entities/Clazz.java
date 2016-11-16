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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "t_class")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Clazz.findAll", query = "SELECT c FROM Clazz c"),
    @NamedQuery(name = "Clazz.findById", query = "SELECT c FROM Clazz c WHERE c.id = :id"),
    @NamedQuery(name = "Clazz.findByName", query = "SELECT c FROM Clazz c WHERE c.name = :name"),
    @NamedQuery(name = "Clazz.findByTeacher", query = "SELECT c FROM Clazz c WHERE c.teacherId = :teacherId"),
    @NamedQuery(name = "Clazz.findByInfo", query = "SELECT c FROM Clazz c WHERE c.info = :info")})
public class Clazz implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Basic(optional = false)
    private Integer id;
    @Basic(optional = false)
    private String name;
    @Basic(optional = false)
    private String info="";
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "classId")
    private List<Student> studentList;
    @JoinColumn(name = "bulkId", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Bulk bulkId;
    @JoinColumn(name = "teacherId", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Teacher teacherId;

    public Clazz() {
    }

    public Clazz(Integer id) {
        this.id = id;
    }

    public Clazz(Integer id, String name, String info) {
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
    public List<Student> getStudentList() {
        return studentList;
    }

    public void setStudentList(List<Student> studentList) {
        this.studentList = studentList;
    }

    public Bulk getBulkId() {
        return bulkId;
    }

    public void setBulkId(Bulk bulkId) {
        this.bulkId = bulkId;
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
        if (!(object instanceof Clazz)) {
            return false;
        }
        Clazz other = (Clazz) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.marksmana.entities.Clazz[ id=" + id + " ]";
    }
    
}
