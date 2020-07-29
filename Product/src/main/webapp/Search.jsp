<%-- 
    Document   : Search
    Created on : Jul 25, 2020, 6:51:59 PM
    Author     : TAI
--%>

<%@page import="javax.persistence.Query"%>
<%@page import="javax.persistence.EntityManager"%>
<%@page import="java.util.List"%>
<%@page import="com.webapp.product.entities.ProductEntity"%>
<%@page import="com.webapp.product.controllers.ProductEntityJpaController"%>
<%@page import="javax.persistence.Persistence"%>
<%@page import="javax.persistence.EntityManagerFactory"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Search Product</title>
    </head>
    <body>
        <form method="post">
            Name: <input type="text" name="name"><br><br>

            <input type="submit" value="Seach">
        </form>
        <table border="2px">
            <tr>
                <td>Name</td>
                <td>Quantity</td>
                <td>Unit Price</td>
                <td>Manufacturer</td>
                <td>&nbsp;</td>
            </tr>
            <%
                if (request.getMethod().equals("POST")) {
                    String name = request.getParameter("name");
                    EntityManagerFactory emf = Persistence.createEntityManagerFactory("com.webapp_Product_war_1.0-SNAPSHOTPU");
                    EntityManager manager = emf.createEntityManager();
                    Query findByName = manager.createNamedQuery("ProductEntity.findByName");
                    findByName.setParameter("name", name);
                    List<ProductEntity> list = findByName.getResultList();
                    for (ProductEntity productEntity : list) {
                        request.setAttribute("entity", productEntity);

            %>
            <tr>
                <td>${entity.name}</td>
                <td>${entity.quantity}</td>
                <td>${entity.unitprice}</td>
                <td>${entity.manufacturer}</td>
            </tr>
            <%}%>
            <%}%>
        </table>


    </body>
</html>
