<%@ page import="com.gmail.trair8.entity.User" %><%--
  Created by IntelliJ IDEA.
  User: Lenovo
  Date: 20.09.2018
  Time: 19:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add new user</title>
</head>

<body>
<div>
    <h1>Super app!</h1>
</div>

<div>
    <%
        if (request.getAttribute("user") != null) {
            out.println("<p>User '" + ((User)request.getAttribute("user")).getEmail() + "' added!</p>");
        }
    %>
    <div>
        <div>
            <h2>Add user</h2>
        </div>

        <form method="post">
            <label>Name:
                <input type="text" name="email"><br />
            </label>
            <label>Password:
                <input type="password" name="pass"><br />
            </label>
            <button type="submit">Submit</button>
        </form>
    </div>
</div>

<div>
    <button onclick="location.href='/online-shop'">Back to main</button>
</div>
<jsp:include page="/jsp/include/footer.jsp" />
</body>
</html>