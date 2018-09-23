<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Lenovo
  Date: 21.09.2018
  Time: 15:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Online-shop!</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
</head>
<body>

<jsp:include page="/jsp/include/navbar.jsp" />





<div class="container-fluid">

    <div class="row">
        <c:if test="${not empty products}">
            <c:forEach var="elem" items="${products}" varStatus="status">

                <div class="col-sm-3">
                    <form class="card" style="width: 18rem;" action="${pageContext.request.contextPath}/product/add" method="GET">
                        <img class="card-img-top" src="${elem.img}" alt="Card image cap">
                        <div class="card-body">
                            <h5 class="card-title"><c:out value="${elem.name}"/></h5>
                            <p class="card-text"><c:out value="${elem.price}"/></p>
                            <p class="card-text"><c:out value="${elem.id}"/></p>
                            <button type="submit" class="btn btn-secondary btn-lg">Добавить в корзину</button>
                        </div>
                        <input name="id" hidden="true" value="${elem.id}"/>
                    </form>

                </div>
            </c:forEach>
        </c:if>
    </div>
    ${basket}
</div>

</body>
</html>
