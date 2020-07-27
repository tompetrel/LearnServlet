<%-- 
    Document   : DeleteStudent
    Created on : Jul 25, 2020, 4:34:47 PM
    Author     : TAI
--%>

<%@page import="com.softech.student.entities.StudentsEntity"%>
<%@page import="com.softech.student.controller.StudentsEntityJpaController"%>
<%@page import="javax.persistence.Persistence"%>
<%@page import="javax.persistence.EntityManagerFactory"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Delete Student!</h1>
        <hr>
        <a href="StudentsController.jsp">Show Students</a>
        <%
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("com.softech_student-hibernate_war_1.0-SNAPSHOTPU");
            StudentsEntityJpaController controller = new StudentsEntityJpaController(emf);
            String Id = request.getParameter("studentId");
            if (Id != null) {
                StudentsEntity entity = controller.findStudentsEntity(Integer.parseInt(Id));
                Id = entity.getStudentId().toString();
            }
        %>
        <form method="post">
            Student Id : <input type="text" name="studentId" value="<%=Id%>">
            <input type="submit" value="Delete">
        </form>

        <%
            if (request.getMethod().equals("POST")) {
                controller.destroy(Integer.parseInt(Id));
                out.println("<h3>Delete Successfully</h3>");
            }
        %>
    </body>
</html>
