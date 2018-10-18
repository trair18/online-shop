package com.gmail.trair8.servlet;


import com.gmail.trair8.controller.EndpointMethod;
import com.gmail.trair8.controller.RequestMappingClass;
import com.gmail.trair8.controller.RequestMappingMethod;
import com.gmail.trair8.controller.impl.Controllers;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/")
public class DispatcherServlet extends HttpServlet {

    private Map<String, EndpointMethod> map;

    @Override
    public void init() {
        map = initUrlToEndpointMethod();
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String path = request.getRequestURI();

        EndpointMethod endpointMethod = map.get(path);
        String view = endpointMethod.invoke(request);

        RequestDispatcher requestDispatcher = request.getRequestDispatcher(view);
        requestDispatcher.forward(request, response);

    }

    private Map<String, EndpointMethod> initUrlToEndpointMethod() {
        String servletPath = "/online-shop";
        String classPath = null;
        String methodPath = null;
        Map<String, EndpointMethod> urlToEndpointMethod = new HashMap<>();
        for (Object controller : Controllers.getAll()) {
            if (controller.getClass().isAnnotationPresent(RequestMappingClass.class)) {
                RequestMappingClass requestMappingClass = controller.getClass().getAnnotation(RequestMappingClass.class);
                classPath = requestMappingClass.path();
            }
            for (Method method : controller.getClass().getDeclaredMethods()) {
                if (method.isAnnotationPresent(RequestMappingMethod.class)) {
                    RequestMappingMethod requestMappingMethod = method.getAnnotation(RequestMappingMethod.class);
                    methodPath = requestMappingMethod.path();
                    urlToEndpointMethod.put(
                            servletPath + classPath + methodPath,
                            new EndpointMethod(method, controller)
                    );
                }
            }
        }
        return urlToEndpointMethod;
    }

}
