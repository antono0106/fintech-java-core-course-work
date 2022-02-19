package com.moroz.service;

import com.moroz.persistence.dao.MovieShowDao;
import com.moroz.persistence.entites.MovieShowEntity;

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

    public MovieShowEntity findById(long id) {
        return movieShowDao.findById(id);
    }
}
