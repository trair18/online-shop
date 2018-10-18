package com.gmail.trair8.validator;

public class Validator {

    public static boolean isValidEmail(String email) {
        return email.matches("\\w+([\\.-]?\\w+)*@\\w+([\\.-]?\\w+)*(\\.\\w{2,3})+");
    }

    public static boolean isValidPassword(String pass) {
        return pass.matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{6,25}$");
    }

}
