<%@page import="com.webapp.javashoponline.entities.Category"%>
<%@page import="java.util.List"%>
<%@page import="com.webapp.javashoponline.controllers.CategoryJpaController"%>
<%@page import="javax.persistence.Persistence"%>
<%@page import="javax.persistence.EntityManagerFactory"%>
<%@include file="../../common/templates/backend/header.jsp" %>
<%@include file="../../common/templates/backend/navbar.jsp" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<section class="row">
    <article class="col">
        <div class="card">
            <div class="card-header">
                List All Categories
            </div>
            <div class="card-body">
                <table class="table table-striped">
                    <tr>
                        <td>Category ID</td>
                        <td>Name</td>
                        <td>&nbsp;</td>
                    </tr>
                    <%
                        EntityManagerFactory emf = Persistence.createEntityManagerFactory("com.webapp_JavaShopOnline_war_1.0-SNAPSHOTPU");
                        CategoryJpaController controller = new CategoryJpaController(emf);
                        List<Category> list = controller.findCategoryEntities();
                        for (Category category : list) {
                            request.setAttribute("entity", category);
                    %>
                    <tr>
                        <td>${entity.categoryId}</td>
                        <td>${entity.name}</td>
                        <td>
                            <a href="DetailCategory?id=${entity.categoryId}">Detail</a>
                            <a href="EditCategory?id=${entity.categoryId}">Edit</a>
                            <a href="DeleteCategory?id=${entity.categoryId}">Delete</a>
                        </td>
                    </tr>
                    <%}%>
                </table>
            </div>
        </div>
    </article>
</section>

<%@include file="../../common/templates/backend/footer.jsp" %>