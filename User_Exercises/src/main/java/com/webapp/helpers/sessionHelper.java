/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webapp.helpers;

import com.webapp.model.User;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author TAI
 */
public class sessionHelper {

    public static boolean checkUserLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User usr = (User) session.getAttribute("user");
        if (usr == null) {
            RequestDispatcher rd = request.getRequestDispatcher("LoginController");
            rd.forward(request, response);
            return true;
        }
        return false;
    }
}
