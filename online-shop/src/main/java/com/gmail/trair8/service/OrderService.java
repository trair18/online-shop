package com.gmail.trair8.service;

import com.gmail.trair8.connectionpool.ConnectionPool;
import com.gmail.trair8.dao.OrderDAO;
import com.gmail.trair8.dao.ProductDAO;
import com.gmail.trair8.entity.Order;
import com.gmail.trair8.entity.Product;
import com.gmail.trair8.exception.ConnectionPoolException;
import com.gmail.trair8.exception.DAOException;
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

        } catch (ConnectionPoolException e) {
            LOGGER.error(e);
        }  catch (SQLException e) {
            LOGGER.error(e);
        } catch (DAOException e){
            System.out.println(e);
        }
    }

    public List<Order> findOrders(int id){
        List<Order> orders = new ArrayList<>();
        try {
            ConnectionPool connectionPool = ConnectionPool.getInstance();
            Connection cn = connectionPool.takeConnection();

            OrderDAO orderDAO = new OrderDAO(cn);
            connectionPool.closeConnection(cn);

            return orderDAO.findOrderByUserId(id);


        } catch (ConnectionPoolException e) {
            LOGGER.error(e);
        } catch (DAOException e) {
            LOGGER.error(e);
        }
        return orders;
    }

    public List<Order> findAll(){
        List<Order> orders = new ArrayList<>();
        try {
            ConnectionPool connectionPool = ConnectionPool.getInstance();
            Connection cn = connectionPool.takeConnection();

            OrderDAO orderDAO = new OrderDAO(cn);
            orders = orderDAO.findAll();
            connectionPool.closeConnection(cn);
        } catch (ConnectionPoolException e) {
            LOGGER.error(e);
        } catch (DAOException e) {
            LOGGER.error(e);
        }
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

        } catch (ConnectionPoolException e) {
            LOGGER.error(e);
        }  catch (SQLException e) {
            LOGGER.error(e);
        } catch (DAOException e){
            System.out.println(e);
        }
    }

}
