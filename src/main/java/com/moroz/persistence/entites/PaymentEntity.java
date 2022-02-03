package com.moroz.persistence.entites;

import com.moroz.persistence.enums.PaymentStatus;

/**
 * @author : anton
 * @since : 01.02.2022, вт
 **/
public class PaymentEntity implements Entity {
    private String paymentCode;
    private int amount;
    private long card;
    private PaymentStatus status;

    public PaymentEntity(String paymentCode, int amount, long card) {
        this.paymentCode = paymentCode;
        this.amount = amount;
        this.card = card;
        this.status = PaymentStatus.NEW;
    }

    public String getPaymentCode() {
        return paymentCode;
    }

    public void setPaymentCode(String paymentCode) {
        this.paymentCode = paymentCode;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public long getCard() {
        return card;
    }

    public void setCard(long card) {
        this.card = card;
    }

    public PaymentStatus getStatus() {
        return status;
    }

    public void setStatus(PaymentStatus status) {
        this.status = status;
    }
}
