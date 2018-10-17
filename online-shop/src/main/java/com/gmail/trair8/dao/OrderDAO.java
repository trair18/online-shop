package com.gmail.trair8.dao;


import com.gmail.trair8.entity.Order;
import com.gmail.trair8.exception.OnlineShopException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OrderDAO extends AbstractDAO<Order>{

    private final static String SELECT_ALL_ORDERS =
            "SELECT * FROM orders";

    private final static String SELECT_ORDER_BY_ID_SQL =
            "SELECT * FROM orders WHERE id = ?";

    private final static String INSERT_ORDER_SQL =
            "INSERT INTO orders (User_id, Product_id, isActual, address, payment, time)" +
                    "VALUES (?, ?, ?, ?, ?, ?)";

    private static final String UPDATE_ORDER =
            "UPDATE orders SET User_id, Product_id = ?, isActual = ?, address = ?, payment = ?, time = ? WHERE id = ?";

    private static final String UPDATE_ACTUAL =
            "UPDATE orders SET isActual = ? WHERE id = ?";


    private final static String SELECT_ORDERS_BY_USER_ID_SQL =
            "SELECT * FROM orders WHERE User_id = ?";

    public OrderDAO(Connection connection) {
        super(connection);
    }

    @Override
    public List<Order> findAll(){
        try (PreparedStatement ps = connection.prepareStatement(SELECT_ALL_ORDERS)){
            List<Order> orders = new ArrayList<>();

            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                orders.add(makeEntity(rs));
            }
            return orders;
        }catch (SQLException e){
            throw new OnlineShopException("Problem when trying to find all orders", e);
        }
    }

    @Override
    public Order findEntityById(int id){
        try (PreparedStatement ps = connection.prepareStatement(SELECT_ORDER_BY_ID_SQL)){
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            rs.next();
            return makeEntity(rs);
        }catch (SQLException e){
            throw new OnlineShopException("Problem when trying to find order by id", e);
        }
    }

    public List<Order> findOrderByUserId(int userId){
        try (PreparedStatement ps = connection.prepareStatement(SELECT_ORDERS_BY_USER_ID_SQL)){
            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();
            List<Order> orderList = new ArrayList<Order>();
            while (rs.next()){
                orderList.add(makeEntity(rs));
            }
            return orderList;
        }catch (SQLException e){
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
    public void insert(Order order){
        try (PreparedStatement ps = connection.prepareStatement(INSERT_ORDER_SQL)){
            ps.setInt(1, order.getUserId());
            ps.setInt(2, order.getProductId());
            ps.setBoolean(3, order.isActual());
            ps.setString(4, order.getAddress());
            ps.setString(5, order.getPayment());
            ps.setLong(6, order.getTime().getTime());
            ps.executeUpdate();
        }catch (SQLException e){
            throw new OnlineShopException(e);
        }
    }

    @Override
    public void update(int id, Order order){
        try (PreparedStatement ps = connection.prepareStatement(UPDATE_ORDER)) {
            ps.setInt(1, order.getUserId());
            ps.setInt(2, order.getProductId());
            ps.setBoolean(3, order.isActual());
            ps.setString(4, order.getAddress());
            ps.setString(5, order.getPayment());
            ps.setLong(6, order.getTime().getTime());
            ps.setInt(7, id);
            ps.executeUpdate();
        }catch (SQLException e){
            throw new OnlineShopException("Problem when trying to update order by id", e);
        }
    }


    public void updateActual(int id, boolean actual){
        try (PreparedStatement ps = connection.prepareStatement(UPDATE_ACTUAL)) {
            ps.setBoolean(1, actual);
            ps.setInt(2, id);
            ps.executeUpdate();
        }catch (SQLException e){
            throw new OnlineShopException(e);
        }
    }



}
