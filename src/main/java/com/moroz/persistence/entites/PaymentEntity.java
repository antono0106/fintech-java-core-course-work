package com.moroz.persistence.entites;

import com.moroz.persistence.enums.PaymentStatus;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PaymentEntity that = (PaymentEntity) o;
        return amount == that.amount && Objects.equals(card, that.card) && status == that.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(amount, card, status);
    }

    @Override
    public String toString() {
        return "PaymentEntity{" +
                "amount=" + amount +
                ", card='" + card + '\'' +
                ", status=" + status +
                '}';
    }
}
