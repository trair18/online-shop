package com.gmail.trair8.controller;

import com.gmail.trair8.connectionpool.ConnectionPool;
import com.gmail.trair8.dao.ProductDAO;
import com.gmail.trair8.dao.UserDAO;
import com.gmail.trair8.entity.Product;
import com.gmail.trair8.entity.User;
import com.gmail.trair8.exception.ConnectionPoolException;
import com.gmail.trair8.exception.DAOException;
import com.gmail.trair8.service.ProductService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


//@RequestMapping(path = "/product")
public class ProductController implements Controller{

    private final static Logger LOGGER = LogManager.getLogger(ProductController.class);

    private ProductService productService = new ProductService();

    @RequestMapping(method = "get", path = "/product/main")
    public ModelAndView findAll(HttpServletRequest request){

        HttpSession session = request.getSession(true);
        if(session.getAttribute("role") == null){
            session.setAttribute("role", "guest");
        }

        List<Product> products = new ArrayList<>();
        Map<String, Object> map = new HashMap<>();

        products = productService.findAll();
        map.put("products", products);

        return new ModelAndView("/jsp/main.jsp", map);
    }

    @RequestMapping(method = "get", path = "/product/sneakers")
    public ModelAndView findAllSneakers(HttpServletRequest request){
        List<Product> products = new ArrayList<>();
        Map<String, Object> map = new HashMap<>();

        products = productService.findAll();
        map.put("products", products);
        return new ModelAndView("/jsp/sneakers.jsp", map);
    }

    @RequestMapping(method = "get", path = "/product/add")
    public ModelAndView add(HttpServletRequest request){

        HttpSession session = request.getSession(true);
        List<Integer> list = (List<Integer>) session.getAttribute("basket");
        list.add(Integer.parseInt(request.getParameter("id")));

        session.setAttribute("basket", list);

        return findAllSneakers(request);
    }

    //@Secured(roles = {"", ""})
    @RequestMapping(method = "get",path = "/product/basket")
    public ModelAndView showBasket(HttpServletRequest request){
        Map<String, Object> map = new HashMap<>();
        HttpSession session = request.getSession(true);
        List<Integer> list = (List<Integer>) session.getAttribute("basket");
        List<Product> products = productService.findBasketProd(list);
        map.put("products", products);

        return new ModelAndView("/jsp/basket.jsp", map);

    }


}
