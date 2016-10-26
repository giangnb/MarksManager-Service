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
import javax.persistence.Column;
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
@Table(name = "t_bulk")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Bulk.findAll", query = "SELECT b FROM Bulk b"),
    @NamedQuery(name = "Bulk.findById", query = "SELECT b FROM Bulk b WHERE b.id = :id"),
    @NamedQuery(name = "Bulk.findByName", query = "SELECT b FROM Bulk b WHERE b.name = :name"),
    @NamedQuery(name = "Bulk.findByInfo", query = "SELECT b FROM Bulk b WHERE b.info = :info")})
public class Bulk implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "name")
    private String name;
    @Basic(optional = false)
    @Column(name = "info")
    private String info;
    @JoinTable(name = "t_bulk_subject", joinColumns = {
        @JoinColumn(name = "bulkId", referencedColumnName = "id")}, inverseJoinColumns = {
        @JoinColumn(name = "subjectId", referencedColumnName = "id")})
    @ManyToMany
    private List<Subject> subjectList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "bulkId")
    private List<Clazz> clazzList;

    public Bulk() {
    }

    public Bulk(Integer id) {
        this.id = id;
    }

    public Bulk(Integer id, String name, String info) {
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
    public List<Subject> getSubjectList() {
        return subjectList;
    }

    public void setSubjectList(List<Subject> subjectList) {
        this.subjectList = subjectList;
    }

    @XmlTransient
    public List<Clazz> getClazzList() {
        return clazzList;
    }

    public void setClazzList(List<Clazz> clazzList) {
        this.clazzList = clazzList;
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
        if (!(object instanceof Bulk)) {
            return false;
        }
        Bulk other = (Bulk) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.marksmana.entities.Bulk[ id=" + id + " ]";
    }
    
}
