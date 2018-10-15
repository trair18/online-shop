package com.gmail.trair8.service;

import com.gmail.trair8.connectionpool.ConnectionPool;
import com.gmail.trair8.dao.ProductDAO;
import com.gmail.trair8.entity.Product;
import com.gmail.trair8.exception.ConnectionPoolException;
import com.gmail.trair8.exception.DAOException;
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
        try {
            ConnectionPool connectionPool = ConnectionPool.getInstance();
            Connection cn = connectionPool.takeConnection();

            ProductDAO productDAO = new ProductDAO(cn);

            for (Integer i: list) {
                products.add(productDAO.findEntityById(i));
            }
            connectionPool.closeConnection(cn);

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
            ConnectionPool connectionPool = ConnectionPool.getInstance();
            Connection cn = connectionPool.takeConnection();

            ProductDAO productDAO = new ProductDAO(cn);
            products = productDAO.findAll();
            connectionPool.closeConnection(cn);
        } catch (ConnectionPoolException e) {
            LOGGER.error(e);
        } catch (DAOException e) {
            LOGGER.error(e);
        }
        return products;
    }

    public List<Product> findByCategory(String category){
        List<Product> products = new ArrayList<>();
        try {
            ConnectionPool connectionPool = ConnectionPool.getInstance();
            Connection cn = connectionPool.takeConnection();

            ProductDAO productDAO = new ProductDAO(cn);
            products = productDAO.findEntityByCategory(category);
            connectionPool.closeConnection(cn);
        } catch (ConnectionPoolException e) {
            LOGGER.error(e);
        } catch (DAOException e) {
            LOGGER.error(e);
        }
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

        } catch (ConnectionPoolException e) {
            LOGGER.error(e);
        }  catch (SQLException e) {
            LOGGER.error(e);
        } catch (DAOException e){
            System.out.println(e);
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

        } catch (ConnectionPoolException e) {
            LOGGER.error(e);
        }  catch (SQLException e) {
            LOGGER.error(e);
        } catch (DAOException e){
            System.out.println(e);
        }
    }

    public Product findProductById(int id){
        Product product = new Product();
        try {
            ConnectionPool connectionPool = ConnectionPool.getInstance();
            Connection cn = connectionPool.takeConnection();

            ProductDAO productDAO = new ProductDAO(cn);
            product = productDAO.findEntityById(id);
            connectionPool.closeConnection(cn);
        } catch (ConnectionPoolException e) {
            LOGGER.error(e);
        } catch (DAOException e) {
            LOGGER.error(e);
        }
        return product;
    }





}
