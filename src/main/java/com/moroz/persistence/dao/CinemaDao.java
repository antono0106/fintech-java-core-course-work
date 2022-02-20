package com.moroz.persistence.dao;

import com.moroz.exceptions.EntityNotFoundException;
import com.moroz.persistence.ConnectionUtil;
import com.moroz.persistence.entites.CinemaEntity;
import com.moroz.persistence.entites.TicketEntity;
import com.moroz.persistence.entites.UserEntity;
import com.moroz.persistence.enums.TicketStatus;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author : anton
 * @since : 15.02.2022, вт
 **/
public class CinemaDao implements BaseDao<CinemaEntity, Long> {
    private final String tableName = "cinema";
    private final Connection connection = ConnectionUtil.getConnection();

    /*private TicketDao ticketDao = new TicketDao();*/

    private Set<CinemaEntity> cinemaEntities = new HashSet<>();

    @Override
    public List<CinemaEntity> findAll() {
        List<CinemaEntity> cinemas = new ArrayList<>();
        try (Statement statement = connection.createStatement();) {

            ResultSet resultSet = statement.executeQuery("SELECT * FROM " + tableName + ";");

            while (resultSet.next()) {
                CinemaEntity cinemaEntity = new CinemaEntity(
                        resultSet.getString("name"),
                        resultSet.getInt("rows_amount"), resultSet.getInt("places_per_row_amount"));
                cinemaEntity.setId(resultSet.getInt("c_id"));
                cinemas.add(cinemaEntity);
            }
        } catch (SQLException e) {
            logger.error(e);
        }

        cinemaEntities = new HashSet<>(cinemas);

        return cinemas;
    }

    @Override
    public void saveEntity(CinemaEntity entity) {
        try (PreparedStatement pstmt = connection.prepareStatement("INSERT INTO " + tableName
                        + " (name, rows_amount, places_per_row_amount) VALUES ('"
                        + entity.getName() + "', '" + entity.getRowsCount() + "', '" + entity.getPlacesCount() + "');",
                Statement.RETURN_GENERATED_KEYS);) {

            pstmt.executeUpdate();
            logger.info("Saved " + entity);
        } catch (SQLException e) {
            logger.error(e);
        }
    }

    @Override
    public void updateEntity(CinemaEntity entity, Long id) {
        try (PreparedStatement pstmt = connection.prepareStatement("UPDATE " + tableName + " SET "
                        + "name = '" + entity.getName() + "', rows_amount = '" + entity.getRowsCount() + "', places_per_row_amount = '" + entity.getPlacesCount() + "'" +
                        " WHERE c_id = '" + id + "';",
                Statement.RETURN_GENERATED_KEYS);) {

            pstmt.executeUpdate();
            logger.info("Updated " + entity);
        } catch (SQLException e) {
            logger.error(e);
        }
    }

    public Set<CinemaEntity> getCinemaEntities() {
        return cinemaEntities;
    }

    public void initPlacesOccupancy(List<TicketEntity> ticketEntities) {
        findAll();

        for (CinemaEntity cinemaEntity : cinemaEntities) {
            for (TicketEntity t : ticketEntities) {
                if (!t.getStatus().equals(TicketStatus.FAILED)) {
                    cinemaEntity.takePlace(t.getRow(), t.getPlace());
                }
            }
        }
    }

    @Override
    public void deleteEntity(CinemaEntity entity) {
        try (PreparedStatement pstmt = connection.prepareStatement("DELETE FROM " + tableName + " WHERE "
                        + "name = '" + entity.getName() + "'AND rows_amount  = '" + entity.getRowsCount() + "' AND places_per_row_amount = '" + entity.getPlacesCount() + "';",
                Statement.RETURN_GENERATED_KEYS);) {

            pstmt.executeUpdate();
            logger.info("Deleted " + entity);
        } catch (SQLException e) {
            logger.error(e);
        }
    }

    public CinemaEntity findById(long id) {
        for (CinemaEntity e : findAll()) {
            if (e.getId() == id) {
                logger.info("Found " + e);
                return e;
            }
        }
        throw new EntityNotFoundException("Entity not found");
    }


    public CinemaEntity findByName(String name) {
        for (CinemaEntity e : findAll()) {
            if (e.getName().equals(name)) {
                logger.info("Found " + e);
                return e;
            }
        }
        throw new EntityNotFoundException("Entity not found");
    }

    public void deleteById(long id) {
        try {
            PreparedStatement pstmt = connection.prepareStatement("DELETE FROM " + tableName + " WHERE "
                            + " c_id = " + id + ";",
                    Statement.RETURN_GENERATED_KEYS);
            pstmt.executeUpdate();
            logger.info("Deleted entity by id =" + id);
        } catch (SQLException e) {
            logger.error(e);
        }
    }
}
