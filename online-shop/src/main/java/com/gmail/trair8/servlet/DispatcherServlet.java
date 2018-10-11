package com.gmail.trair8.servlet;


import com.gmail.trair8.controller.*;
import com.gmail.trair8.controller.impl.Controllers;
import com.gmail.trair8.controller.impl.ProductController;
import com.gmail.trair8.controller.impl.UserController;
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

    private final static Logger LOGGER = LogManager.getLogger();

    private UserController userController = new UserController();
    private ProductController productController = new ProductController();
    private Map<Class, Controller> map = new HashMap<>();

    private Map<String, EndpointMethod> asd;

    @Override
    public void init() throws ServletException {
        map.put(ProductController.class, productController);
        map.put(UserController.class, userController);
        initUrlToEndpointMethod();


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

        String path = request.getRequestURI().substring("/online-shop".length());

        String view = null;
        Class cl = null;
        Controller controller = null;

        for(Map.Entry<Class, Controller> pair : map.entrySet()) {
            Class cur = pair.getKey();
            ControllerA a = (ControllerA) cur.getAnnotation(ControllerA.class);
            String q = a.path();
            if (q.equals(path.substring(0, path.indexOf("/", 1)))) {
                cl = cur;
                controller = pair.getValue();
                path = path.substring(path.indexOf("/", 1));
                break;
            }
        }

        for (Method method : cl.getDeclaredMethods()) {
            if (method.isAnnotationPresent(RequestMapping.class)){
                RequestMapping requestMapping = method.getAnnotation(RequestMapping.class);
                if (requestMapping.path().equals(path)){
                    try {
                        view =  method.invoke(controller, request).toString();
                        break;
                    }catch (Exception e){
                        throw new RuntimeException(e);
                    }
                }
            }
        }

            RequestDispatcher requestDispatcher = request.getRequestDispatcher(view);
            requestDispatcher.forward(request, response);

    }

    private Map<String, EndpointMethod> initUrlToEndpointMethod() {
        String servletPath = "/online-shop";
        String classPath = null;
        String methodPath = null;
        Map<String, EndpointMethod> urlToEndpointMethod = new HashMap<>();
        for (Object controller : Controllers.getAll()) {
            if (controller.getClass().isAnnotationPresent(ControllerA.class)) {
                ControllerA controllerA = controller.getClass().getAnnotation(ControllerA.class);
                classPath = controllerA.path();
            }
            for (Method method : controller.getClass().getDeclaredMethods()) {
                if (method.isAnnotationPresent(RequestMapping.class)) {
                    RequestMapping requestMapping = method.getAnnotation(RequestMapping.class);
                    methodPath = requestMapping.path();
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
