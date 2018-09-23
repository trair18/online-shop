<%--
  Created by IntelliJ IDEA.
  User: Lenovo
  Date: 18.09.2018
  Time: 2:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Страница подтверждения успешной регистрации посетителя</title>
</head>
<body>
<h1>Регистрация посетителя успешно завершена</h1>
<jsp:useBean id="user" class="com.gmail.trair8.entity.User" scope="application"/>
Email: <%= user.getEmail()%><br>
Имя: <%= user.getFirstName()%><br>
Фамилия: <%= user.getSurname()%><br>
Зарегистрирован.<br><br>
<a href="login.html">Войти в систему</a>
</body>
</html>