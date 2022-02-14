package com.moroz.service;

import com.moroz.persistence.dao.UserDao;

/**
 * @author : anton
 * @since : 14.02.2022, пн
 **/
public class UserService {
    private final UserDao userDao;

    public UserService() {
        userDao = new UserDao();
    }

    public UserDao getUserDao() {
        return userDao;
    }
}
