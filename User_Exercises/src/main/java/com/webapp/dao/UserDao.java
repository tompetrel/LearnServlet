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
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

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

    public List<User> getAllUser() throws Exception {
        List<User> list = new ArrayList<>();
        String sql = "select * from [User]";

        try (
                 Connection con = DatabaseHelper.openConnection();  Statement stmt = con.createStatement();  ResultSet rs = stmt.executeQuery(sql);) {
            while (rs.next()) {
                User user = new User(rs.getInt("Username"), rs.getString("Password"), rs.getString("Name"), rs.getBoolean("Gender"));
                list.add(user);
            }
        }
        return list;
    }

    public User findByID(int username) throws Exception {
        String sql = "select * from [User] where Username = ?";

        try (
                 Connection con = DatabaseHelper.openConnection();  PreparedStatement ps = con.prepareStatement(sql);) {

            ps.setInt(1, username);
            try ( ResultSet rs = ps.executeQuery();) {
                if (rs.next()) {
                    User user = new User(rs.getInt("Username"), rs.getString("Password"), rs.getString("Name"), rs.getBoolean("Gender"));
                    return user;
                }
            }
        }
        return null;
    }

    public boolean updateUser(User user) throws Exception {
        String sql = "update [User] set Password = ?, Name = ?, Gender = ? where Username = ?";

        try (
                 Connection con = DatabaseHelper.openConnection();  PreparedStatement ps = con.prepareStatement(sql);) {

            ps.setString(1, user.getPassword());
            ps.setString(2, user.getName());
            ps.setBoolean(3, user.isGender());
            ps.setInt(4, user.getUsername());
            return ps.executeUpdate() > 0;
        }
    }

}
