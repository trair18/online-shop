package com.gmail.trair8.controller.impl;

import com.gmail.trair8.controller.Controller;
import com.gmail.trair8.controller.RequestMappingClass;
import com.gmail.trair8.controller.RequestMappingMethod;
import com.gmail.trair8.entity.User;
import com.gmail.trair8.service.UserService;
import com.gmail.trair8.validator.Validator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@RequestMappingClass(path = "/user")
public class UserController implements Controller {

    private UserService userService = new UserService();

    @RequestMappingMethod(path = "/signin")
    public String signIn(HttpServletRequest request) {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        User user = userService.signIn(email, password);
        if (user != null) {
            request.setAttribute("isCorrect", true);
            HttpSession session = request.getSession(true);
            if (user.isAdmin()) {
                session.setAttribute("role", "admin");
            } else {
                session.setAttribute("role", "client");
            }
            session.setAttribute("id", user.getId());
        } else {
            request.setAttribute("isCorrect", false);
        }


        return "/jsp/enter.jsp";
    }

    @RequestMappingMethod(path = "/signupform")
    public String signUpForm(HttpServletRequest request) {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String firstName = request.getParameter("firstName");
        String surname = request.getParameter("surname");
        if (Validator.isValidEmail(email) && Validator.isValidPassword(password)) {

            User user = new User();
            user.setEmail(email);
            user.setPassword(password);
            user.setFirstName(firstName);
            user.setSurname(surname);

            if (userService.checkIsFreeEmail(email)) {
                userService.signUp(user);
                HttpSession session = request.getSession(true);
                session.setAttribute("role", "client");
                session.setAttribute("id", userService.findUserByEmail(email).getId());
                return Controllers.PRODUCT_CONTROLLER.findAll(request);
            } else {
                request.setAttribute("isFree", false);
            }

        } else {
            request.setAttribute("isValid", false);
        }
        return "/jsp/signup.jsp";
    }

    @RequestMappingMethod(path = "/signup")
    public String signUp(HttpServletRequest request) {
        return "/jsp/signup.jsp";
    }

    @RequestMappingMethod(path = "/signout")
    public String signOut(HttpServletRequest request) {
        HttpSession session = request.getSession(true);
        session.setAttribute("role", "guest");
        return Controllers.PRODUCT_CONTROLLER.findAll(request);
    }

    @RequestMappingMethod(path = "/langen")
    public String setEnLang(HttpServletRequest request) {
        HttpSession session = request.getSession(true);
        session.setAttribute("lang", "en");
        return Controllers.PRODUCT_CONTROLLER.findAll(request);
    }

    @RequestMappingMethod(path = "/langru")
    public String setRuLang(HttpServletRequest request) {
        HttpSession session = request.getSession(true);
        session.setAttribute("lang", "ru");
        return Controllers.PRODUCT_CONTROLLER.findAll(request);
    }

    @RequestMappingMethod(path = "/allUsers")
    public String showAllUsers(HttpServletRequest request) {
        if (request.getParameter("admin") != null) {
            Boolean admin = new Boolean(request.getParameter("admin"));
            Boolean blocked = new Boolean(request.getParameter("blocked"));
            Integer loyaltyPoints = new Integer(request.getParameter("loyaltyPoints"));
            Integer id = new Integer(request.getParameter("id"));

            User user = new User();
            user.setAdmin(admin);
            user.setBlocked(blocked);
            user.setLoyaltyPoints(loyaltyPoints);
            userService.updateUserFromAdmin(id, user);
        }

        List<User> users = userService.findAll();
        request.setAttribute("users", users);

        return "/jsp/allUsers.jsp";
    }

}