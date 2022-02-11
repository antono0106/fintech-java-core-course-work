package com.moroz;

import com.moroz.persistence.dao.UserDao;
import com.moroz.persistence.entites.UserEntity;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * @author : anton
 * @since : 11.02.2022, пт
 **/
public class UserDaoTest {
    private UserDao userDao;

    @Before
    public void setup() {
        userDao = new UserDao();
    }

    @Ignore
    @Test
    public void saveTest() {
        assertNotNull(userDao.saveEntity(new UserEntity("Anton", "email@gmail.com", "1111111")));
    }

    @Test
    public void getAllTest() {
        assertNotEquals(userDao.findAll().size(), 0);
    }
}
