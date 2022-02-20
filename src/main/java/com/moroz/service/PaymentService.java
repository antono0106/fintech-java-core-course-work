package com.moroz.service;

import com.moroz.persistence.dao.PaymentDao;
import com.moroz.persistence.entites.PaymentEntity;

/**
 * @author : anton
 * @since : 19.02.2022, сб
 **/
public class PaymentService implements Service<PaymentEntity> {
    private final PaymentDao paymentDao;

    public PaymentService() {
        paymentDao = new PaymentDao();
    }

    @Override
    public void create(PaymentEntity entity) {
        paymentDao.saveEntity(entity);
    }

    public PaymentEntity findById(long id) {
        return null;
    }
}