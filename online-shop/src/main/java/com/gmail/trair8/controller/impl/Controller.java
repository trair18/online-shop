package com.gmail.trair8.controller.impl;


import java.util.Arrays;
import java.util.List;

public enum  Controller {

    PRODUCT_CONTROLLER(new ProductController()),
    USER_CONTROLLER(new UserController()),
    ORDER_CONTROLLER(new OrderController());

    private final Object instance;

    Controller(Object instance) {
        this.instance = instance;
    }

    public Object getInstance() {
        return instance;
    }
}
