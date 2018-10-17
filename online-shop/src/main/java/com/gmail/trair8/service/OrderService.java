package com.gmail.trair8.service;

import com.gmail.trair8.connectionpool.ConnectionPool;
import com.gmail.trair8.dao.OrderDAO;
import com.gmail.trair8.entity.Order;
import com.gmail.trair8.exception.OnlineShopException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderService {

    private static final Logger LOGGER = LogManager.getLogger();


    public void makeOrder(List<Order> orderList){
        try {
            ConnectionPool connectionPool = ConnectionPool.getInstance();
            Connection cn = connectionPool.takeConnection();
            cn.setAutoCommit(false);

            OrderDAO orderDAO = new OrderDAO(cn);
            for (Order order : orderList) {
                orderDAO.insert(order);
            }

            cn.commit();
            connectionPool.closeConnection(cn);

        }  catch (SQLException e) {
            LOGGER.error(e);
            throw new OnlineShopException(e);
        }
    }

    public List<Order> findOrders(int id){

            ConnectionPool connectionPool = ConnectionPool.getInstance();
            Connection cn = connectionPool.takeConnection();

            OrderDAO orderDAO = new OrderDAO(cn);
            connectionPool.closeConnection(cn);

            return orderDAO.findOrderByUserId(id);


    }

    public List<Order> findAll(){
        List<Order> orders = new ArrayList<>();
            ConnectionPool connectionPool = ConnectionPool.getInstance();
            Connection cn = connectionPool.takeConnection();

            OrderDAO orderDAO = new OrderDAO(cn);
            orders = orderDAO.findAll();
            connectionPool.closeConnection(cn);

        return orders;
    }

    public void updateActual(int id, boolean actual){
        try {
            ConnectionPool connectionPool = ConnectionPool.getInstance();
            Connection cn = connectionPool.takeConnection();
            cn.setAutoCommit(false);

            OrderDAO orderDAO = new OrderDAO(cn);
            orderDAO.updateActual(id, actual);

            cn.commit();
            connectionPool.closeConnection(cn);

        }  catch (SQLException e) {
            LOGGER.error(e);
            throw new OnlineShopException(e);
        }
    }

}
