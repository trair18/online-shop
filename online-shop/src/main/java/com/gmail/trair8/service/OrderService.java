package com.gmail.trair8.service;

import com.gmail.trair8.connectionpool.ConnectionPool;
import com.gmail.trair8.dao.OrderDAO;
import com.gmail.trair8.entity.Order;
import com.gmail.trair8.exception.OnlineShopException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import static com.gmail.trair8.util.ConnectionUtils.modifyOperations;
import static com.gmail.trair8.util.ConnectionUtils.readOperations;

public class OrderService {

    private static final Logger LOGGER = LogManager.getLogger(OrderService.class);

    private OrderDAO orderDAO = new OrderDAO();

    public void makeOrder(List<Order> orderList) {
        for (Order order : orderList) {
            modifyOperations(connection -> orderDAO.insert(connection, order));
        }
    }

    public Map<Order, String> findOrders(int id) {

        return readOperations(connection -> orderDAO.findOrderByUserId(connection, id));
    }

    public List<Order> findAll() {
        return readOperations(connection -> orderDAO.findAll(connection));
    }

    public void updateActual(int id, boolean actual) {
            modifyOperations(connection -> orderDAO.updateActual(connection, id, actual));
    }

}
