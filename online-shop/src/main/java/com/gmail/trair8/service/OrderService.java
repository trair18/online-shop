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

public class OrderService {

    private static final Logger LOGGER = LogManager.getLogger(OrderService.class);

    public void makeOrder(List<Order> orderList) {
        try (Connection cn = ConnectionPool.getInstance().takeConnection()) {
            cn.setAutoCommit(false);
            OrderDAO orderDAO = new OrderDAO(cn);
            for (Order order : orderList) {
                orderDAO.insert(order);
            }
            cn.commit();
        } catch (SQLException e) {
            LOGGER.error(e);
            throw new OnlineShopException(e);
        }
    }

    public Map<Order, String> findOrders(int id) {
        try (Connection cn = ConnectionPool.getInstance().takeConnection()) {
            OrderDAO orderDAO = new OrderDAO(cn);
            return orderDAO.findOrderByUserId(id);
        } catch (SQLException e) {
            LOGGER.error(e);
            throw new OnlineShopException(e);
        }
    }

    public List<Order> findAll() {
        try (Connection cn = ConnectionPool.getInstance().takeConnection()) {
            OrderDAO orderDAO = new OrderDAO(cn);
            return orderDAO.findAll();
        } catch (SQLException e) {
            LOGGER.error(e);
            throw new OnlineShopException(e);
        }
    }

    public void updateActual(int id, boolean actual) {
        try (Connection cn = ConnectionPool.getInstance().takeConnection()) {
            cn.setAutoCommit(false);

            OrderDAO orderDAO = new OrderDAO(cn);
            orderDAO.updateActual(id, actual);

            cn.commit();
        } catch (SQLException e) {
            LOGGER.error(e);
            throw new OnlineShopException(e);
        }
    }

}
