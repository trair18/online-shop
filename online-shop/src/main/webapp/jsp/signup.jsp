<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Lenovo
  Date: 21.09.2018
  Time: 21:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Sign up</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>

</head>
<body>

<jsp:include page="/jsp/include/navbar.jsp" />

<div class="container-fluid">

    <form action="${pageContext.request.contextPath}/user/signupform" method="POST">
        <div class="form-row">
            <div class="form-group col-md-6">
                <label for="inputEmail4">Email</label>
                <input name="email" type="email" class="form-control" id="inputEmail4" placeholder="Email">
            </div>
            <div class="form-group col-md-6">
                <label for="inputPassword4">Password</label>
                <input name="password" type="password" class="form-control" id="inputPassword4" placeholder="Password">
            </div>
        </div>
        <div class="form-row">
            <div class="form-group col-md-6">
                <label for="inputFirstName">First name</label>
                <input name="firstName" type="firtsname" class="form-control" id="inputFirstName" placeholder="First name">
            </div>
            <div class="form-group col-md-6">
                <label for="inputSurname">Surname</label>
                <input name="surname" type="surname" class="form-control" id="inputSurname" placeholder="Surname">
            </div>
        </div>

        <button type="submit" class="btn btn-primary">Sign in</button>
    </form>

    <c:if test="${user != null}">
        ${user.firstName} ${user.surname} успешно зарегестрирован!
    </c:if>
    <c:if test="${user == null}">
        Пользователь с такой почтой уже зарегестривован!
    </c:if>
    <c:if test="${role.equals('client')}">
        role: ${role}
    </c:if>

</div>


</body>
</html>
