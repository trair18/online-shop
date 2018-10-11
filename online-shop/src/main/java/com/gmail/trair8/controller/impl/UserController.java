package com.gmail.trair8.controller.impl;

import com.gmail.trair8.connectionpool.ConnectionPool;
import com.gmail.trair8.controller.Controller;
import com.gmail.trair8.controller.ControllerA;
import com.gmail.trair8.controller.RequestMapping;
import com.gmail.trair8.dao.UserDAO;
import com.gmail.trair8.entity.User;
import com.gmail.trair8.exception.ConnectionPoolException;
import com.gmail.trair8.exception.DAOException;
import com.gmail.trair8.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@ControllerA(path = "/user")
public class UserController implements Controller {

    private final static Logger LOGGER = LogManager.getLogger(UserController.class.getName());
    private UserService userService = new UserService();

    @RequestMapping(path = "/list")
    public String findAll(HttpServletRequest request){
        List<User> users = new ArrayList<>();
        try {
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
            ConnectionPool connectionPool = ConnectionPool.getInstance();
            Connection cn = connectionPool.takeConnection();
            UserDAO userDAO = new UserDAO(cn);
            users = userDAO.findAll();
            request.setAttribute("users", users);

            connectionPool.closeConnection(cn);
        } catch (SQLException e) {
            LOGGER.error(e);
        } catch (ConnectionPoolException e) {
            LOGGER.error(e);
        } catch (DAOException e) {
            LOGGER.error(e);
        }
        return "/jsp/list.jsp";
    }

    @RequestMapping(path = "/signin")
    public String signIn(HttpServletRequest request){
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        User user = new User();
        user.setEmail(email);
        user.setPassword(password);
        if (userService.checkSignIn(user)) {
            request.setAttribute("isCorrect", true);
            HttpSession session = request.getSession(true);
            session.setAttribute("role", "client");

        }else {
            request.setAttribute("isCorrect", false);
        }


        return "/jsp/userpage.jsp";
    }

    @RequestMapping(path = "/signupform")
    public String signUpForm(HttpServletRequest request){
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String firstName = request.getParameter("firstName");
        String surname = request.getParameter("surname");

        User user = new User();
        user.setEmail(email);
        user.setPassword(password);
        user.setFirstName(firstName);
        user.setSurname(surname);

        if (userService.CheckIsFreeEmail(email)){
            userService.signUp(user);
            HttpSession session = request.getSession(true);
            session.setAttribute("role", "client");
            session.setAttribute("basket", new ArrayList<Integer>());
            return new ProductController().findAll(request);
        }else{
            request.setAttribute("isFree", false);
        }

        return "/jsp/signup.jsp";
    }

    @RequestMapping(path = "/signup")
    public String signUp(HttpServletRequest request) {
        return "/jsp/signup.jsp";
    }

    @RequestMapping(path = "/signout")
    public String signOut(HttpServletRequest request) {
        HttpSession session = request.getSession(true);
        session.setAttribute("role", "guest");
        return Controllers.PRODUCT_CONTROLLER.findAll(request);
    }

    @RequestMapping(path = "/langen")
    public String setEnLang(HttpServletRequest request){
        HttpSession session = request.getSession(true);
        session.setAttribute("lang", "en");
        return Controllers.PRODUCT_CONTROLLER.findAll(request);
    }

    @RequestMapping(path = "/langru")
    public String setRuLang(HttpServletRequest request){
        HttpSession session = request.getSession(true);
        session.setAttribute("lang", "ru");
        return Controllers.PRODUCT_CONTROLLER.findAll(request);
    }

}


