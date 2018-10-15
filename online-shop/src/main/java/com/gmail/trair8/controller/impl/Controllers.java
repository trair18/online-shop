package com.gmail.trair8.controller.impl;


import java.util.Arrays;
import java.util.List;

public class Controllers {

    public static final ProductController PRODUCT_CONTROLLER = new ProductController();
    public static final UserController USER_CONTROLLER = new UserController();
    public static final OrderController ORDER_CONTROLLER = new OrderController();

    public static List<Object> getAll() {
        return Arrays.asList(PRODUCT_CONTROLLER, USER_CONTROLLER, ORDER_CONTROLLER);
    }
}
