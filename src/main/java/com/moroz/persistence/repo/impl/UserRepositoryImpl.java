package com.moroz.persistence.repo.impl;

import com.moroz.logging.CustomLogger;
import com.moroz.persistence.config.ConnectionConfig;
import com.moroz.persistence.entites.UserEntity;
import com.moroz.persistence.repo.UserRepository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserRepositoryImpl implements UserRepository {
    static final CustomLogger logger = new CustomLogger(UserRepository.class);
    private static final Connection connection = ConnectionConfig.getConnection();

    @Override
    public List<UserEntity> getAll() throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM users");

        List<UserEntity> users = new ArrayList<>();

        while (resultSet.next()) {
            users.add(new UserEntity(
                    resultSet.getString("full_name"),
                    resultSet.getString("email"),
                    resultSet.getString("phone")
            ));
        }

        return users;
    }

    @Override
    public UserEntity findByEmail(String email) throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM users WHERE email = " + email);

        return new UserEntity(resultSet.getString("full_name"), resultSet.getString("email"), resultSet.getString("phone"));
    }

    @Override
    public UserEntity findByPhoneNumber(String phoneNumber) throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM users WHERE phone = " + phoneNumber);

        return new UserEntity(resultSet.getString("full_name"), resultSet.getString("email"), resultSet.getString("phone"));
    }
}
