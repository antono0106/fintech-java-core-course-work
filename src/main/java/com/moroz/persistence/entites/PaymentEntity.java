package com.moroz.persistence.entites;

import com.moroz.persistence.enums.PaymentStatus;

/**
 * @author : anton
 * @since : 01.02.2022, вт
 **/
public class PaymentEntity extends AbstractEntity implements Entity {

    private int amount;
    private String card;
    private PaymentStatus status;

    public PaymentEntity(int amount, String card) {
        this.amount = amount;
        this.card = card;
        this.status = PaymentStatus.NEW;
    }

    public PaymentEntity(int amount, String card, PaymentStatus status) {
        this.amount = amount;
        this.card = card;
        this.status = status;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getCard() {
        return card;
    }

    public void setCard(String card) {
        this.card = card;
    }

    public PaymentStatus getStatus() {
        return status;
    }

    public void setStatus(PaymentStatus status) {
        this.status = status;
    }
}
