package com.moroz.persistence.dao;

import com.moroz.persistence.ConnectionUtil;
import com.moroz.persistence.entites.MovieShowEntity;
import com.moroz.persistence.entites.TicketEntity;
import com.moroz.persistence.entites.UserEntity;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * @author : anton
 * @since : 17.02.2022, чт
 **/
public class TicketDao implements BaseDao<TicketEntity, Long> {
    private final String tableName = "ticket";
    private final Connection connection = ConnectionUtil.getConnection();

    private final MovieShowDao movieShowDao = new MovieShowDao();

    @Override
    public List<TicketEntity> findAll() {
        List<TicketEntity> ticketEntities = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM " + tableName + ";");

            while (resultSet.next()) {
                MovieShowEntity movieShowEntity = movieShowDao.findById(resultSet.getInt("movie_show_id"));



            }
        } catch (SQLException e) {
            logger.error(e);
        }
        return ticketEntities;
    }

    @Override
    public void saveEntity(TicketEntity entity) {

    }

    @Override
    public void updateEntity(TicketEntity entity, Long whereValue) {

    }

    @Override
    public void deleteEntity(TicketEntity entity) {

    }
}
