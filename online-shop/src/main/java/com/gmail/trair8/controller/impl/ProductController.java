package com.gmail.trair8.controller.impl;

import com.gmail.trair8.controller.RequestMappingClass;
import com.gmail.trair8.controller.RequestMappingMethod;
import com.gmail.trair8.entity.Product;
import com.gmail.trair8.service.ProductService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


@RequestMappingClass(path = "/product")
public class ProductController {

    private ProductService productService = new ProductService();

    @RequestMappingMethod(path = "/main")
    public String findAll(HttpServletRequest request) {

        HttpSession session = request.getSession(true);
        if (session.getAttribute("role") == null) {
            session.setAttribute("role", "guest");
            session.setAttribute("lang", "en");
            session.setAttribute("cart", new ArrayList<Integer>());
        }
        List<Product> products = productService.findAll();
        request.setAttribute("products", products);
        return "/jsp/main.jsp";
    }

    @RequestMappingMethod(path = "/sneakers")
    public String findAllSneakers(HttpServletRequest request) {
        List<Product> products = productService.findByCategory("sneakers");
        request.setAttribute("products", products);
        return "/jsp/products.jsp";
    }

    @RequestMappingMethod(path = "/clothes")
    public String findAllClothes(HttpServletRequest request) {
        List<Product> products = productService.findByCategory("clothes");
        request.setAttribute("products", products);
        return "/jsp/products.jsp";
    }

    @RequestMappingMethod(path = "/accessories")
    public String findAllAccessories(HttpServletRequest request) {
        List<Product> products = productService.findByCategory("accessories");
        request.setAttribute("products", products);
        return "/jsp/products.jsp";
    }


    @RequestMappingMethod(path = "/add")
    public String add(HttpServletRequest request) {

        HttpSession session = request.getSession(true);
        List<Integer> list = (List<Integer>) session.getAttribute("cart");
        list.add(Integer.parseInt(request.getParameter("id")));
        session.setAttribute("cart", list);
        return findAllSneakers(request);
    }

    @RequestMappingMethod(path = "/delete")
    public String delete(HttpServletRequest request) {

        HttpSession session = request.getSession(true);
        List<Integer> list = (List<Integer>) session.getAttribute("cart");
        list.remove((Object) Integer.parseInt(request.getParameter("id")));
        session.setAttribute("cart", list);
        return showCart(request);
    }


    @RequestMappingMethod(path = "/cart")
    public String showCart(HttpServletRequest request) {
        HttpSession session = request.getSession(true);
        List<Integer> list = (List<Integer>) session.getAttribute("cart");
        List<Product> products = productService.findCartProd(list);
        request.setAttribute("products", products);
        return "/jsp/cart.jsp";

    }

    @RequestMappingMethod(path = "/addProductForm")
    public String addProductForm(HttpServletRequest request) {
        return "/jsp/addProductForm.jsp";
    }

    @RequestMappingMethod(path = "/addProduct")
    public String addProduct(HttpServletRequest request) {
        String name = request.getParameter("name");
        String category = request.getParameter("category");
        BigDecimal price = new BigDecimal(request.getParameter("price"));
        Boolean inStock = new Boolean(request.getParameter("inStock"));
        String img = request.getParameter("img");

        Product product = new Product();
        product.setName(name);
        product.setCategory(category);
        product.setPrice(price);
        product.setInStock(inStock);
        product.setImg(img);

        productService.addProduct(product);
        return "/jsp/addProduct.jsp";
    }

    @RequestMappingMethod(path = "/editProductForm")
    public String editProductForm(HttpServletRequest request) {
        int id = Integer.parseInt(request.getParameter("id"));
        Product product = productService.findProductById(id);
        request.setAttribute("product", product);
        return "/jsp/editProductForm.jsp";
    }


    @RequestMappingMethod(path = "/editProduct")
    public String editProduct(HttpServletRequest request) {
        String name = request.getParameter("name");
        String category = request.getParameter("category");
        BigDecimal price = new BigDecimal(request.getParameter("price"));
        Boolean inStock = new Boolean(request.getParameter("inStock"));
        String img = request.getParameter("img");
        int id = Integer.parseInt(request.getParameter("id"));

        Product product = new Product();
        product.setName(name);
        product.setCategory(category);
        product.setPrice(price);
        product.setInStock(inStock);
        product.setImg(img);

        productService.updateProduct(id, product);
        return "/jsp/addProduct.jsp";
    }

}
