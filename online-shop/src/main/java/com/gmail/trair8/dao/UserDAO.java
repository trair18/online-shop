package com.gmail.trair8.dao;

import com.gmail.trair8.entity.User;
import com.gmail.trair8.exception.OnlineShopException;
import com.gmail.trair8.hash.BCryptHash;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class UserDAO extends AbstractDAO<User> {

    private static final Logger LOGGER = LogManager.getLogger(UserDAO.class);

    private static final String INSERT_USER_SQL =
            "INSERT INTO users (email, password, isAdmin, firstName, surname, account, loyaltyPoints, isBlocked)" +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String SELECT_USER_BY_ID_SQL =
            "SELECT * FROM users WHERE id = ?";
    private static final String SELECT_USER_BY_EMAIL =
            "SELECT * FROM users WHERE email = ?";
    private static final String SELECT_ALL_USERS =
            "SELECT * FROM users";
    private static final String UPDATE_USER =
            "UPDATE users SET email = ?, password = ?, firstName = ?, surname = ?, account = ?, loyaltyPoints = ?, isBlocked = ? WHERE id = ?";

    private static final String UPDATE_USER_FROM_ADMIN =
            "UPDATE users SET isAdmin = ?, loyaltyPoints = ?, isBlocked = ? WHERE id = ?";

    private static final String UPDATE_USER_FROM_USER =
            "UPDATE users SET surname = ?, firstName = ?, email = ? WHERE id = ?";

    public UserDAO() {
    }

    @Override
    public List<User> findAll(Connection connection) {
        try (PreparedStatement ps = connection.prepareStatement(SELECT_ALL_USERS);
             ResultSet rs = ps.executeQuery()) {
            List<User> clients = new ArrayList<>();
            while (rs.next()) {
                clients.add(makeEntity(rs));
            }
            return clients;
        } catch (SQLException e) {
            LOGGER.error("Problem when trying to find all users");
            throw new OnlineShopException("Problem when trying to find all users", e);
        }
    }

    @Override
    public User findEntityById(Connection connection, int id) {
        try (PreparedStatement ps = connection.prepareStatement(SELECT_USER_BY_ID_SQL)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                rs.next();
                return makeEntity(rs);
            }
        } catch (SQLException e) {
            LOGGER.error("Problem when trying to find user by id");
            throw new OnlineShopException("Problem when trying to find user by id", e);
        }
    }

    public User findEntityByEmail(Connection connection, String email) {
        try (PreparedStatement ps = connection.prepareStatement(SELECT_USER_BY_EMAIL)) {
            ps.setString(1, email);
            try (ResultSet rs = ps.executeQuery()) {
                if (!rs.next()) {
                    return null;
                }
                return makeEntity(rs);
            }
        } catch (SQLException e) {
            LOGGER.error("Problem when trying to find user by email");
            throw new OnlineShopException("Problem when trying to find user by email", e);
        }
    }


    private User makeEntity(ResultSet rs) throws SQLException {
        User client = new User();
        client.setId(rs.getInt("id"));
        client.setEmail(rs.getString("email"));
        client.setPassword(rs.getString("password"));
        client.setFirstName(rs.getString("firstName"));
        client.setSurname(rs.getString("surname"));
        client.setAccount(rs.getBigDecimal("account"));
        client.setLoyaltyPoints(rs.getInt("loyaltyPoints"));
        client.setBlocked(rs.getBoolean("isBlocked"));
        client.setAdmin(rs.getBoolean("isAdmin"));
        return client;
    }

    @Override
    public void insert(Connection connection, User user) {
        try (PreparedStatement ps = connection.prepareStatement(INSERT_USER_SQL)) {
            ps.setString(1, user.getEmail());
            ps.setString(2, BCryptHash.hashPassword(user.getPassword()));
            ps.setBoolean(3, user.isAdmin());
            ps.setString(4, user.getFirstName());
            ps.setString(5, user.getSurname());
            ps.setBigDecimal(6, user.getAccount());
            ps.setInt(7, user.getLoyaltyPoints());
            ps.setBoolean(8, user.isBlocked());
            ps.executeUpdate();

        } catch (SQLException e) {
            LOGGER.error("Problem when trying to insert user");
            throw new OnlineShopException("Problem when trying to insert user", e);
        }
    }

    @Override
    public void update(Connection connection, int id, User user) {
        try (PreparedStatement ps = connection.prepareStatement(UPDATE_USER)) {
            ps.setString(1, user.getEmail());
            ps.setString(2, BCryptHash.hashPassword(user.getPassword()));
            ps.setString(3, user.getFirstName());
            ps.setString(4, user.getSurname());
            ps.setBigDecimal(5, user.getAccount());
            ps.setInt(6, user.getLoyaltyPoints());
            ps.setBoolean(7, user.isBlocked());
            ps.setInt(8, id);
            ps.executeUpdate();

        } catch (SQLException e) {
            LOGGER.error("Problem when trying to update user by id");
            throw new OnlineShopException("Problem when trying to update user by id", e);
        }
    }

    public void updateFromAdmin(Connection connection, int id, User user) {
        try (PreparedStatement ps = connection.prepareStatement(UPDATE_USER_FROM_ADMIN)) {
            ps.setBoolean(1, user.isAdmin());
            ps.setInt(2, user.getLoyaltyPoints());
            ps.setBoolean(3, user.isBlocked());
            ps.setInt(4, id);
            ps.executeUpdate();

        } catch (SQLException e) {
            LOGGER.error("Problem when trying to update user by id");
            throw new OnlineShopException("Problem when trying to update user by id", e);
        }
    }

    public void updateFromUser(Connection connection, int id, User user) {
        try (PreparedStatement ps = connection.prepareStatement(UPDATE_USER_FROM_USER)) {
            ps.setString(1, user.getSurname());
            ps.setString(2, user.getFirstName());
            ps.setString(3, user.getEmail());
            ps.setInt(4, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error("Problem when trying to update user by id");
            throw new OnlineShopException("Problem when trying to update user by id", e);
        }
    }


}
