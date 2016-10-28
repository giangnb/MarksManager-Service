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
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Giang
 */
@Entity
@Table(name = "t_admin")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Admin.findAll", query = "SELECT a FROM Admin a"),
    @NamedQuery(name = "Admin.findById", query = "SELECT a FROM Admin a WHERE a.id = :id"),
    @NamedQuery(name = "Admin.findByPass", query = "SELECT a FROM Admin a WHERE a.pass = :pass"),
    @NamedQuery(name = "Admin.findByLastLogin", query = "SELECT a FROM Admin a WHERE a.lastLogin = :lastLogin"),
    @NamedQuery(name = "Admin.findByLastChange", query = "SELECT a FROM Admin a WHERE a.lastChange = :lastChange")})
public class Admin implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    private String id;
    @Basic(optional = false)
    private String pass;
    @Basic(optional = false)
    @Lob
    private String prohibited;
    @Basic(optional = false)
    @Column(name = "last_login")
    private long lastLogin;
    @Basic(optional = false)
    @Column(name = "last_change")
    private long lastChange;

    public Admin() {
    }

    public Admin(String id) {
        this.id = id;
    }

    public Admin(String id, String pass, String prohibited, long lastLogin, long lastChange) {
        this.id = id;
        this.pass = pass;
        this.prohibited = prohibited;
        this.lastLogin = lastLogin;
        this.lastChange = lastChange;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getProhibited() {
        return prohibited;
    }

    public void setProhibited(String prohibited) {
        this.prohibited = prohibited;
    }

    public long getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(long lastLogin) {
        this.lastLogin = lastLogin;
    }

    public long getLastChange() {
        return lastChange;
    }

    public void setLastChange(long lastChange) {
        this.lastChange = lastChange;
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
        if (!(object instanceof Admin)) {
            return false;
        }
        Admin other = (Admin) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.marksmana.entities.Admin[ id=" + id + " ]";
    }
    
}
