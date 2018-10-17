package com.gmail.trair8.service;

import com.gmail.trair8.connectionpool.ConnectionPool;
import com.gmail.trair8.dao.ProductDAO;
import com.gmail.trair8.entity.Product;
import com.gmail.trair8.exception.OnlineShopException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductService {

    private static final Logger LOGGER = LogManager.getLogger();

    public List<Product> findCartProd(List<Integer> list){
        List<Product> products = new ArrayList<>();

            ConnectionPool connectionPool = ConnectionPool.getInstance();
            Connection cn = connectionPool.takeConnection();

            ProductDAO productDAO = new ProductDAO(cn);

            for (Integer i: list) {
                products.add(productDAO.findEntityById(i));
            }
            connectionPool.closeConnection(cn);

        return products;
    }

    public List<Product> findAll(){
        List<Product> products;
            ConnectionPool connectionPool = ConnectionPool.getInstance();
            Connection cn = connectionPool.takeConnection();

            ProductDAO productDAO = new ProductDAO(cn);
            products = productDAO.findAll();
            connectionPool.closeConnection(cn);

        return products;
    }

    public List<Product> findByCategory(String category){
        List<Product> products;
            ConnectionPool connectionPool = ConnectionPool.getInstance();
            Connection cn = connectionPool.takeConnection();

            ProductDAO productDAO = new ProductDAO(cn);
            products = productDAO.findEntityByCategory(category);
            connectionPool.closeConnection(cn);

        return products;
    }

    public void addProduct(Product product){
        try {
            ConnectionPool connectionPool = ConnectionPool.getInstance();
            Connection cn = connectionPool.takeConnection();
            cn.setAutoCommit(false);

            ProductDAO productDAO = new ProductDAO(cn);
            productDAO.insert(product);

            cn.commit();
            connectionPool.closeConnection(cn);

        }  catch (SQLException e) {
            LOGGER.error(e);
            throw new OnlineShopException(e);
        }
    }


    public void updateProduct(int id, Product product){
        try {
            ConnectionPool connectionPool = ConnectionPool.getInstance();
            Connection cn = connectionPool.takeConnection();
            cn.setAutoCommit(false);

            ProductDAO productDAO = new ProductDAO(cn);
            productDAO.update(id ,product);

            cn.commit();
            connectionPool.closeConnection(cn);

        }  catch (SQLException e) {
            LOGGER.error(e);
            throw new OnlineShopException(e);
        }
    }

    public Product findProductById(int id){
        Product product;

            ConnectionPool connectionPool = ConnectionPool.getInstance();
            Connection cn = connectionPool.takeConnection();

            ProductDAO productDAO = new ProductDAO(cn);
            product = productDAO.findEntityById(id);
            connectionPool.closeConnection(cn);

        return product;
    }





}
