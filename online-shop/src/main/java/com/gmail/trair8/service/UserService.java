package com.gmail.trair8.service;

import com.gmail.trair8.dao.UserDAO;
import com.gmail.trair8.entity.User;
import com.gmail.trair8.hash.BCryptHash;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

import static com.gmail.trair8.util.ConnectionUtils.modifyOperations;
import static com.gmail.trair8.util.ConnectionUtils.readOperations;

public class UserService {

    private static final Logger LOGGER = LogManager.getLogger(UserService.class);

    private UserDAO userDAO = new UserDAO();

    public void signUp(User user) {
        modifyOperations(connection -> userDAO.insert(connection, user));
    }

    public boolean checkIsFreeEmail(String email) {
        User user = readOperations(connection -> userDAO.findEntityByEmail(connection, email));
        return user == null;
    }

    public User signIn(String email, String password) {
        User user = readOperations(connection -> userDAO.findEntityByEmail(connection, email));
        if (user != null && BCryptHash.checkPassword(password, user.getPassword())) {
            return user;
        }
        return null;
    }

    public User findUserByEmail(String email) {
        return readOperations(connection -> userDAO.findEntityByEmail(connection, email));
    }

    public User findUserById(int id) {
        return readOperations(connection -> userDAO.findEntityById(connection, id));
    }

    public List<User> findAll() {
        return readOperations(connection -> userDAO.findAll(connection));
    }

    public void updateUserFromAdmin(int id, User user) {
        modifyOperations(connection -> userDAO.updateFromAdmin(connection, id, user));
    }

    public void updateUserFromUser(int id, User user) {
        modifyOperations(connection -> userDAO.updateFromUser(connection, id, user));
    }

}
