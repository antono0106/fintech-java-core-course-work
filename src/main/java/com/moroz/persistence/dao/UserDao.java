package com.moroz.persistence.dao;

import com.moroz.logging.CustomLogger;
import com.moroz.persistence.config.ConnectionConfig;
import com.moroz.persistence.entites.UserEntity;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author : anton
 * @since : 11.02.2022, пт
 **/
public class UserDao implements BaseDao<UserEntity> {

    private final String tableName = "users";

    @Override
    public List<UserEntity> findAll() {
        Connection connection = ConnectionConfig.getConnection();
        List<UserEntity> users = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM " + tableName + ";");

            while(resultSet.next()) {
                users.add(new UserEntity(resultSet.getString("full_name"),
                        resultSet.getString("email"), resultSet.getString("phone")));
            }
        } catch (SQLException e) {
            logger.error(e);
        }
        return users;
    }

    @Override
    public UserEntity saveEntity(UserEntity entity) {
        Connection connection = ConnectionConfig.getConnection();

        try {
            /*Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("INSERT INTO " + tableName + " VALUES ('"
            + entity.getFullName() + "', '" + entity.getEmail() + "', '" + entity.getPhoneNumber() + "');");
            logger.info("Saved " + entity.getEmail());
            return entity;*/
            PreparedStatement pstmt = connection.prepareStatement("INSERT INTO " + tableName + " VALUES ('"
                            + entity.getFullName() + "', '" + entity.getEmail() + "', '" + entity.getPhoneNumber() + "');",
                    Statement.RETURN_GENERATED_KEYS);
            pstmt.executeUpdate();
            logger.info("Saved " + entity.getEmail());
            return entity;
        } catch (SQLException e) {
            logger.error(e);
            return null;
        }
    }

    @Override
    public UserEntity updateEntity(UserEntity entity) {
        return null;
    }

    @Override
    public void deleteEntity(UserEntity entity) {

    }
}
