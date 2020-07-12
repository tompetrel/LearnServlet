/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webapp.model;

import java.io.Serializable;

/**
 *
 * @author TAI
 */
public class User implements Serializable {

    private int username;
    private String password, name;
    private boolean gender;

    public User() {
    }

    public User(int username, String password, String name, boolean gender) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.gender = gender;
    }

    public int getUsername() {
        return username;
    }

    public void setUsername(int username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

}
