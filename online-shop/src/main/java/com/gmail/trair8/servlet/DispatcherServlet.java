package com.gmail.trair8.servlet;


import com.gmail.trair8.controller.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/")
public class DispatcherServlet extends HttpServlet {

    private final static Logger LOGGER = LogManager.getLogger();

    private UserController userController = new UserController();
    private ProductController productController = new ProductController();

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

        ModelAndView modelAndView = null;
        Class cl = null;
        Controller controller = null;

        if (path.startsWith("/product")){
            cl = ProductController.class;
            controller = productController;
        } else if (path.startsWith("/user"))
        {
            cl = UserController.class;
            controller = userController;
        }

        for (Method method : cl.getDeclaredMethods()) {
            if (method.isAnnotationPresent(RequestMapping.class)){
                RequestMapping requestMapping = method.getAnnotation(RequestMapping.class);
                if (requestMapping.path().equals(path)){
                    try {
                        modelAndView = (ModelAndView) method.invoke(controller, request);
                        break;
                    }catch (Exception e){
                        throw new RuntimeException(e);
                    }
                }
            }
        }
        if (modelAndView.getModel() != null) {
            for (Map.Entry<String, Object> pair : modelAndView.getModel().entrySet()) {
                request.setAttribute(pair.getKey(), pair.getValue());
            }
        }

        RequestDispatcher requestDispatcher = request.getRequestDispatcher(modelAndView.getView());
        requestDispatcher.forward(request, response);

    }
}
