<%-- 
    Document   : StudentsController
    Created on : Jul 25, 2020, 4:01:18 PM
    Author     : TAI
--%>

<%@page import="java.util.List"%>
<%@page import="com.softech.student.entities.StudentsEntity"%>
<%@page import="com.softech.student.controller.StudentsEntityJpaController"%>
<%@page import="javax.persistence.Persistence"%>
<%@page import="javax.persistence.EntityManagerFactory"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Students</title>
    </head>
    <body>
        <h1>Welcome to Students Controller!</h1>
        <hr>

        <table border="2px">
            <tr>
                <td>Student Id</td>
                <td>Name</td>
                <td>Gender</td>
                <td>Phone</td>
                <td>Email</td>
                <td>Address</td>
                <td>&nbsp;</td>
            </tr>
            <%
                EntityManagerFactory emf = Persistence.createEntityManagerFactory("com.softech_student-hibernate_war_1.0-SNAPSHOTPU");
                StudentsEntityJpaController controller = new StudentsEntityJpaController(emf);
                List<StudentsEntity> list = controller.findStudentsEntityEntities();
                for (StudentsEntity studentsEntity : list) {
                    request.setAttribute("students", studentsEntity);

            %>
            <tr>
                <td>${students.studentId}</td>
                <td>${students.name}</td>
                <%if (studentsEntity.getGender()) {
                %>
                    <td>Male</td>
                <%} else {%>
                    <td>Female</td>           
                <%}%>
                <td>${students.phone}</td>
                <td>${students.email}</td>
                <td>${students.address}</td>
                <td><a href="NewStudent.jsp">Add</a> | <a href="EditStudent.jsp">Edit</a> | <a href="DeleteStudent.jsp">Delete</a></td>
            </tr>
            <%}%>

        </table>
    </body>
</html>
