<%-- 
    Document   : AddNewProduct
    Created on : Jul 25, 2020, 6:28:59 PM
    Author     : TAI
--%>

<%@page import="com.webapp.product.controllers.ProductEntityJpaController"%>
<%@page import="javax.persistence.Persistence"%>
<%@page import="javax.persistence.EntityManagerFactory"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Product</title>
    </head>
    <body>
        <h1>Add New Product!</h1>
        <hr>
        <form method="post">
            Product ID: <input type="text" value="Auto" disabled><br><br>
            Name: <input type="text" name="name"><br><br>
            Quantity: <input type="number" name="quantity"><br><br>
            Unit Price: <input type="number" name="unitprice"><br><br>

            Manufacturer: <select name="manufacturer">
                <Option value="Sony">Sony</Option>
                <Option value="SamSung">SamSung</Option>
            </select><br><br>
            Description: <input type="text" name="description"><br><br>
            <hr>
            <input type="submit" name="action" value="Save">
            <input type="submit" name="action" value="Search">
        </form>

        <%
            if (request.getMethod().equals("POST")) {

        %>
        <jsp:useBean id="entity" class="com.webapp.product.entities.ProductEntity" scope="page"/>
        <jsp:setProperty name="entity" property="*"/>
        <%            EntityManagerFactory emf = Persistence.createEntityManagerFactory("com.webapp_Product_war_1.0-SNAPSHOTPU");
            ProductEntityJpaController controller = new ProductEntityJpaController(emf);
            String action = request.getParameter("action");
            if (action != null && action.equals("Save")) {
                controller.create(entity);
                out.println("<h3>Product Saved</h3>");
            } else {
                response.sendRedirect("Search.jsp");
            }
        %>
        <%}%>
    </body>
</html>
