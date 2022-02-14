package com.moroz.persistence.dao;

import com.moroz.logging.CustomLogger;

import java.util.List;

/**
 * @author : anton
 * @since : 11.02.2022, пт
 **/
public interface BaseDao<K, V> {
    CustomLogger logger = new CustomLogger(BaseDao.class);

    List<K> findAll();

    void saveEntity(K entity);

    void updateEntity(K entity, V whereValue);

    void deleteEntity(K entity);
}
