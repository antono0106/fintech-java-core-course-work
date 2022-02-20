package com.moroz.service;

import com.moroz.persistence.dao.MovieShowDao;
import com.moroz.persistence.entites.CinemaEntity;
import com.moroz.persistence.entites.MovieEntity;
import com.moroz.persistence.entites.MovieShowEntity;

import java.time.LocalTime;
import java.util.List;

/**
 * @author : anton
 * @since : 19.02.2022, сб
 **/
public class MovieShowService implements Service<MovieShowEntity> {
    private final MovieShowDao movieShowDao;

    public MovieShowService() {
        movieShowDao = new MovieShowDao();
    }
    @Override
    public void create(MovieShowEntity entity) {
        movieShowDao.saveEntity(entity);
    }

    public List<MovieShowEntity> findAll() {
        return movieShowDao.findAll();
    }

    public MovieShowEntity findByMovieAndCinemaAndTime(MovieEntity movieEntity, CinemaEntity cinemaEntity, LocalTime localTime) {
        return movieShowDao.findByMovieAndCinemaAndTime(movieEntity, cinemaEntity, localTime);
    }

    public MovieShowEntity findById(long id) {
        return movieShowDao.findById(id);
    }
}
