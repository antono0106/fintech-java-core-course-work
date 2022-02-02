package com.moroz.persistence.config;

import com.moroz.logging.CustomLogger;

import java.sql.Connection;
import java.util.Properties;

/**
 * @author : anton
 * @since : 01.02.2022, вт
 **/
public class ConnectionConfig {
    private static Connection connection;
    private final Properties props = new Properties();


    private ConnectionConfig() {

    }

    public Connection getInstance() {
        return connection;
    }
}
