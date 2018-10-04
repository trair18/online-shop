package com.gmail.trair8.service;

import com.gmail.trair8.connectionpool.ConnectionPool;
import com.gmail.trair8.dao.UserDAO;
import com.gmail.trair8.entity.User;
import com.gmail.trair8.exception.ConnectionPoolException;
import com.gmail.trair8.exception.DAOException;
import com.gmail.trair8.hash.BCryptHash;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
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

    public boolean checkSignIn(User curUser){
        try {

            ConnectionPool connectionPool = ConnectionPool.getInstance();
            Connection cn = connectionPool.takeConnection();
            List<User> users;
            UserDAO userDAO = new UserDAO(cn);
            users = userDAO.findAll();
            connectionPool.closeConnection(cn);

            for (User user: users) {
                if (user.getEmail().equals(curUser.getEmail())){
                    if (BCryptHash.checkPassword(curUser.getPassword(), user.getPassword())){
                        return true;
                    }
                }
            }
        } catch (ConnectionPoolException e) {
            LOGGER.error(e);
        } catch (DAOException e) {
            LOGGER.error(e);
        }
        return false;
    }


}
