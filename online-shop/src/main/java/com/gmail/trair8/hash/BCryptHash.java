package com.gmail.trair8.hash;

import org.mindrot.jbcrypt.BCrypt;

public class BCryptHash {

    private static int workload = 12;

    public static String hashPassword(String passwordPlaintext) {
        String salt = BCrypt.gensalt(workload);
        String hashedPassword = BCrypt.hashpw(passwordPlaintext, salt);

        return (hashedPassword);
    }

    public static boolean checkPassword(String passwordPlaintext, String storedHash) {
        boolean passwordVerified = false;

        if (null == storedHash || !storedHash.startsWith("$2a$")) {
            throw new IllegalArgumentException("Invalid hash provided for comparison");
        }
        passwordVerified = BCrypt.checkpw(passwordPlaintext, storedHash);

        return (passwordVerified);
    }
}
