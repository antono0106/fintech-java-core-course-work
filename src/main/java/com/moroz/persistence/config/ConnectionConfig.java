package com.moroz.persistence.config;

import com.moroz.logging.CustomLogger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Properties;

/**
 * @author : anton
 * @since : 01.02.2022, вт
 **/
public class ConnectionConfig {
    private static final String URL = "jdbc:postgresql://localhost:5432/cinema";
    private static final Properties PROPS = new Properties();
    private static Connection connection;

    static final CustomLogger logger = new CustomLogger(ConnectionConfig.class);

    static {
        Runtime.getRuntime().addShutdownHook(new Thread(ConnectionConfig::closeConnection));
    }

    public static Connection getConnection() {
        if (connection == null) {
            try {
                Class.forName("org.postgresql.Driver");
                PROPS.setProperty("user", "postgres");
                PROPS.setProperty("password", "root");
                connection = DriverManager.getConnection(URL, PROPS);
                logger.info("Connected to " + connection.getCatalog() + " database");
            } catch (SQLException | ClassNotFoundException e) {
                logger.error(e.getMessage(), e);
            }
        }
        return connection;
    }

    public static void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
                logger.info("Connection closed successfully");
            } catch (SQLException e) {
                logger.error(Arrays.toString(e.getStackTrace()));
            }
        }
    }
}
