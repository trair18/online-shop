package com.gmail.trair8.dao;


import com.gmail.trair8.entity.Order;

import com.gmail.trair8.exception.DAOException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderDAO extends AbstractDAO<Order>{

    private final static String SELECT_ALL_ORDERS =
            "SELECT * FROM orders";

    private final static String SELECT_ORDER_BY_ID_SQL =
            "SELECT * FROM orders WHERE id = ?";

    private final static String INSERT_USER_SQL =
            "INSERT INTO orders (User_id, Product_id, isActual, address, payment, time)" +
                    "VALUES (?, ?, ?, ?, ?, ?)";

    private static final String UPDATE_ORDER =
            "UPDATE orders SET User_id, Product_id = ?, isActual = ?, address = ?, payment = ?, time = ? WHERE id = ?";

    public OrderDAO(Connection connection) {
        super(connection);
    }

    @Override
    public List<Order> findAll() throws DAOException {
        try (PreparedStatement ps = connection.prepareStatement(SELECT_ALL_ORDERS)){
            List<Order> orders = new ArrayList<>();

            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                orders.add(makeEntity(rs));
            }
            return orders;
        }catch (SQLException e){
            throw new DAOException("Problem when trying to find all orders", e);
        }
    }

    @Override
    public Order findEntityById(int id) throws DAOException{
        try (PreparedStatement ps = connection.prepareStatement(SELECT_ORDER_BY_ID_SQL)){
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            rs.next();
            return makeEntity(rs);
        }catch (SQLException e){
            throw new DAOException("Problem when trying to find order by id", e);
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
        order.setTime(rs.getLong("time"));
        return order;
    }

    @Override
    public void insert(Order order) throws DAOException{
        try (PreparedStatement ps = connection.prepareStatement(INSERT_USER_SQL)){
            ps.setInt(1, order.getUserId());
            ps.setInt(2, order.getProductId());
            ps.setBoolean(3, order.isActual());
            ps.setString(4, order.getAddress());
            ps.setString(5, order.getPayment());
            ps.setLong(6, order.getTime());
            ps.executeUpdate();
        }catch (SQLException e){
            throw new DAOException("Problem when trying to insert order", e);
        }
    }

    @Override
    public void update(int id, Order order) throws DAOException{
        try (PreparedStatement ps = connection.prepareStatement(UPDATE_ORDER)) {
            ps.setInt(1, order.getUserId());
            ps.setInt(2, order.getProductId());
            ps.setBoolean(3, order.isActual());
            ps.setString(4, order.getAddress());
            ps.setString(5, order.getPayment());
            ps.setLong(6, order.getTime());
            ps.setInt(7, id);
            ps.executeUpdate();
        }catch (SQLException e){
            throw new DAOException("Problem when trying to update order by id", e);
        }
    }
}
