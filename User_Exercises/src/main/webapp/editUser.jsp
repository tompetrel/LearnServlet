<%-- 
    Document   : editUser
    Created on : Jul 14, 2020, 12:06:22 AM
    Author     : TAI
--%>

<%@page import="com.webapp.model.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
            User user = (User) request.getAttribute("user");
        %>
        <h1>Edit User!</h1>
        <form action="EditUser" method="post">
            Username : <input type="text" name="username" value="<%= user.getUsername()%>"><br><br>
            Password : <input type="text" name="password" value="<%= user.getPassword()%>"><br><br>
            Name : <input type="text" name="name" value="<%= user.getName()%>"><br><br>
            Gender : <br>
            Male :
            <input type="radio" name="gender" value="<%= user.isGender()%>"
                   <%= user.isGender() ? "checked" : ""%>>
            Female :
            <input type="radio" name="gender" value="<%= !user.isGender()%>"
                   <%= !user.isGender() ? "checked" : ""%>>
            <input type="submit" value="Update">
        </form>
    </body>
</html>
