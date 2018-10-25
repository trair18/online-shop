package com.gmail.trair8.servlet;


import com.gmail.trair8.connectionpool.ConnectionPool;
import com.gmail.trair8.controller.EndpointMethod;
import com.gmail.trair8.controller.RequestMappingClass;
import com.gmail.trair8.controller.RequestMappingMethod;
import com.gmail.trair8.controller.impl.Controller;
import com.gmail.trair8.exception.OnlineShopException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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

    private static final Logger LOGGER = LogManager.getLogger(DispatcherServlet.class);

    private Map<String, EndpointMethod> map;

    @Override
    public void init() {
        map = initUrlToEndpointMethodMap();
    }

    @Override
    public void destroy() {
        super.destroy();
        ConnectionPool.getInstance().dispose();
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
        try {
            String path = request.getRequestURI();
            EndpointMethod endpointMethod = map.get(path);
            if (endpointMethod == null) {
                LOGGER.error("endpointMethod not found.");
                throw new OnlineShopException();
            }
            String view = endpointMethod.invoke(request);
            while (view.startsWith("redirect ")) {
                path = view.substring("redirect ".length());
                endpointMethod = map.get(path);
                view = endpointMethod.invoke(request);
            }

            RequestDispatcher requestDispatcher = request.getRequestDispatcher(view);
            requestDispatcher.forward(request, response);
        } catch (Exception e) {
            LOGGER.error(e);
        }
    }

    private Map<String, EndpointMethod> initUrlToEndpointMethodMap() {
        String servletMapping = "/online-shop";
        String classMapping = null;
        String methodMapping = null;
        Map<String, EndpointMethod> urlToEndpointMethodMap = new HashMap<>();
        for (Controller controllerEnum : Controller.values()) {
            Object controller = controllerEnum.getInstance();
            if (controller.getClass().isAnnotationPresent(RequestMappingClass.class)) {
                RequestMappingClass requestMappingClass = controller.getClass().getAnnotation(RequestMappingClass.class);
                classMapping = requestMappingClass.path();
            }
            for (Method method : controller.getClass().getDeclaredMethods()) {
                if (method.isAnnotationPresent(RequestMappingMethod.class)) {
                    RequestMappingMethod requestMappingMethod = method.getAnnotation(RequestMappingMethod.class);
                    methodMapping = requestMappingMethod.path();
                    urlToEndpointMethodMap.put(
                            servletMapping + classMapping + methodMapping,
                            new EndpointMethod(method, controller)
                    );
                }
            }
        }
        return urlToEndpointMethodMap;
    }

}
