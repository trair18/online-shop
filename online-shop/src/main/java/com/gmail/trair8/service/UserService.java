package com.gmail.trair8.service;

import com.gmail.trair8.connectionpool.ConnectionPool;
import com.gmail.trair8.dao.UserDAO;
import com.gmail.trair8.entity.User;
import com.gmail.trair8.exception.OnlineShopException;
import com.gmail.trair8.hash.BCryptHash;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class UserService {

    private static final Logger LOGGER = LogManager.getLogger(UserService.class);

    public void signUp(User user) {
        try (Connection cn = ConnectionPool.getInstance().takeConnection()){
            cn.setAutoCommit(false);

            UserDAO userDAO = new UserDAO(cn);
            userDAO.insert(user);

            cn.commit();
        } catch (SQLException e) {
            LOGGER.error(e);
            throw new OnlineShopException(e);
        }
    }

    public boolean checkIsFreeEmail(String email) {
        try(Connection cn = ConnectionPool.getInstance().takeConnection()){
            UserDAO userDAO = new UserDAO(cn);
            User user = userDAO.findEntityByEmail(email);
            return user == null;
        }catch (SQLException e){
            LOGGER.error(e);
            throw new OnlineShopException(e);
        }

    }

    public User signIn(String email, String password) {

        try(Connection cn = ConnectionPool.getInstance().takeConnection()) {
            UserDAO userDAO = new UserDAO(cn);
            User user = userDAO.findEntityByEmail(email);
            if (user != null) {
                if (BCryptHash.checkPassword(password, user.getPassword())) {
                    return user;
                }
            }
            return null;
        }catch (SQLException e){
            LOGGER.error(e);
            throw new OnlineShopException(e);
        }
    }

    public User findUserByEmail(String email) {
        try(Connection cn = ConnectionPool.getInstance().takeConnection()) {
            UserDAO userDAO = new UserDAO(cn);
            return userDAO.findEntityByEmail(email);
        }catch (SQLException e){
            LOGGER.error(e);
            throw new OnlineShopException(e);
        }

    }

    public List<User> findAll() {
        try(Connection cn = ConnectionPool.getInstance().takeConnection()) {
            UserDAO userDAO = new UserDAO(cn);
            return userDAO.findAll();
        }catch (SQLException e){
            LOGGER.error(e);
            throw new OnlineShopException(e);
        }
    }


    public void updateUserFromAdmin(int id, User user) {
        try (Connection cn = ConnectionPool.getInstance().takeConnection()) {
            cn.setAutoCommit(false);

            UserDAO userDAO = new UserDAO(cn);
            userDAO.updateFromAdmin(id, user);

            cn.commit();
        } catch (SQLException e) {
            LOGGER.error(e);
            throw new OnlineShopException(e);
        }
    }

}
