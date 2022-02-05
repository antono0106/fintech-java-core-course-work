package com.moroz.persistence.repo;

import com.moroz.persistence.entites.UserEntity;

import java.sql.SQLException;
import java.util.List;

public interface UserRepository {
    List<UserEntity> getAll() throws SQLException;

    UserEntity findByEmail(String email) throws SQLException;

    UserEntity findByPhoneNumber(String phoneNumber) throws SQLException;
}
