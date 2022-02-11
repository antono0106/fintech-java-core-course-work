package com.moroz.persistence.dao;

import com.moroz.logging.CustomLogger;

import java.util.List;

/**
 * @author : anton
 * @since : 11.02.2022, пт
 **/
public interface BaseDao<T> {
    CustomLogger logger = new CustomLogger(BaseDao.class);

    List<T> findAll();

    T saveEntity(T entity);

    T updateEntity(T entity);

    void deleteEntity(T entity);
}
