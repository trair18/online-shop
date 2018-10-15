package com.gmail.trair8.service;

import com.gmail.trair8.connectionpool.ConnectionPool;
import com.gmail.trair8.dao.OrderDAO;
import com.gmail.trair8.dao.UserDAO;
import com.gmail.trair8.entity.Order;
import com.gmail.trair8.entity.User;
import com.gmail.trair8.exception.ConnectionPoolException;
import com.gmail.trair8.exception.DAOException;
import com.gmail.trair8.hash.BCryptHash;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserService {

    private static final Logger LOGGER = LogManager.getLogger();

    public void signUp(User user){
        try {
            ConnectionPool connectionPool = ConnectionPool.getInstance();
            Connection cn = connectionPool.takeConnection();
            cn.setAutoCommit(false);

            UserDAO userDAO = new UserDAO(cn);
            userDAO.insert(user);

            cn.commit();

            connectionPool.closeConnection(cn);

        } catch (SQLException e) {
            LOGGER.error(e);
        } catch (ConnectionPoolException e) {
            LOGGER.error(e);
        } catch (DAOException e) {
            LOGGER.error(e);
        }
    }

    public boolean CheckIsFreeEmail(String email){
        try {
            ConnectionPool connectionPool = ConnectionPool.getInstance();
            Connection cn = connectionPool.takeConnection();

            UserDAO userDAO = new UserDAO(cn);
            List<User> users = userDAO.findAll();
            connectionPool.closeConnection(cn);

            for (User user: users){
                if (user.getEmail().equals(email)){
                    return false;
                }
            }
        } catch (ConnectionPoolException e) {
            LOGGER.error(e);
        } catch (DAOException e) {
            LOGGER.error(e);
        }
        return true;
    }

    public User signIn(String email, String password){
        try {

            ConnectionPool connectionPool = ConnectionPool.getInstance();
            Connection cn = connectionPool.takeConnection();
            UserDAO userDAO = new UserDAO(cn);
            User user = userDAO.findEntityByEmail(email);
            connectionPool.closeConnection(cn);
                if (user != null){
                    if (BCryptHash.checkPassword(password, user.getPassword())){
                        return user;
                    }
                }
        } catch (ConnectionPoolException e) {
            LOGGER.error(e);
        } catch (DAOException e) {
            LOGGER.error(e);
        }
        return null;
    }

    public int findId(String email){
        try {

            ConnectionPool connectionPool = ConnectionPool.getInstance();
            Connection cn = connectionPool.takeConnection();
            UserDAO userDAO = new UserDAO(cn);
            User user = userDAO.findEntityByEmail(email);
            connectionPool.closeConnection(cn);
            if (user != null){
                return user.getId();
            }
        } catch (ConnectionPoolException e) {
            LOGGER.error(e);
        } catch (DAOException e) {
            LOGGER.error(e);
        }
        throw new RuntimeException();
    }

    public List<User> findAll(){
        List<User> users = new ArrayList<>();
        try {
            ConnectionPool connectionPool = ConnectionPool.getInstance();
            Connection cn = connectionPool.takeConnection();

            UserDAO userDAO = new UserDAO(cn);
            users = userDAO.findAll();
            connectionPool.closeConnection(cn);
        } catch (ConnectionPoolException e) {
            LOGGER.error(e);
        } catch (DAOException e) {
            LOGGER.error(e);
        }
        return users;
    }


    public void updateUserFromAdmin(int id, User user){
        try {
            ConnectionPool connectionPool = ConnectionPool.getInstance();
            Connection cn = connectionPool.takeConnection();
            cn.setAutoCommit(false);

            UserDAO userDAO = new UserDAO(cn);
            userDAO.updateFromAdmin(id, user);

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

}
