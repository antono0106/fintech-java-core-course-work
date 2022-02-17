package com.moroz.persistence.entites;

import com.moroz.persistence.enums.TicketStatus;

import java.time.LocalDateTime;

/**
 * @author : anton
 * @since : 01.02.2022, вт
 **/
public class TicketEntity implements Entity {
    private MovieShowEntity cinemaShow;
    private int row;
    private int place;
    private UserEntity userEntity;
    private TicketStatus status;
    private final LocalDateTime dateOfCreation;
    private LocalDateTime dateOfStatusModification;
    private String paymentCode;

    public TicketEntity(MovieShowEntity cinemaShow, int row, int place, UserEntity userEntity, TicketStatus status) {
        this.cinemaShow = cinemaShow;
        this.row = row;
        this.place = place;
        this.userEntity = userEntity;
        this.status = status;
        this.dateOfCreation = LocalDateTime.now();
        this.dateOfStatusModification = LocalDateTime.now();
    }

    public MovieShowEntity getCinemaShow() {
        return cinemaShow;
    }

    public void setCinemaShow(MovieShowEntity cinemaShow) {
        this.cinemaShow = cinemaShow;
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

    public UserEntity getUserEntity() {
        return userEntity;
    }

    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
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

    public String getPaymentCode() {
        return paymentCode;
    }

    public void setPaymentCode(String paymentCode) {
        this.paymentCode = paymentCode;
    }
}
