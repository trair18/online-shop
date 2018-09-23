<%--
  Created by IntelliJ IDEA.
  User: Lenovo
  Date: 17.09.2018
  Time: 17:29
  To change this template use File | Settings | File Templates.
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html>
<head>
    <title> Очень простая страница JSP </title>
</head>
<body>
<h1> Очень простая страница JSP </h1>
<НЗ>Этот пример показывает, как работать с выражениями JSP</НЗ>
    Текущая дата: <%= new java.util.Date()%>
    <br>
    Значение параметра "param": <%= request.getParameter("param")%>
</body>
</html>