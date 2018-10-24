package com.gmail.trair8.dao;

import com.gmail.trair8.entity.Product;
import com.gmail.trair8.exception.OnlineShopException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO extends AbstractDAO<Product> {

    private static final Logger LOGGER = LogManager.getLogger(ProductDAO.class);

    private static final String SELECT_ALL_PRODUCTS =
            "SELECT * FROM products";

    private static final String SELECT_PRODUCT_BY_ID_SQL =
            "SELECT * FROM products WHERE id = ?";

    private static final String SELECT_PRODUCT_BY_CATEGORY_SQL =
            "SELECT * FROM products WHERE category = ?";


    private static final String UPDATE_PRODUCT =
            "UPDATE products SET name = ?, price = ?, rating = ?, inStock = ?, category = ?, img = ? WHERE id = ?";

    private static final String INSERT_PRODUCT_SQL =
            "INSERT INTO products (name, price, rating, inStock, img, category)" +
                    "VALUES (?, ?, ?, ?, ?, ?)";


    public ProductDAO(){
    }


    @Override
    public List<Product> findAll(Connection connection) {
        try (PreparedStatement ps = connection.prepareStatement(SELECT_ALL_PRODUCTS);
             ResultSet rs = ps.executeQuery()) {
            List<Product> products = new ArrayList<>();
            while (rs.next()) {
                products.add(makeEntity(rs));
            }
            return products;
        } catch (SQLException e) {
            LOGGER.error("Problem when trying to find all products");
            throw new OnlineShopException("Problem when trying to find all products", e);
        }
    }

    @Override
    public Product findEntityById(Connection connection, int id) {
        try (PreparedStatement ps = connection.prepareStatement(SELECT_PRODUCT_BY_ID_SQL)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                rs.next();
                return makeEntity(rs);
            }
        } catch (SQLException e) {
            LOGGER.error("Problem when trying to find product by id");
            throw new OnlineShopException("Problem when trying to find product by id", e);
        }
    }

    public List<Product> findEntityByCategory(Connection connection, String category) {
        try (PreparedStatement ps = connection.prepareStatement(SELECT_PRODUCT_BY_CATEGORY_SQL)) {
            ps.setString(1, category);
            List<Product> products = new ArrayList<>();
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    products.add(makeEntity(rs));
                }
                return products;
            }
        } catch (SQLException e) {
            LOGGER.error("Problem when trying to find product by category");
            throw new OnlineShopException("Problem when trying to find product by category", e);
        }
    }

    private Product makeEntity(ResultSet rs) throws SQLException {
        Product product = new Product();
        product.setId(rs.getInt("id"));
        product.setName(rs.getString("name"));
        product.setPrice(rs.getBigDecimal("price"));
        product.setRating(rs.getDouble("rating"));
        product.setInStock(rs.getBoolean("inStock"));
        product.setImg(rs.getString("img"));
        product.setCategory(rs.getString("category"));
        return product;
    }

    @Override
    public void update(Connection connection, int id, Product product) {
        try (PreparedStatement ps = connection.prepareStatement(UPDATE_PRODUCT)) {
            ps.setString(1, product.getName());
            ps.setBigDecimal(2, product.getPrice());
            ps.setDouble(3, product.getRating());
            ps.setBoolean(4, product.isInStock());
            ps.setString(5, product.getCategory());
            ps.setString(6, product.getImg());
            ps.setInt(7, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error("Problem when trying to update product by id");
            throw new OnlineShopException("Problem when trying to update product by id", e);
        }
    }

    @Override
    public void insert(Connection connection, Product product) {
        try (PreparedStatement ps = connection.prepareStatement(INSERT_PRODUCT_SQL)) {
            ps.setString(1, product.getName());
            ps.setBigDecimal(2, product.getPrice());
            ps.setDouble(3, product.getRating());
            ps.setBoolean(4, product.isInStock());
            ps.setString(5, product.getImg());
            ps.setString(6, product.getCategory());
            ps.executeUpdate();

        } catch (SQLException e) {
            LOGGER.error("Problem when trying to insert product");
            throw new OnlineShopException("Problem when trying to insert product", e);
        }
    }

}
