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
    @NamedQuery(name = "AdminAccount.findAll", query = "SELECT a FROM AdminAccount a"),
    @NamedQuery(name = "AdminAccount.findById", query = "SELECT a FROM AdminAccount a WHERE a.id = :id"),
    @NamedQuery(name = "AdminAccount.findByPass", query = "SELECT a FROM AdminAccount a WHERE a.pass = :pass"),
    @NamedQuery(name = "AdminAccount.findByLastLogin", query = "SELECT a FROM AdminAccount a WHERE a.lastLogin = :lastLogin"),
    @NamedQuery(name = "AdminAccount.findByLastChange", query = "SELECT a FROM AdminAccount a WHERE a.lastChange = :lastChange")})
public class AdminAccount implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id")
    private String id;
    @Basic(optional = false)
    @Column(name = "pass")
    private String pass;
    @Basic(optional = false)
    @Lob
    @Column(name = "prohibited")
    private String prohibited;
    @Basic(optional = false)
    @Column(name = "last_login")
    private long lastLogin;
    @Basic(optional = false)
    @Column(name = "last_change")
    private long lastChange;

    public AdminAccount() {
    }

    public AdminAccount(String id) {
        this.id = id;
    }

    public AdminAccount(String id, String pass, String prohibited, long lastLogin, long lastChange) {
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
        if (!(object instanceof AdminAccount)) {
            return false;
        }
        AdminAccount other = (AdminAccount) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.marksmana.entities.AdminAccount[ id=" + id + " ]";
    }
    
}
