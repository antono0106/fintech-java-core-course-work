package com.moroz.service;

import com.moroz.persistence.dao.CinemaDao;
import com.moroz.persistence.entites.CinemaEntity;
import com.moroz.persistence.entites.TicketEntity;

import java.util.List;
import java.util.Set;

/**
 * @author : anton
 * @since : 19.02.2022, сб
 **/
public class CinemaService implements Service<CinemaEntity> {
    private final CinemaDao cinemaDao;

    public CinemaService() {
        this.cinemaDao = new CinemaDao();
    }

    public CinemaDao getCinemaDao() {
        return cinemaDao;
    }

    @Override
    public void create(CinemaEntity entity) {
        cinemaDao.saveEntity(entity);
    }

    public Set<CinemaEntity> getAllPlacesOccupancy() {
        return cinemaDao.getCinemaEntities();
    }

    public CinemaEntity findById(long id) {
        return cinemaDao.findById(id);
    }

    public CinemaEntity findByName(String name) {
        return cinemaDao.findByName(name);
    }

    public void initPlacesOccupancy(List<TicketEntity> ticketEntities) {
        cinemaDao.initPlacesOccupancy(ticketEntities);
    }
}
