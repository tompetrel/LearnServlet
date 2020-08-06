<%@page import="com.webapp.javashoponline.entities.Product"%>
<%@page import="java.util.List"%>
<%@page import="com.webapp.javashoponline.controllers.ProductJpaController"%>
<%@page import="javax.persistence.Persistence"%>
<%@page import="javax.persistence.EntityManagerFactory"%>
<%@include file="../../common/templates/backend/header.jsp" %>
<%@include file="../../common/templates/backend/navbar.jsp" %>
<section class="row">
    <article class="col">
        <div class="card">
            <div class="card-header">
                List All Products
            </div>
            <div class="card-body">
                <table class="table table-striped">
                    <tr>
                        <td>Products ID</td>
                        <td>Name</td>
                        <td>Category Id</td>
                        <td>Quantity</td>
                        <td>Price</td>
                        <td>Status</td>
                        <td>Manufacture Date</td>
                        <td>Image</td>
                        <td>Description</td>
                        <td>&nbsp;</td>
                    </tr>
                    <%
                        EntityManagerFactory emf = Persistence.createEntityManagerFactory("com.webapp_JavaShopOnline_war_1.0-SNAPSHOTPU");
                        ProductJpaController controller = new ProductJpaController(emf);
                        List<Product> list = controller.findProductEntities();
                        for (Product product : list) {
                            request.setAttribute("entity", product);
                    %>
                    <tr>
                        <td>${entity.productId}</td>
                        <td>${entity.name}</td>
                        <td>${entity.categoryId}</td>
                        <td>${entity.quantity}</td>
                        <td>${entity.price}</td>
                        <td>${entity.status}</td>
                        <td>${entity.manufactureDate}</td>
                        <td>${entity.image}</td>
                        <td>${entity.description}</td>
                        <td>
                            <a href="DetailProduct?id=${entity.productId}">Detail</a>
                            <a href="EditProduct?id=${entity.productId}">Edit</a>
                            <a href="DeleteProduct?id=${entity.productId}">Delete</a>
                        </td>
                    </tr>
                    <%}%>
                </table>
            </div>
        </div>
    </article>
</section>

<%@include file="../../common/templates/backend/footer.jsp" %>