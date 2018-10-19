
<%--
  Created by IntelliJ IDEA.
  User: Lenovo
  Date: 21.09.2018
  Time: 1:32
  To change this template use File | Settings | File Templates.
--%>
<%@page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${lang}"/>
<fmt:bundle basename="text">

<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <a class="navbar-brand" href="/online-shop">Online-shop</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown1" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                    <fmt:message key="text.catalog.title"/>
                </a>
                <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                    <a class="dropdown-item" href="/online-shop/product/clothes"><fmt:message key="text.catalog.clothes"/></a>
                    <a class="dropdown-item" href="/online-shop/product/sneakers"><fmt:message key="text.catalog.sneakers"/></a>
                    <a class="dropdown-item" href="/online-shop/product/accessories"><fmt:message key="text.catalog.accessories"/></a>
                </div>
            </li>

        </ul>

        <c:if test="${!role.equals('admin')}">
            <a class="button" href="/online-shop/product/cart"> <fmt:message key="text.cart.title"/></a>
        </c:if>
    <c:choose>
        <c:when test="${role.equals('client')}">
            <div class="nav-item dropdown">
                <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                    <fmt:message key="text.user.profile"/>
                </a>
                <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                    <a class="dropdown-item" href="#"><fmt:message key="text.user.myInformation"/></a>
                    <a class="dropdown-item" href="/online-shop/order/myOrders"><fmt:message key="text.user.myOrders"/></a>
                    <div class="dropdown-divider"></div>
                    <a class="dropdown-item" href="/online-shop/user/signout" ><fmt:message key="text.user.signOut"/></a>
                </div>
            </div>
        </c:when>
        <c:when test="${role.equals('admin')}">
            <div class="nav-item dropdown">
                <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownAdmin" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                    <fmt:message key="text.admin.info"/>
                </a>
                <div class="dropdown-menu" aria-labelledby="navbarDropdownAdmin">
                    <a class="dropdown-item" href="/online-shop/product/addProductForm"><fmt:message key="text.admin.addProduct"/></a>
                    <a class="dropdown-item" href="/online-shop/user/allUsers"><fmt:message key="text.admin.users"/></a>
                    <a class="dropdown-item" href="/online-shop/order/allOrders"><fmt:message key="text.admin.orders"/></a>
                    <div class="dropdown-divider"></div>
                    <a class="dropdown-item" href="/online-shop/user/signout" ><fmt:message key="text.user.signOut"/></a>
                </div>
            </div>
        </c:when>
        <c:otherwise>
            <div class="nav-item dropdown">
                <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown2" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                    <fmt:message key="text.SingIn.title"/>
                </a>
                <div class="dropdown-menu">
                    <form class="px-4 py-3" action="/online-shop/user/signin" method="POST">
                        <div class="form-group">
                            <label for="exampleDropdownFormEmail1"><fmt:message key="text.SingIn.email"/> </label>
                            <input name="email" type="email" class="form-control" id="exampleDropdownFormEmail1" placeholder="email@example.com" pattern="\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+">
                        </div>
                        <div class="form-group">
                            <label for="exampleDropdownFormPassword1"><fmt:message key="text.SingIn.password"/></label>
                            <input name="password" type="password" class="form-control" id="exampleDropdownFormPassword1" placeholder="<fmt:message key="text.SingIn.passwordPlaceholder"/>" pattern="^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\S+$).{6,25}$">
                        </div>
                        <%--<div class="form-check">
                            <input type="checkbox" class="form-check-input" id="dropdownCheck">
                            <label class="form-check-label" for="dropdownCheck">
                                Remember me
                            </label>
                        </div>--%>
                        <button type="submit" class="btn btn-primary"><fmt:message key="text.SingIn.button"/></button>
                        <input name="currentReq" hidden="true" value="${pageContext.request.requestURI}"/>
                    </form>
                    <div class="dropdown-divider"></div>
                    <a class="dropdown-item" href="/online-shop/user/signup"><fmt:message key="text.SignIn.registration"/></a>
                    <a class="dropdown-item" href="#"><fmt:message key="text.SignIn.forgotPassword"/></a>
                </div>
            </div>
        </c:otherwise>
    </c:choose>

        <form class="form-inline my-2 my-lg-0">
            <input class="form-control mr-sm-2" type="search" placeholder="Search" aria-label="Search">
            <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
        </form>

    </div>
</nav>
</fmt:bundle>