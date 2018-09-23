package com.gmail.trair8.controller;

import com.gmail.trair8.connectionpool.ConnectionPool;
import com.gmail.trair8.dao.UserDAO;
import com.gmail.trair8.entity.Product;
import com.gmail.trair8.entity.User;
import com.gmail.trair8.exception.ConnectionPoolException;
import com.gmail.trair8.exception.DAOException;
import com.gmail.trair8.model.Model;
import com.gmail.trair8.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserController implements Controller{

    private final static Logger LOGGER = LogManager.getLogger(UserController.class.getName());
    private UserService userService = new UserService();

    @RequestMapping(method = "get", path = "/user/list")
    public ModelAndView findAll(HttpServletRequest request){
        List<User> users = new ArrayList<>();
        Map<String, Object> map = new HashMap<>();
        try {
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
            ConnectionPool connectionPool = ConnectionPool.getInstance();
            Connection cn = connectionPool.takeConnection();

            UserDAO userDAO = new UserDAO(cn);
            users = userDAO.findAll();
            map.put("users", users);

            connectionPool.closeConnection(cn);
        } catch (SQLException e) {
            LOGGER.error(e);
        } catch (ConnectionPoolException e) {
            LOGGER.error(e);
        } catch (DAOException e) {
            LOGGER.error(e);
        }
        return new ModelAndView("/jsp/list.jsp", map);
    }

    @RequestMapping(method = "post", path = "/user/signin")
    public ModelAndView signIn(HttpServletRequest request){
        Map<String, Object> map = new HashMap<>();

        String email = request.getParameter("email");
        String password = request.getParameter("password");
        User user = new User();
        user.setEmail(email);
        user.setPassword(password);
        if (userService.checkSignIn(user)) {
            HttpSession session = request.getSession(true);
            session.setAttribute("role", "client");
            session.setAttribute("basket", new ArrayList<Integer>());
        }
        System.out.println(request.getRequestURI());
        System.out.println(request.getRequestURL());
        System.out.println(request.getContextPath());

        return new ModelAndView("/jsp/userpage.jsp", null);
    }

    @RequestMapping(method = "post", path = "/user/signupform")
    public ModelAndView signUpForm(HttpServletRequest request){
        Map<String, Object> map = new HashMap<>();

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
            map.put("user", user);
            userService.signUp(user);
            HttpSession session = request.getSession(true);
            session.setAttribute("role", "client");
            session.setAttribute("basket", new ArrayList<Integer>());
            return new ProductController().findAll(request);
        }

        return new ModelAndView("/jsp/signup.jsp", map);
    }

    @RequestMapping(method = "get", path = "/user/signup")
    public ModelAndView signUp(HttpServletRequest request) {
        return new ModelAndView("/jsp/signup.jsp", null);
    }

    @RequestMapping(method = "get", path = "/user/signout")
    public ModelAndView signOut(HttpServletRequest request) {
        HttpSession session = request.getSession(true);
        session.setAttribute("role", "guest");
        return new ProductController().findAll(request);
    }


}


