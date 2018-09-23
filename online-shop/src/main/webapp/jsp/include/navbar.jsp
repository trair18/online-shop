<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Lenovo
  Date: 21.09.2018
  Time: 1:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <a class="navbar-brand" href="/online-shop">Online-shop</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown1" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                    Каталог
                </a>
                <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                    <a class="dropdown-item" href="#">Одежда</a>
                    <a class="dropdown-item" href="/online-shop/product/sneakers">Обувь</a>
                    <a class="dropdown-item" href="#">Аксессуары</a>
                    <div class="dropdown-divider"></div>
                    <a class="dropdown-item" href="#">Скидки</a>
                </div>
            </li>

        </ul>

    <c:choose>
        <c:when test="${role.equals('client')}">
            <div class="nav-item dropdown">
                <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                    Профиль
                </a>
                <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                    <a class="dropdown-item" href="#">Мои Данные</a>
                    <a class="dropdown-item" href="#">Мои Заказы</a>
                    <a class="dropdown-item" href="/online-shop/product/basket">Корзина</a>
                    <div class="dropdown-divider"></div>
                    <a class="dropdown-item" href="/online-shop/user/signout" >Выйти</a>
                </div>
            </div>
        </c:when>
        <c:otherwise>
            <div class="nav-item dropdown">
                <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown2" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                    Войти
                </a>
                <div class="dropdown-menu">
                    <form class="px-4 py-3" action="${pageContext.request.contextPath}/user/signin" method="POST">
                        <div class="form-group">
                            <label for="exampleDropdownFormEmail1">Email address</label>
                            <input name="email" type="email" class="form-control" id="exampleDropdownFormEmail1" placeholder="email@example.com">
                        </div>
                        <div class="form-group">
                            <label for="exampleDropdownFormPassword1">Password</label>
                            <input name="password" type="password" class="form-control" id="exampleDropdownFormPassword1" placeholder="Password">
                        </div>
                        <div class="form-check">
                            <input type="checkbox" class="form-check-input" id="dropdownCheck">
                            <label class="form-check-label" for="dropdownCheck">
                                Remember me
                            </label>
                        </div>
                        <button type="submit" class="btn btn-primary">Sign in</button>
                    </form>
                    <div class="dropdown-divider"></div>
                    <a class="dropdown-item" href="/online-shop/user/signup">New around here? Sign up</a>
                    <a class="dropdown-item" href="#">Forgot password?</a>
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
