<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Lenovo
  Date: 18.09.2018
  Time: 17:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>

</head>
<body>
<form action = "DispatcherServlet" method="get"></form>

<h1>ЛИИИИИСТ</h1>

<c:if test="${not empty users}">
    <ui>
    <c:forEach var="elem" items="${users}" varStatus="status">
        <li><c:out value="${elem.email}"/></li>
    </c:forEach>
    </ui>
</c:if>

<c:if test="${not empty user}">
    <c:out value="${user.email}"/>
</c:if>

</body>
</html>
