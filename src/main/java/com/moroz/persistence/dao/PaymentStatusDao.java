package com.moroz.persistence.dao;

import com.moroz.persistence.enums.PaymentStatus;

import java.util.List;

/**
 * @author : anton
 * @since : 17.02.2022, чт
 **/
public class PaymentStatusDao implements BaseDao<PaymentStatus, String>{
    @Override
    public List<PaymentStatus> findAll() {
        return null;
    }

    @Override
    public void saveEntity(PaymentStatus entity) {

    }

    @Override
    public void updateEntity(PaymentStatus entity, String whereValue) {

    }

    @Override
    public void deleteEntity(PaymentStatus entity) {

    }
}
