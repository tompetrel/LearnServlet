<%-- 
    Document   : NewStudent
    Created on : Jul 25, 2020, 4:34:21 PM
    Author     : TAI
--%>

<%@page import="com.softech.student.controller.StudentsEntityJpaController"%>
<%@page import="javax.persistence.Persistence"%>
<%@page import="javax.persistence.EntityManagerFactory"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>New Student</title>
    </head>
    <body>
        <h1>New Student!</h1>
        <hr>
        <a href="StudentsController.jsp">Show Students</a>
        <form method="post">
            Student Id : <input type="text" value="Auto" disabled><br><br>
            Name : <input type="text" name="name"><br><br>
            Gender : <input type="radio" name="gender" value="true">Male
            <input type="radio" name="gender" value="false">Female<br><br>
            Phone : <input type="text" name="phone"><br><br>
            Email : <input type="text" name="email"><br><br>
            Address : <input type="text" name="address"><br><br>

            <input type="submit" value="ADD">
            <input type="reset" value="Reset">

        </form>
        <%
            if (request.getMethod().equals("POST")) {
        %> 
        <jsp:useBean id="entity" class="com.softech.student.entities.StudentsEntity" scope="page"/>
        <jsp:setProperty name="entity" property="*"/>
        <%
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("com.softech_student-hibernate_war_1.0-SNAPSHOTPU");
            StudentsEntityJpaController controller = new StudentsEntityJpaController(emf);
            controller.create(entity);
            out.println("<h3>Student Saved</h3>");
        %>
        <%}%>

    </body>
</html>
