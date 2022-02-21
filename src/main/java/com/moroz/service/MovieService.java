package com.moroz.service;

import com.moroz.persistence.dao.MovieDao;
import com.moroz.persistence.entites.CinemaEntity;
import com.moroz.persistence.entites.MovieEntity;

/**
 * @author : anton
 * @since : 19.02.2022, сб
 **/
public class MovieService implements Service<MovieEntity> {
    private MovieDao movieDao;

    public MovieService() {
        movieDao = new MovieDao();
    }

    public MovieDao getMovieDao() {
        return movieDao;
    }

    @Override
    public void create(MovieEntity entity) {
        movieDao.saveEntity(entity);
    }

    public MovieEntity findById(long id) {
        return movieDao.findById(id);
    }

    public MovieEntity findByName(String name) {
        return movieDao.findByName(name);
    }
}
