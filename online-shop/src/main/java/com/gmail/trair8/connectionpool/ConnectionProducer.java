package com.gmail.trair8.connectionpool;

import com.gmail.trair8.exception.OnlineShopException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.ResourceBundle;


public class ConnectionProducer {

    private static final Logger LOGGER = LogManager.getLogger(ConnectionProducer.class);

    private String url;
    private Properties configProp;

    private static final String DATABASE_NAME_PROPERTIES = "database";
    private static final String URL = "db.url";
    private static final String USER = "db.user";
    private static final String PASSWORD = "db.password";
    private static final String AUTO_RECONNECT = "db.autoReconnect";
    private static final String CHARACTER_ENCODING = "db.characterEncoding";
    private static final String USE_UNICODE = "db.useUnicode";

    ConnectionProducer() {
        ResourceBundle resourceBundle = ResourceBundle.getBundle(DATABASE_NAME_PROPERTIES);
        configProp = new Properties();
        url = resourceBundle.getString(URL);
        configProp.put("user", resourceBundle.getString(USER));
        configProp.put("password", resourceBundle.getString(PASSWORD));
        configProp.put("autoReconnect", resourceBundle.getString(AUTO_RECONNECT));
        configProp.put("characterEncoding", resourceBundle.getString(CHARACTER_ENCODING));
        configProp.put("useUnicode", resourceBundle.getString(USE_UNICODE));
        try {
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
        } catch (SQLException e) {
            LOGGER.fatal("Problem with DriverManager registration", e);
            throw new OnlineShopException(e);
        }
    }

    public Connection produce() throws SQLException {

        return DriverManager.getConnection(url, configProp);
    }
}
