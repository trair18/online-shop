package com.gmail.trair8.service;

import com.gmail.trair8.dao.ProductDAO;
import com.gmail.trair8.entity.Product;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

import static com.gmail.trair8.util.ConnectionUtils.modifyOperations;
import static com.gmail.trair8.util.ConnectionUtils.readOperations;

public class ProductService {

    private static final Logger LOGGER = LogManager.getLogger(ProductService.class);

    private ProductDAO productDAO = new ProductDAO();

    public List<Product> findCartProd(List<Integer> list) {
        List<Product> products = new ArrayList<>();
        for (Integer i : list) {
            products.add(readOperations(connection -> productDAO.findEntityById(connection, i)));
        }
        return products;
    }

    public List<Product> findAll() {
        return readOperations(connection -> productDAO.findAll(connection));
    }

    public List<Product> findByCategory(String category) {
        return readOperations(connection -> productDAO.findEntityByCategory(connection, category));
    }

    public void addProduct(Product product) {
        modifyOperations(connection -> productDAO.insert(connection, product));
    }


    public void updateProduct(int id, Product product) {
        modifyOperations(connection -> productDAO.update(connection, id, product));
    }

    public Product findProductById(int id) {
        return readOperations(connection -> productDAO.findEntityById(connection, id));
    }

}
