package com.gmail.trair8.dao;

import com.gmail.trair8.hash.BCryptHash;
import com.gmail.trair8.entity.User;
import com.gmail.trair8.exception.DAOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class UserDAO extends AbstractDAO<User>{

    private final static Logger LOGGER = LogManager.getLogger();

    private final static String INSERT_USER_SQL =
            "INSERT INTO users (email, password, isAdmin, firstName, surname, account, loyaltyPoints, isBlocked)" +
            "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
    private final static String SELECT_USER_BY_ID_SQL =
            "SELECT * FROM users WHERE id = ?";
    private final static String SELECT_ALL_USERS =
            "SELECT * FROM users";
    private static final String UPDATE_USER =
            "UPDATE users SET email = ?, password = ?, firstName = ?, surname = ?, account = ?, loyaltyPoints = ?, isBlocked = ? WHERE id = ?";


    public UserDAO(Connection connection) {
        super(connection);
    }

    @Override
    public List<User> findAll() throws DAOException{
        try (PreparedStatement ps = connection.prepareStatement(SELECT_ALL_USERS)){
            List<User> clients = new ArrayList<>();

            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                clients.add(makeEntity(rs));
            }
            return clients;
        }catch (SQLException e){
            throw new DAOException("Problem when trying to find all users", e);
        }
    }

    @Override
    public User findEntityById(int id) throws DAOException{

        try (PreparedStatement ps = connection.prepareStatement(SELECT_USER_BY_ID_SQL)){
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            rs.next();
            return makeEntity(rs);
        }catch (SQLException e){
            throw new DAOException("Problem when trying to find user by id", e);
        }
    }

    private User makeEntity(ResultSet rs) throws SQLException {
        User client = new User();
        client.setId(rs.getInt("id"));
        client.setEmail(rs.getString("email"));
        client.setPassword(rs.getString("password"));
        client.setFirstName(rs.getString("firstName"));
        client.setSurname(rs.getString("surname"));
        client.setAccount(rs.getString("account"));
        client.setLoyaltyPoints(rs.getInt("loyaltyPoints"));
        client.setBlocked(rs.getBoolean("isBlocked"));
        client.setAdmin(rs.getBoolean("isAdmin"));
        return client;
    }

    @Override
    public void insert(User user) throws DAOException{
        try (PreparedStatement ps = connection.prepareStatement(INSERT_USER_SQL)){
            ps.setString(1, user.getEmail());
            ps.setString(2, BCryptHash.hashPassword(user.getPassword()));
            ps.setBoolean(3, user.isAdmin());
            ps.setString(4, user.getFirstName());
            ps.setString(5, user.getSurname());
            ps.setString(6, user.getAccount());
            ps.setInt(7, user.getLoyaltyPoints());
            ps.setBoolean(8, user.isBlocked());
            ps.executeUpdate();

        }catch (SQLException e){
            throw new DAOException("Problem when trying to insert user", e);
        }
    }

    @Override
    public void update(int id, User user) throws DAOException{
        try (PreparedStatement ps = connection.prepareStatement(UPDATE_USER)) {
            ps.setString(1, user.getEmail());
            ps.setString(2, BCryptHash.hashPassword(user.getPassword()));
            ps.setString(3, user.getFirstName());
            ps.setString(4, user.getSurname());
            ps.setString(5, user.getAccount());
            ps.setInt(6, user.getLoyaltyPoints());
            ps.setBoolean(7, user.isBlocked());
            ps.setInt(8, id);
            ps.executeUpdate();

        }catch (SQLException e){
            throw new DAOException("Problem when trying to update user by id", e);
        }
    }
}
