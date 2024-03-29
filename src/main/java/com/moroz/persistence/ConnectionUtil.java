package com.moroz.persistence;

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
public class ConnectionUtil {
    private static final String URL = "jdbc:postgresql://localhost:5432/cinema";
    private static final Properties PROPS = new Properties();
    private static Connection connection;

    static final CustomLogger logger = new CustomLogger(ConnectionUtil.class);

    public static Connection getConnection() {
        if (connection == null) {
            try {
                Class.forName("org.postgresql.Driver");
                PROPS.setProperty("user", "postgres");
                PROPS.setProperty("password", "root");
                connection = DriverManager.getConnection(URL, PROPS);
                logger.info("Connected to " + connection.getCatalog() + " database");
            } catch (SQLException | ClassNotFoundException e) {
                logger.error(e);
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
                logger.error(String.join("\n",Arrays.toString(e.getStackTrace())));
            }
        }
    }
}
