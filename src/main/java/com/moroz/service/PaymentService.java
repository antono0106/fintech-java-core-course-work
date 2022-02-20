package com.moroz.service;

import com.moroz.persistence.dao.PaymentDao;
import com.moroz.persistence.entites.PaymentEntity;
import com.moroz.persistence.enums.PaymentStatus;

import java.util.List;

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

    public PaymentEntity getLastPayment() {
        return paymentDao.findAll().get(paymentDao.findAll().size() - 1);
    }

    public List<PaymentEntity> findAll() {
        return paymentDao.findAll();
    }

    public void updateStatus(PaymentEntity entity, PaymentStatus status) {
        paymentDao.updateStatus(entity, status);
    }

    public PaymentEntity findById(long id) {
        return null;
    }
}
