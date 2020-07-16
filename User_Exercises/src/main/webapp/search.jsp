<%-- 
    Document   : search
    Created on : Jul 14, 2020, 11:17:53 AM
    Author     : TAI
--%>

<%@page import="java.util.List"%>
<%@page import="com.webapp.model.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Serch User</h1>
        <form action="SearchController">
            Name : <input type="text" name="name">
            <input type="submit" value="Search">

        </form>
        <hr>
        <table>
            <tr>
                <td>Username</td>
                <td>Name</td>
                <td>Password</td>
                <td>&nbsp;</td>
            </tr>
            <%
                List<User> list = (List<User>) request.getAttribute("user");
                if (list != null) {
                    for (User user : list) {


            %>
            <tr>
                <td><%= user.getUsername()%></td>
                <td><%= user.getName()%></td>
                <td><%= user.getPassword()%></td>
                <td>
                    <a href="">Edit</a>
                    <a href="">Delete</a>
                </td>

            </tr>

            <%                    }
                }
            %>
        </table>

    </body>
</html>
