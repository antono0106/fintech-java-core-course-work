package com.moroz.persistence.entites;

import com.moroz.persistence.enums.TicketStatus;

import java.time.LocalDateTime;

/**
 * @author : anton
 * @since : 01.02.2022, вт
 **/
public class TicketEntity implements Entity {
    private MovieShowEntity movieShowEntity;
    private int row;
    private int place;
    private TicketStatus status;
    private final LocalDateTime dateOfCreation;
    private LocalDateTime dateOfStatusModification;
    private PaymentEntity paymentEntity;

    public TicketEntity(MovieShowEntity movieShowEntity, int row, int place) {
        this.movieShowEntity = movieShowEntity;
        this.row = row;
        this.place = place;
        this.status = TicketStatus.NEW;
        this.dateOfCreation = LocalDateTime.now();
        this.dateOfStatusModification = LocalDateTime.now();
        this.paymentEntity = null;
    }

    public TicketEntity(MovieShowEntity movieShowEntity, int row, int place, TicketStatus status, LocalDateTime dateOfCreation, LocalDateTime dateOfStatusModification, PaymentEntity paymentEntity) {
        this.movieShowEntity = movieShowEntity;
        this.row = row;
        this.place = place;
        this.status = status;
        this.dateOfCreation = dateOfCreation;
        this.dateOfStatusModification = dateOfStatusModification;
        this.paymentEntity = paymentEntity;
    }

    public MovieShowEntity getMovieShowEntity() {
        return movieShowEntity;
    }

    public void setMovieShowEntity(MovieShowEntity movieShowEntity) {
        this.movieShowEntity = movieShowEntity;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getPlace() {
        return place;
    }

    public void setPlace(int place) {
        this.place = place;
    }

    public TicketStatus getStatus() {
        return status;
    }

    public void setStatus(TicketStatus status) {
        this.status = status;
    }

    public LocalDateTime getDateOfCreation() {
        return dateOfCreation;
    }

    public LocalDateTime getDateOfStatusModification() {
        return dateOfStatusModification;
    }

    public void setDateOfStatusModification(LocalDateTime dateOfStatusModification) {
        this.dateOfStatusModification = dateOfStatusModification;
    }

    public PaymentEntity getPaymentEntity() {
        return paymentEntity;
    }

    public void setPaymentEntity(PaymentEntity paymentEntity) {
        this.paymentEntity = paymentEntity;
    }
}
