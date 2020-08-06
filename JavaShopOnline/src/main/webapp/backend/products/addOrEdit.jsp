<%@include file="../../common/templates/backend/header.jsp" %>
<%@include file="../../common/templates/backend/navbar.jsp" %>

<section class="row">
    <article class="col">
        <div class="card">
            <div class="card-header">
                Product
            </div>
            <ul class="card-body">
                <form action="action">
                    <div class="form-group">
                        <label for="productId">Product Id:</label>
                        <input type="text" class="form-control" id="productId" name="productId" placeholder="Product ID">
                    </div>
                    <div class="form-group">
                        <label for="name">Name:</label>
                        <input type="text" class="form-control" id="name" name="name" placeholder="Product Name">
                    </div>
                    <div class="form-group">
                        <label for="categoryId">Category Id:</label>
                        <input type="text" class="form-control" id="categoryId" name="categoryId" placeholder="Category ID">
                    </div>
                    <div class="form-group">
                        <label for="quantity">Quantity:</label>
                        <input type="text" class="form-control" id="quantity" name="quantity" placeholder="Quantity">
                    </div>
                    <div class="form-group">
                        <input type="submit" value="AddNew" class="btn btn-primary">
                    </div>
                </form>
            </ul>
        </div>
    </article>
</section>

<%@include file="../../common/templates/backend/footer.jsp" %>