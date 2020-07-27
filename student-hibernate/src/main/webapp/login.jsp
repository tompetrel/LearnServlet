<%-- 
    Document   : login
    Created on : Jul 21, 2020, 5:11:09 PM
    Author     : TAI
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Login to System</h1>
        <hr>
        <form action="LoginController" method="post">
            Username :
            <input type="text" name="username"><br>
            Password :
            <input type="password" name="password"><br>
            <input type="submit" value="Login">
            <input type="reset" value="Reset">
        </form>
        <%
            String message = (String) request.getAttribute("message");
            if (message != null) {
        %>
        <h3><%= message%></h3>
        <%}%>
    </body>
</html>
