package com.moroz.persistence.dao;

import com.moroz.exceptions.EntityNotFoundException;
import com.moroz.persistence.ConnectionUtil;
import com.moroz.persistence.entites.UserEntity;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * @author : anton
 * @since : 11.02.2022, пт
 **/
public class UserDao implements BaseDao<UserEntity, String> {
    private final String tableName = "users";
    private final Connection connection = ConnectionUtil.getConnection();

    private final Pattern phoneNumberPattern = Pattern.compile("^(?:\\+38)?(0\\d{9})$");
    private final Pattern emailPattern = Pattern.compile("^(.+)@(.+)$");

    @Override
    public List<UserEntity> findAll() {
        List<UserEntity> users = new ArrayList<>();

        try(Statement statement = connection.createStatement()) {
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
    public void saveEntity(UserEntity entity) {
        try(PreparedStatement pstmt = connection.prepareStatement("INSERT INTO " + tableName + " VALUES ('"
                        + entity.getFullName() + "', '" + entity.getEmail() + "', '" + entity.getPhoneNumber() + "');",
                Statement.RETURN_GENERATED_KEYS);) {
            if (!emailPattern.matcher(entity.getEmail()).matches()
                || !phoneNumberPattern.matcher(entity.getPhoneNumber()).matches()) {
                throw new SQLException("Input data doesn't match");
            }

            pstmt.executeUpdate();
            logger.info("Saved " + entity);
        } catch (SQLException e) {
            logger.error(e);
        }
    }

    @Override
    public void updateEntity(UserEntity entity, String email) {
        try(PreparedStatement pstmt = connection.prepareStatement("UPDATE " + tableName + " SET "
                        + "full_name = '" + entity.getFullName() + "', email = '" + entity.getEmail() + "', phone = '" + entity.getPhoneNumber() + "'" +
                        " WHERE email = '" + email +"';",
                Statement.RETURN_GENERATED_KEYS);) {
            if (!emailPattern.matcher(entity.getEmail()).matches()
                    || !phoneNumberPattern.matcher(entity.getPhoneNumber()).matches()
                    || !emailPattern.matcher(email).matches()) {
                throw new SQLException("Input data doesn't match");
            }


            pstmt.executeUpdate();
            logger.info("Updated " + entity);
        } catch (SQLException e) {
            logger.error(e);
        }
    }

    @Override
    public void deleteEntity(UserEntity entity) {
        try(PreparedStatement pstmt = connection.prepareStatement("DELETE FROM " + tableName + " WHERE "
                        + "full_name = '" + entity.getFullName() + "'AND email = '" + entity.getEmail() + "'AND phone = '" + entity.getPhoneNumber() + "';",
                Statement.RETURN_GENERATED_KEYS);) {
            if (!emailPattern.matcher(entity.getEmail()).matches()
                    || !phoneNumberPattern.matcher(entity.getPhoneNumber()).matches()) {
                throw new SQLException("Input data doesn't match");
            }


            pstmt.executeUpdate();
            logger.info("Deleted " + entity);
        } catch (SQLException e) {
            logger.error(e);
        }
    }

    public UserEntity findByEmail(String email) {
        for (UserEntity e: findAll()) {
            if (e.getEmail().equals(email)) {
                logger.info("Found " + e);
                return e;
            }
        }
        throw new EntityNotFoundException("Entity not found");
    }

    public void deleteByEmail(String email) {
        for (UserEntity e: findAll()) {
            if (e.getEmail().equals(email)) {
                deleteEntity(e);
                break;
            }
        }
        throw new EntityNotFoundException("Entity not found");
    }

}
