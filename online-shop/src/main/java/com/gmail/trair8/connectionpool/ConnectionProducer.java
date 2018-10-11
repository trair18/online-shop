package com.gmail.trair8.connectionpool;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;



public class ConnectionProducer {

    private final static Logger LOGGER = LogManager.getLogger(ConnectionProducer.class);

    private Properties configProp;

    private static final String URL = "jdbc:mysql://localhost:3306/cafe?autoReconnect=true&useSSL=false";
    private static final String USER = "root";
    private static final String PASSWORD = "artem7102565";
    private static final String AUTO_RECONNECT = "true";
    private static final String CHARACTER_ENCODING = "UTF-8";
    private static final String USE_UNICODE = "true";


    {
        configProp = new Properties();
        configProp.put("user", USER);
        configProp.put("password", PASSWORD);
        configProp.put("autoReconnect", AUTO_RECONNECT);
        configProp.put("characterEncoding", CHARACTER_ENCODING);
        configProp.put("useUnicode", USE_UNICODE);
    }

    ConnectionProducer() {
        try {
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
        } catch (SQLException e) {
            LOGGER.fatal("Problem with DriverManager registration", e);
            throw new RuntimeException(e);
        }
    }

    public Connection produce() throws SQLException {

        return DriverManager.getConnection(URL, configProp);
    }
}
