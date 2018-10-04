package com.gmail.trair8.controller;

import com.gmail.trair8.entity.Product;
import com.gmail.trair8.service.ProductService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;


@ControllerA(path = "/product")
public class ProductController implements Controller{

    private final static Logger LOGGER = LogManager.getLogger(ProductController.class);

    private ProductService productService = new ProductService();

    @RequestMapping(method = "get", path = "/main")
    public String findAll(HttpServletRequest request){

        HttpSession session = request.getSession(true);
        if(session.getAttribute("role") == null){
            session.setAttribute("role", "guest");
            session.setAttribute("lang", "en");
            session.setAttribute("cart", new ArrayList<Integer>());
        }
        List<Product> products = new ArrayList<>();
        products = productService.findAll();
        request.setAttribute("products", products);
        return "/jsp/main.jsp";
    }

    @RequestMapping(method = "get", path = "/sneakers")
    public String findAllSneakers(HttpServletRequest request){

        List<Product> products = new ArrayList<>();
        products = productService.findAll();
        request.setAttribute("products", products);
        return "/jsp/sneakers.jsp";
    }

    @RequestMapping(method = "get", path = "/add")
    public String add(HttpServletRequest request){

        HttpSession session = request.getSession(true);
        List<Integer> list = (List<Integer>) session.getAttribute("cart");
        list.add(Integer.parseInt(request.getParameter("id")));
        session.setAttribute("cart", list);
        return findAllSneakers(request);
    }

    @RequestMapping(method = "get", path = "/delete")
    public String delete(HttpServletRequest request){

        HttpSession session = request.getSession(true);
        List<Integer> list = (List<Integer>) session.getAttribute("cart");
        list.remove((Object)Integer.parseInt(request.getParameter("id")));
        session.setAttribute("cart", list);
        return showCart(request);
    }



    @RequestMapping(method = "get",path = "/cart")
    public String showCart(HttpServletRequest request){
        HttpSession session = request.getSession(true);
        List<Integer> list = (List<Integer>) session.getAttribute("cart");
        List<Product> products = productService.findCartProd(list);
        request.setAttribute("products", products);
        return "/jsp/cart.jsp";

    }

}
