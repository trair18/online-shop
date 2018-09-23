package com.gmail.trair8.connectionpool;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connector {
    public  static Connection getConnection() throws SQLException {

        return DriverManager.getConnection("jdbc:mysql://localhost:3306/cafe?autoReconnect=true&useSSL=false", "root", "artem7102565");
    }
}
