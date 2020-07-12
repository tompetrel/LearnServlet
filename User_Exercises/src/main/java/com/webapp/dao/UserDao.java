/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webapp.dao;

import com.webapp.helpers.DatabaseHelper;
import com.webapp.model.User;
import java.sql.Connection;
import java.sql.PreparedStatement;

/**
 *
 * @author TAI
 */
public class UserDao {

    public boolean insertUser(User user) throws Exception {
        String sql = "insert into [User](Password, Name, Gender) values (?,?,?)";

        try (
                 Connection con = DatabaseHelper.openConnection();  PreparedStatement ps = con.prepareStatement(sql);) {

            ps.setString(1, user.getPassword());
            ps.setString(2, user.getName());
            ps.setBoolean(3, user.isGender());

            return ps.executeUpdate() > 0;
        }
    }
}
