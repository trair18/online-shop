package com.gmail.trair8.controller.impl;

import com.gmail.trair8.controller.Controller;
import com.gmail.trair8.controller.RequestMappingClass;
import com.gmail.trair8.controller.RequestMappingMethod;
import com.gmail.trair8.entity.Order;
import com.gmail.trair8.service.OrderService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RequestMappingClass(path = "/order")
public class OrderController implements Controller {

    private OrderService orderService = new OrderService();

    @RequestMappingMethod(path = "/makeOrderForm")
    public String order(HttpServletRequest request) {
        HttpSession session = request.getSession(true);
        if (session.getAttribute("role").equals("guest")) {
            return "/jsp/signup.jsp";
        } else {
            return "/jsp/order.jsp";
        }
    }

    @RequestMappingMethod(path = "/makeOrder")
    public String makeOrder(HttpServletRequest request) {
        String address = request.getParameter("address");
        String payment = request.getParameter("payment");
        System.out.println(payment);
        HttpSession session = request.getSession(true);
        int userId = (Integer) session.getAttribute("id");


        List<Integer> cartList = (ArrayList<Integer>) session.getAttribute("cart");
        List<Order> orderList = new ArrayList<>();
        Order order;
        for (Integer i : cartList) {
            order = new Order();
            order.setAddress(address);
            order.setPayment(payment);
            order.setUserId(userId);
            order.setProductId(i);
            order.setTime(new Date());
            order.setActual(true);
            orderList.add(order);
        }
        orderService.makeOrder(orderList);
        session.setAttribute("cart", new ArrayList<Integer>());


        return Controllers.PRODUCT_CONTROLLER.findAll(request);
    }

    @RequestMappingMethod(path = "/myOrders")
    public String showMyOrders(HttpServletRequest request) {
        HttpSession session = request.getSession(true);
        int id = (Integer) session.getAttribute("id");
        Map<Order, String> orders = orderService.findOrders(id);
        request.setAttribute("orders", orders);

        return "/jsp/myOrders.jsp";
    }


    @RequestMappingMethod(path = "/allOrders")
    public String showAllOrders(HttpServletRequest request) {
        if (request.getParameter("actual") != null) {
            Boolean actual = new Boolean(request.getParameter("actual"));
            Integer id = new Integer(request.getParameter("orderId"));
            orderService.updateActual(id, actual);
        }

        List<Order> orders = orderService.findAll();
        request.setAttribute("orders", orders);

        return "/jsp/allOrders.jsp";
    }
}
