package com.moroz.persistence.dao;

import com.moroz.persistence.enums.TicketStatus;

import java.util.List;

/**
 * @author : anton
 * @since : 17.02.2022, чт
 **/
public class TicketStatusDao implements BaseDao<TicketStatus, String> {
    @Override
    public List<TicketStatus> findAll() {
        return null;
    }

    @Override
    public void saveEntity(TicketStatus entity) {

    }

    @Override
    public void updateEntity(TicketStatus entity, String whereValue) {

    }

    @Override
    public void deleteEntity(TicketStatus entity) {

    }
}
