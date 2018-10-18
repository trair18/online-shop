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

    private static final Logger LOGGER = LogManager.getLogger(ProductService.class);

    public List<Product> findCartProd(List<Integer> list) {
        try (Connection cn = ConnectionPool.getInstance().takeConnection()) {
            List<Product> products = new ArrayList<>();
            ProductDAO productDAO = new ProductDAO(cn);
            for (Integer i : list) {
                products.add(productDAO.findEntityById(i));
            }
            return products;
        } catch (SQLException e) {
            LOGGER.error(e);
            throw new OnlineShopException(e);
        }
    }

    public List<Product> findAll() {
        try (Connection cn = ConnectionPool.getInstance().takeConnection()) {
            ProductDAO productDAO = new ProductDAO(cn);
            return productDAO.findAll();
        } catch (SQLException e) {
            LOGGER.error(e);
            throw new OnlineShopException(e);
        }
    }

    public List<Product> findByCategory(String category) {
        try (Connection cn = ConnectionPool.getInstance().takeConnection()) {
            ProductDAO productDAO = new ProductDAO(cn);
            return productDAO.findEntityByCategory(category);
        } catch (SQLException e) {
            LOGGER.error(e);
            throw new OnlineShopException(e);
        }

    }

    public void addProduct(Product product) {
        try (Connection cn = ConnectionPool.getInstance().takeConnection()) {
            cn.setAutoCommit(false);

            ProductDAO productDAO = new ProductDAO(cn);
            productDAO.insert(product);

            cn.commit();
        } catch (SQLException e) {
            LOGGER.error(e);
            throw new OnlineShopException(e);
        }
    }


    public void updateProduct(int id, Product product) {
        try (Connection cn = ConnectionPool.getInstance().takeConnection()) {
            cn.setAutoCommit(false);

            ProductDAO productDAO = new ProductDAO(cn);
            productDAO.update(id, product);

            cn.commit();
        } catch (SQLException e) {
            LOGGER.error(e);
            throw new OnlineShopException(e);
        }
    }

    public Product findProductById(int id) {
        try (Connection cn = ConnectionPool.getInstance().takeConnection()) {
            ProductDAO productDAO = new ProductDAO(cn);
            return productDAO.findEntityById(id);
        } catch (SQLException e) {
            LOGGER.error(e);
            throw new OnlineShopException(e);
        }
    }

}
