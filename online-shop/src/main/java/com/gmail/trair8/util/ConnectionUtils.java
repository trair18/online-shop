package com.gmail.trair8.util;

import com.gmail.trair8.connectionpool.ConnectionPool;
import com.gmail.trair8.exception.OnlineShopException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.function.Consumer;
import java.util.function.Function;

public class ConnectionUtils {

    private static final Logger LOGGER = LogManager.getLogger(ConnectionUtils.class);

    public static <R> R readOperations(Function<Connection, R> function) {
        try (Connection connection = ConnectionPool.getInstance().takeConnection()) {
            connection.setReadOnly(true);
            return function.apply(connection);
        } catch (SQLException e) {
            LOGGER.error("SQLException in readOperations.");
            throw new OnlineShopException(e);
        }
    }

    public static void modifyOperations(Consumer<Connection> consumer) {
        Connection connection = null;
        try {
            connection = ConnectionPool.getInstance().takeConnection();
            connection.setAutoCommit(false);
            consumer.accept(connection);
            connection.commit();
        } catch (SQLException e) {
            rollback(connection);
            LOGGER.error("SQLException in modifyOperations.");
            throw new OnlineShopException(e);
        } finally {
            close(connection);
        }
    }

    private static void rollback(Connection connection) {
        try {
            connection.rollback();
        } catch (SQLException sqlException) {
            LOGGER.error(sqlException);
            throw new OnlineShopException(sqlException);
        }
    }

    private static void close(Connection connection) {
        try {
            connection.close();
        } catch (SQLException e) {
            LOGGER.error(e);
            throw new OnlineShopException(e);
        }
    }
}
