/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webapp.controller;

import com.webapp.dao.UserDao;
import com.webapp.helpers.sessionHelper;
import com.webapp.model.User;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author TAI
 */
@WebServlet(name = "EditUser", urlPatterns = {"/EditUser"})
public class EditUser extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if(sessionHelper.checkUserLogin(request, response))return;
        try {
            String username = request.getParameter("username");
            UserDao dao = new UserDao();
            User user = dao.findByID(Integer.parseInt(username));
            if (user == null) {
                RequestDispatcher rd = request.getRequestDispatcher("ListUser");
                rd.forward(request, response);
            } else {
                request.setAttribute("user", user);
                RequestDispatcher rd = request.getRequestDispatcher("editUser.jsp");
                rd.forward(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if(sessionHelper.checkUserLogin(request, response))return;
        try {
            UserDao dao = new UserDao();
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            String name = request.getParameter("name");
            String gender = request.getParameter("gender");

            User user = new User(Integer.parseInt(username), password, name, Boolean.parseBoolean(gender));
            dao.updateUser(user);
            RequestDispatcher rd = request.getRequestDispatcher("ListUser");
            rd.forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
