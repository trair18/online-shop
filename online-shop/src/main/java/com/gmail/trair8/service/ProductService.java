package com.gmail.trair8.service;

import com.gmail.trair8.connectionpool.ConnectionPool;
import com.gmail.trair8.controller.ModelAndView;
import com.gmail.trair8.controller.RequestMapping;
import com.gmail.trair8.dao.ProductDAO;
import com.gmail.trair8.dao.UserDAO;
import com.gmail.trair8.entity.Product;
import com.gmail.trair8.entity.User;
import com.gmail.trair8.exception.ConnectionPoolException;
import com.gmail.trair8.exception.DAOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductService {

    private static final Logger LOGGER = LogManager.getLogger();

    public List<Product> findBasketProd(List<Integer> list){
        List<Product> products = new ArrayList<>();
        try {
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
            ConnectionPool connectionPool = ConnectionPool.getInstance();
            Connection cn = connectionPool.takeConnection();

            ProductDAO productDAO = new ProductDAO(cn);

            for (Integer i: list) {
                products.add(productDAO.findEntityById(i));
            }
            connectionPool.closeConnection(cn);

        } catch (SQLException e) {
            LOGGER.error(e);
        } catch (ConnectionPoolException e) {
            LOGGER.error(e);
        } catch (DAOException e) {
            LOGGER.error(e);
        }
        return products;
    }

    public List<Product> findAll(){
        List<Product> products = new ArrayList<>();
        try {
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
            ConnectionPool connectionPool = ConnectionPool.getInstance();
            Connection cn = connectionPool.takeConnection();

            ProductDAO productDAO = new ProductDAO(cn);
            products = productDAO.findAll();
            connectionPool.closeConnection(cn);
        } catch (SQLException e) {
            LOGGER.error(e);
        } catch (ConnectionPoolException e) {
            LOGGER.error(e);
        } catch (DAOException e) {
            LOGGER.error(e);
        }
        return products;
    }


}
