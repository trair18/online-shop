package com.gmail.trair8.controller;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class EndpointMethod {

    private Method method;
    private Object controller;

    public EndpointMethod(Method method, Object controller) {
        this.method = method;
        this.controller = controller;
    }

    public String invoke(HttpServletRequest request) {
        try {
            return method.invoke(controller, request).toString();
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException("Problem in reflection while calling controller method");
        }

    }
}
