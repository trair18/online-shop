package com.gmail.trair8.dao;

import com.gmail.trair8.entity.Review;
import com.gmail.trair8.exception.OnlineShopException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReviewDAO extends AbstractDAO<Review> {

    private static final Logger LOGGER = LogManager.getLogger(ReviewDAO.class);

    private static final String SELECT_ALL_REVIEWS =
            "SELECT * FROM reviews";

    private static final String SELECT_REVIEW_BY_ID_SQL =
            "SELECT * FROM reviews WHERE id = ?";

    private static final String INSERT_REVIEW_SQL =
            "INSERT INTO reviews (User_id, Product_id, text)" +
                    "VALUES (?, ?, ?)";

    private static final String UPDATE_REVIEW =
            "UPDATE reviews SET User_id = ?, Product_id = ?, text = ? WHERE id = ?";


    public ReviewDAO(){
    }

    @Override
    public List<Review> findAll(Connection connection) {
        try (PreparedStatement ps = connection.prepareStatement(SELECT_ALL_REVIEWS);
             ResultSet rs = ps.executeQuery()) {
            List<Review> reviews = new ArrayList<>();
            while (rs.next()) {
                reviews.add(makeEntity(rs));
            }
            return reviews;
        } catch (SQLException e) {
            LOGGER.error("Problem when trying to find all reviews");
            throw new OnlineShopException("Problem when trying to find all reviews", e);
        }
    }

    @Override
    public Review findEntityById(Connection connection,int id) {
        try (PreparedStatement ps = connection.prepareStatement(SELECT_REVIEW_BY_ID_SQL)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                rs.next();
                return makeEntity(rs);
            }
        } catch (SQLException e) {
            LOGGER.error("Problem when trying to find review by id");
            throw new OnlineShopException("Problem when trying to find review by id", e);
        }
    }


    private Review makeEntity(ResultSet rs) throws SQLException {
        Review review = new Review();
        review.setId(rs.getInt("id"));
        review.setUserId(rs.getInt("User_id"));
        review.setProductId(rs.getInt("Product_id"));
        review.setText(rs.getString("text"));
        return review;
    }


    @Override
    public void insert(Connection connection, Review review) {
        try (PreparedStatement ps = connection.prepareStatement(INSERT_REVIEW_SQL)) {
            ps.setInt(1, review.getUserId());
            ps.setInt(2, review.getProductId());
            ps.setString(3, review.getText());
            ps.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error("Problem when trying to insert review");
            throw new OnlineShopException("Problem when trying to insert review", e);
        }
    }

    @Override
    public void update(Connection connection, int id, Review review) {
        try (PreparedStatement ps = connection.prepareStatement(UPDATE_REVIEW)) {
            ps.setInt(1, review.getUserId());
            ps.setInt(2, review.getProductId());
            ps.setString(3, review.getText());
            ps.setInt(4, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error("Problem when trying to update review by id");
            throw new OnlineShopException("Problem when trying to update review by id", e);
        }
    }
}
