/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softech.students.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author TAI
 */
@Entity
@Table(name = "Students")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "StudentsEntity.findAll", query = "SELECT s FROM StudentsEntity s"),
    @NamedQuery(name = "StudentsEntity.findByUsername", query = "SELECT s FROM StudentsEntity s WHERE s.username = :username"),
    @NamedQuery(name = "StudentsEntity.findByName", query = "SELECT s FROM StudentsEntity s WHERE s.name = :name")})
public class StudentsEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "username")
    private String username;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "name")
    private String name;

    public StudentsEntity() {
    }

    public StudentsEntity(String username) {
        this.username = username;
    }

    public StudentsEntity(String username, String name) {
        this.username = username;
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (username != null ? username.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof StudentsEntity)) {
            return false;
        }
        StudentsEntity other = (StudentsEntity) object;
        if ((this.username == null && other.username != null) || (this.username != null && !this.username.equals(other.username))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.softech.students.entities.StudentsEntity[ username=" + username + " ]";
    }
    
}
