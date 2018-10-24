package com.gmail.trair8.dao;


import com.gmail.trair8.entity.Order;
import com.gmail.trair8.exception.OnlineShopException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class OrderDAO extends AbstractDAO<Order> {

    private static final Logger LOGGER = LogManager.getLogger(ProductDAO.class);

    private static final String SELECT_ALL_ORDERS =
            "SELECT * FROM orders";

    private static final String SELECT_ORDER_BY_ID_SQL =
            "SELECT * FROM orders WHERE id = ?";

    private static final String INSERT_ORDER_SQL =
            "INSERT INTO orders (User_id, Product_id, isActual, address, payment, time)" +
                    "VALUES (?, ?, ?, ?, ?, ?)";

    private static final String UPDATE_ORDER =
            "UPDATE orders SET User_id = ?, Product_id = ?, isActual = ?, address = ?, payment = ?, time = ? WHERE id = ?";

    private static final String UPDATE_ACTUAL =
            "UPDATE orders SET isActual = ? WHERE id = ?";


    private static final String SELECT_ORDERS_BY_USER_ID_SQL =
            "SELECT  name, orders.id, User_id, Product_id, isActual, address, payment, time FROM cafe.orders LEFT JOIN cafe.products ON orders.Product_id = products.id WHERE User_id = ?";

    public OrderDAO() {

    }

    @Override
    public List<Order> findAll(Connection connection) {
        try (PreparedStatement ps = connection.prepareStatement(SELECT_ALL_ORDERS);
             ResultSet rs = ps.executeQuery()) {
            List<Order> orders = new ArrayList<>();
            while (rs.next()) {
                orders.add(makeEntity(rs));
            }
            return orders;
        } catch (SQLException e) {
            LOGGER.error("Problem when trying to find all orders");
            throw new OnlineShopException("Problem when trying to find all orders", e);
        }
    }

    @Override
    public Order findEntityById(Connection connection, int id) {
        try (PreparedStatement ps = connection.prepareStatement(SELECT_ORDER_BY_ID_SQL)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                rs.next();
                return makeEntity(rs);
            }
        } catch (SQLException e) {
            LOGGER.error("Problem when trying to find order by id");
            throw new OnlineShopException("Problem when trying to find order by id", e);
        }
    }

    public Map<Order, String> findOrderByUserId(Connection connection, int userId) {
        try (PreparedStatement ps = connection.prepareStatement(SELECT_ORDERS_BY_USER_ID_SQL)) {
            ps.setInt(1, userId);
            try (ResultSet rs = ps.executeQuery()) {
                Map<Order, String> map = new HashMap<>();
                while (rs.next()) {
                    map.put(makeEntity(rs), rs.getString("name"));
                }
                return map;
            }
        } catch (SQLException e) {
            LOGGER.error("Problem when trying to find order by user id");
            throw new OnlineShopException("Problem when trying to find order by user id", e);
        }
    }


    private Order makeEntity(ResultSet rs) throws SQLException {
        Order order = new Order();
        order.setId(rs.getInt("id"));
        order.setUserId(rs.getInt("User_id"));
        order.setProductId(rs.getInt("Product_id"));
        order.setActual(rs.getBoolean("isActual"));
        order.setAddress(rs.getString("address"));
        order.setPayment(rs.getString("payment"));
        order.setTime(new Date(rs.getLong("time")));
        return order;
    }

    @Override
    public void insert(Connection connection, Order order) {
        try (PreparedStatement ps = connection.prepareStatement(INSERT_ORDER_SQL)) {
            ps.setInt(1, order.getUserId());
            ps.setInt(2, order.getProductId());
            ps.setBoolean(3, order.isActual());
            ps.setString(4, order.getAddress());
            ps.setString(5, order.getPayment());
            ps.setLong(6, order.getTime().getTime());
            ps.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error("Problem when trying to insert order");
            throw new OnlineShopException("Problem when trying to insert order", e);
        }
    }

    @Override
    public void update(Connection connection, int id, Order order) {
        try (PreparedStatement ps = connection.prepareStatement(UPDATE_ORDER)) {
            ps.setInt(1, order.getUserId());
            ps.setInt(2, order.getProductId());
            ps.setBoolean(3, order.isActual());
            ps.setString(4, order.getAddress());
            ps.setString(5, order.getPayment());
            ps.setLong(6, order.getTime().getTime());
            ps.setInt(7, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error("Problem when trying to update order by id");
            throw new OnlineShopException("Problem when trying to update order by id", e);
        }
    }


    public void updateActual(Connection connection, int id, boolean actual) {
        try (PreparedStatement ps = connection.prepareStatement(UPDATE_ACTUAL)) {
            ps.setBoolean(1, actual);
            ps.setInt(2, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error("Problem when trying to update order actual by id");
            throw new OnlineShopException("Problem when trying to update order actual by id", e);
        }
    }

}
