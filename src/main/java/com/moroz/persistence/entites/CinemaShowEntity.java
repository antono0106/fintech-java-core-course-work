package com.moroz.persistence.entites;

import java.time.LocalTime;
import java.util.Objects;

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

    @Override
    public String toString() {
        return "CinemaShowEntity{" +
                "movieEntity=" + movieEntity +
                ", cinemaEntity=" + cinemaEntity +
                ", time=" + time +
                ", amount=" + amount +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CinemaShowEntity that = (CinemaShowEntity) o;
        return amount == that.amount && Objects.equals(movieEntity, that.movieEntity) && Objects.equals(cinemaEntity, that.cinemaEntity) && Objects.equals(time, that.time);
    }

    @Override
    public int hashCode() {
        return Objects.hash(movieEntity, cinemaEntity, time, amount);
    }
}
