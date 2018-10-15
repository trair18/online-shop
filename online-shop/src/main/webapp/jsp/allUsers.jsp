<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>My orders</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>

</head>
<body>
<jsp:include page="/jsp/include/navbar.jsp" />

<div>
    <c:if test="${not empty users}">

        <table class="table">
            <thead>
            <tr>
                <th scope="col">#</th>
                <th scope="col">email</th>
                <th scope="col">isAdmin</th>
                <th scope="col">surname</th>
                <th scope="col">first name</th>
                <th scope="col">account</th>
                <th scope="col">loyaltyPoints</th>
                <th scope="col">isBlocked</th>
                <th scope="col">#</th>

            </tr>
            </thead>
            <tbody>
            <c:forEach var="elem" items="${users}" varStatus="status">
                <form action="/online-shop/user/allUsers" method="POST">
                    <input name="id" hidden="true" value="${elem.id}"/>
                    <tr>
                        <th scope="row"><c:out value="${elem.id}"/></th>
                        <td><c:out value="${elem.email}"/></td>
                        <td><input name="admin" value="${elem.admin}"/></td>
                        <td><c:out value="${elem.surname}"/></td>
                        <td><c:out value="${elem.firstName}"/></td>
                        <td><c:out value="${elem.account}"/></td>
                        <td><input name="loyaltyPoints" value="${elem.loyaltyPoints}"/></td>
                        <td><input name="blocked" value="${elem.blocked}"/></td>
                        <td><button type="submit" class="btn btn-primary">Сохранить</button></td>
                    </tr>
                </form>
            </c:forEach>
            </tbody>
        </table>


    </c:if>

</div>
<jsp:include page="/jsp/include/footer.jsp" />
</body>
</html>