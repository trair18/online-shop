<%--
  Created by IntelliJ IDEA.
  User: Lenovo
  Date: 22.09.2018
  Time: 2:56
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Basket</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>

</head>
<body>
<jsp:include page="/jsp/include/navbar.jsp" />

<div class="row">
    <c:if test="${not empty basket}">
        <c:forEach var="elem" items="${products}" varStatus="status">
            <div class="col-sm-3">
                <div class="card" style="width: 18rem;">
                    <img class="card-img-top" src="${elem.img}" alt="Card image cap">
                    <div class="card-body">
                        <h5 class="card-title"><c:out value="${elem.name}"/></h5>
                        <p class="card-text"><c:out value="${elem.price}"/></p>
                        <p class="card-text" hidden="true">${elem.id}</p>
                        <a href="#" class="btn btn-primary">Убрать из корзину</a>
                    </div>
                </div>

            </div>
        </c:forEach>
    </c:if>


</div>

</body>
</html>
