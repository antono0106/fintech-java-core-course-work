package com.moroz.persistence.dao;

import com.moroz.persistence.ConnectionUtil;
import com.moroz.persistence.entites.MovieShowEntity;
import com.moroz.persistence.entites.PaymentEntity;
import com.moroz.persistence.entites.TicketEntity;
import com.moroz.persistence.entites.UserEntity;
import com.moroz.persistence.enums.PaymentStatus;
import com.moroz.persistence.enums.TicketStatus;

import java.sql.*;
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
    private final PaymentDao paymentDao = new PaymentDao();

    private final List<TicketStatus> statuses = List.of(TicketStatus.NEW, TicketStatus.PROCESSING, TicketStatus.DONE, TicketStatus.FAILED);

    @Override
    public List<TicketEntity> findAll() {
        List<TicketEntity> ticketEntities = new ArrayList<>();
        try(Statement statement = connection.createStatement();) {

            ResultSet resultSet = statement.executeQuery("SELECT * FROM " + tableName + ";");

            while (resultSet.next()) {
                MovieShowEntity movieShowEntity = movieShowDao.findById(resultSet.getInt("movie_show_id"));

                TicketStatus ticketStatus = null;

                for (TicketStatus status: statuses) {
                    if (status.getId() == resultSet.getInt("status_id")) {
                        ticketStatus = status;
                        break;
                    }
                }

                PaymentEntity paymentEntity = null;

                for (PaymentEntity pEntity: paymentDao.findAll()) {
                    if (resultSet.getString("payment_id") != null && pEntity.getId() == Integer.parseInt(resultSet.getString("payment_id"))) {
                        paymentEntity = paymentDao.findById(pEntity.getId());
                    }
                }

                ticketEntities.add(new TicketEntity(movieShowEntity,
                        resultSet.getInt("row"),
                        resultSet.getInt("place"),
                        ticketStatus,
                        resultSet.getTimestamp("creation_date").toLocalDateTime(),
                        resultSet.getTimestamp("modification_date").toLocalDateTime(),
                        paymentEntity));
            }
        } catch (SQLException e) {
            logger.error(e);
        }
        return ticketEntities;
    }

    @Override
    public void saveEntity(TicketEntity entity) {
        try(PreparedStatement pstmt = connection.prepareStatement("INSERT INTO ticket (movie_show_id, row, place)\n" +
                        "SELECT " + entity.getMovieShowEntity().getId()  + ", " + entity.getRow() + ", " + entity.getPlace() +
                        " WHERE NOT EXISTS(SELECT * FROM ticket WHERE movie_show_id = " + entity.getMovieShowEntity().getId() + " AND row = " + entity.getRow() + " AND place = " + entity.getPlace() +");",
                Statement.RETURN_GENERATED_KEYS);) {

            for (int i = 0; i < findAll().size(); i++) {
                if (entity.equals(findAll().get(i))) {
                    throw new SQLException("Cant create entity: row and place are already occupied");
                }
            }

            pstmt.executeUpdate();
            logger.info("Saved " + entity);
        } catch (SQLException e) {
            logger.error(e);
        }
    }

    @Override
    public void updateEntity(TicketEntity entity, Long statusId) {
        try(PreparedStatement pstmt = connection.prepareStatement("UPDATE " + tableName + " SET "
                        + "movie_show_id = '" + entity.getMovieShowEntity().getId() + "', status_id = '" + entity.getStatus().getId() + "', modification_date = '" + entity.getDateOfStatusModification() + "'" +
                        " WHERE status_id = '" + statusId +"';",
                Statement.RETURN_GENERATED_KEYS);) {

            pstmt.executeUpdate();
            logger.info("Updated " + entity);
        } catch (SQLException e) {
            logger.error(e);
        }
    }

    @Override
    public void deleteEntity(TicketEntity entity) {
        try(PreparedStatement pstmt = connection.prepareStatement("DELETE FROM " + tableName + " WHERE "
                        + "movie_show_id = '" + entity.getMovieShowEntity().getId() + "'AND row = '" + entity.getRow() + "'AND place = '" + entity.getPlace() + "';",
                Statement.RETURN_GENERATED_KEYS);) {

            pstmt.executeUpdate();
            logger.info("Deleted " + entity);
        } catch (SQLException e) {
            logger.error(e);
        }
    }
}
