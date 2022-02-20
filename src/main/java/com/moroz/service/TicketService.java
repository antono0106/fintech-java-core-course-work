package com.moroz.service;

import com.moroz.persistence.dao.TicketDao;
import com.moroz.persistence.entites.TicketEntity;
import com.moroz.persistence.enums.TicketStatus;

import java.util.List;
import java.util.Set;

/**
 * @author : anton
 * @since : 19.02.2022, сб
 **/

/*
TODO
сделать проверку занятости места через массив булеанов
* */
public class TicketService implements Service<TicketEntity> {
    private TicketDao ticketDao;

    private final CinemaService cinemaService = new CinemaService();

    public TicketService() {
        ticketDao = new TicketDao();
    }

    @Override
    public void create(TicketEntity entity) {
        ticketDao.saveEntity(entity);
    }

    public List<TicketEntity> findAll() {
        return ticketDao.findAll();
    }

    public void updateStatus(TicketEntity entity, TicketStatus status) {
        ticketDao.updateStatus(entity, status);
    }

    public TicketEntity findById(long id) {
        return null;
    }
}
