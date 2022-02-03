package com.moroz.persistence.entites;

import java.time.LocalTime;

/**
 * @author : anton
 * @since : 01.02.2022, вт
 **/
public class CinemaShowEntity implements Entity {
    private MovieEntity movieEntity;
    private CinemaEntity cinemaEntity;
    private LocalTime time;
    private final int amount;

    public CinemaShowEntity(MovieEntity movieEntity, CinemaEntity cinemaEntity, LocalTime time, int amount) {
        this.movieEntity = movieEntity;
        this.cinemaEntity = cinemaEntity;
        this.time = time;
        this.amount = amount;
    }

    public MovieEntity getMovieEntity() {
        return movieEntity;
    }

    public void setMovieEntity(MovieEntity movieEntity) {
        this.movieEntity = movieEntity;
    }

    public CinemaEntity getCinemaEntity() {
        return cinemaEntity;
    }

    public void setCinemaEntity(CinemaEntity cinemaEntity) {
        this.cinemaEntity = cinemaEntity;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public int getAmount() {
        return amount;
    }
}
