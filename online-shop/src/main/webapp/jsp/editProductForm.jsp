<%--
  Created by IntelliJ IDEA.
  User: Lenovo
  Date: 15.10.2018
  Time: 19:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit Product</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>

</head>
<body>

<jsp:include page="/jsp/include/navbar.jsp" />

<div class="container-fluid">

    <form action="${pageContext.request.contextPath}/product/editProduct" method="POST">
        <input name="id" hidden="true" value="${product.id}">
        <div class="form-row">
            <div class="form-group col-md-6">
                <label for="inputName">Name</label>
                <input name="name" type="name" class="form-control" id="inputName" value="${product.name}" placeholder="Name">
            </div>
            <div class="form-group col-md-6">
                <label for="inputCategory">Category</label>
                <input name="category" type="category" class="form-control" id="inputCategory" value="${product.category}" placeholder="Category">
            </div>
        </div>
        <div class="form-row">
            <div class="form-group col-md-6">
                <label for="inputPrice">Price</label>
                <input name="price" type="price" class="form-control" id="inputPrice" value="${product.price}" placeholder="Price">
            </div>
            <div class="form-group col-md-6">
                <label for="inputInStock">inStock</label>
                <input name="inStock" type="inStock" class="form-control" id="inputInStock" value="${product.inStock}" placeholder="In Stock">
            </div>
        </div>

        <div class="form-row">
            <div class="form-group col-md-6">
                <label for="inputImg">Img</label>
                <input name="img" type="img" class="form-control" id="inputImg" value="${product.img}" placeholder="Img">
            </div>
            <div class="form-group col-md-6">
                <label for="inputImg">Добавить изображение в хранилище</label>
                <a href="https://ru.imgbb.com/">https://ru.imgbb.com/</a>
            </div>
        </div>

        <button type="submit" class="btn btn-primary">Save</button>
    </form>


</div>

<jsp:include page="/jsp/include/footer.jsp" />
</body>
</html>
