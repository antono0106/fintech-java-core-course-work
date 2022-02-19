package com.moroz.service;

import com.moroz.persistence.entites.AbstractEntity;

/**
 * @author : anton
 * @since : 19.02.2022, сб
 **/
public interface Service<T> {
    void create(T entity);
}
