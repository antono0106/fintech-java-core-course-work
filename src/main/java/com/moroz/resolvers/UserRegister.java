package com.moroz.resolvers;

import com.moroz.persistence.entites.UserEntity;
import com.moroz.service.UserService;

/**
 * @author : anton
 * @since : 15.02.2022, вт
 **/
public class UserRegister {
    private final UserService userService;

    public UserRegister() {
        userService = new UserService();
    }

    public void register(UserEntity entity) {
        userService.getUserDao().saveEntity(entity);
    }
}
