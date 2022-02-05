package com.moroz.persistence.config;

import com.moroz.logging.CustomLogger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @author : anton
 * @since : 01.02.2022, вт
 **/
public class ConnectionConfig {
    private static final String url = "jdbc:postgresql://localhost:5432/cinema";
    private static final Properties props = new Properties();
    private static Connection connection;

    static final CustomLogger logger = new CustomLogger(ConnectionConfig.class);

    public static Connection getInstance() {
        if (connection == null) {
            try {
                Class.forName("org.postgresql.Driver");
                props.setProperty("user", "postgres");
                props.setProperty("password", "root");
                connection = DriverManager.getConnection(url, props);
                logger.info("Connected to " + connection.getCatalog());
            } catch (SQLException | ClassNotFoundException e) {
                logger.error(e.getMessage(), e);
                throw new RuntimeException("Failed to connect to database");
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
                e.printStackTrace();
            }
        }
    }
}
