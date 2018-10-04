package com.gmail.trair8.dao;

import com.gmail.trair8.entity.Product;
import com.gmail.trair8.entity.User;
import com.gmail.trair8.exception.DAOException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO extends AbstractDAO<Product>{

    private final static String SELECT_ALL_PRODUCTS =
            "SELECT * FROM products";

    private final static String SELECT_PRODUCT_BY_ID_SQL =
            "SELECT * FROM products WHERE id = ?";

    private static final String UPDATE_PRODUCT =
            "UPDATE products SET name = ?, price = ?, rating = ?, inStock = ? WHERE id = ?";

    private final static String INSERT_PRODUCT_SQL =
            "INSERT INTO products (name, price, rating, inStock, img)" +
                    "VALUES (?, ?, ?, ?, ?)";


    public ProductDAO(Connection connection) {
        super(connection);
    }



    @Override
    public List<Product> findAll() throws DAOException{
        try (PreparedStatement ps = connection.prepareStatement(SELECT_ALL_PRODUCTS)){
            List<Product> products = new ArrayList<>();

            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                products.add(makeEntity(rs));
            }
            return products;
        }catch (SQLException e){
            throw new DAOException("Problem when trying to find all products", e);
        }
    }

    @Override
    public Product findEntityById(int id) throws DAOException{
        try (PreparedStatement ps = connection.prepareStatement(SELECT_PRODUCT_BY_ID_SQL)){
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            rs.next();
            return makeEntity(rs);
        }catch (SQLException e){
            throw new DAOException("Problem when trying to find product by id", e);
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
        return product;
    }

    @Override
    public void update(int id, Product product) throws DAOException{
        try (PreparedStatement ps = connection.prepareStatement(UPDATE_PRODUCT)) {
            ps.setString(1, product.getName());
            ps.setBigDecimal(2, product.getPrice());
            ps.setDouble(3, product.getRating());
            ps.setBoolean(4, product.isInStock());
            ps.setInt(5, id);
            ps.executeUpdate();
        }catch (SQLException e){
            throw new DAOException("Problem when trying to update product by id", e);
        }
    }

    @Override
    public void insert(Product product) throws DAOException{
        try (PreparedStatement ps = connection.prepareStatement(INSERT_PRODUCT_SQL)){
            ps.setString(1, product.getName());
            ps.setBigDecimal(2, product.getPrice());
            ps.setDouble(3, product.getRating());
            ps.setBoolean(4, product.isInStock());
            ps.setString(5, product.getImg());
            ps.executeUpdate();

        }catch (SQLException e){
            throw new DAOException("Problem when trying to insert product", e);
        }
    }

}
