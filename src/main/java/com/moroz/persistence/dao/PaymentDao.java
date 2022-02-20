package com.moroz.persistence.dao;

import com.moroz.exceptions.EntityNotFoundException;
import com.moroz.persistence.ConnectionUtil;
import com.moroz.persistence.entites.PaymentEntity;
import com.moroz.persistence.entites.UserEntity;
import com.moroz.persistence.enums.PaymentStatus;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * @author : anton
 * @since : 17.02.2022, чт
 **/
public class PaymentDao implements BaseDao<PaymentEntity, Long> {
    private final String tableName = "payments";
    private final Connection connection = ConnectionUtil.getConnection();

    private final List<PaymentStatus> statuses = List.of(PaymentStatus.NEW, PaymentStatus.DONE, PaymentStatus.FAILED);

    private final Pattern creditCardRegex = Pattern.compile("^[0-9][0-9]{12}(?:[0-9]{3})?$");

    @Override
    public List<PaymentEntity> findAll() {
        List<PaymentEntity> payments = new ArrayList<>();
        try(Statement statement = connection.createStatement();) {

            ResultSet resultSet = statement.executeQuery("SELECT * FROM " + tableName + ";");

            while(resultSet.next()) {
                PaymentStatus paymentStatus = null;

                for (PaymentStatus status: statuses) {
                    if (status.getId() == resultSet.getInt("status_id")) {
                        paymentStatus = status;
                        break;
                    }
                }

                PaymentEntity paymentEntity = new PaymentEntity(
                        resultSet.getInt("amount"),
                        resultSet.getString("card"),
                        paymentStatus);
                paymentEntity.setId(resultSet.getInt("id"));

                payments.add(paymentEntity);
            }
        } catch (SQLException e) {
            logger.error(e);
        }
        return payments;
    }

    @Override
    public void saveEntity(PaymentEntity entity) {
        try(PreparedStatement pstmt = connection.prepareStatement("INSERT INTO " + tableName + " (amount, card) VALUES ('"
                        + entity.getAmount() + "', '" + entity.getCard() + "');",
                Statement.RETURN_GENERATED_KEYS);) {

            if (!creditCardRegex.matcher(entity.getCard()).matches()) {
                throw new SQLException("Input data doesn't match");
            }

            pstmt.executeUpdate();
            logger.info("Saved " + entity);
        } catch (SQLException e) {
            logger.error(e);
        }
    }

    @Override
    public void updateEntity(PaymentEntity entity, Long id) {
        try(PreparedStatement pstmt = connection.prepareStatement("UPDATE " + tableName + " SET "
                        + "amount = '" + entity.getAmount() + "', card = '" + entity.getCard() + "', status_id = '" + entity.getStatus().getId() + "'" +
                        " WHERE id = '" + id +"';",
                Statement.RETURN_GENERATED_KEYS);) {

            if (!creditCardRegex.matcher(entity.getCard()).matches()) {
                throw new SQLException("Input data doesn't match");
            }

            pstmt.executeUpdate();
            logger.info("Updated " + entity);
        } catch (SQLException e) {
            logger.error(e);
        }
    }

    @Override
    public void deleteEntity(PaymentEntity entity) {
        try(PreparedStatement pstmt = connection.prepareStatement("DELETE FROM " + tableName + " WHERE "
                        + "amount = '" + entity.getAmount() + "'AND card = '" + entity.getCard() + "'AND status_id = '" + entity.getStatus().getId() + "';",
                Statement.RETURN_GENERATED_KEYS);) {

            if (!creditCardRegex.matcher(entity.getCard()).matches()) {
                throw new SQLException("Input data doesn't match");
            }

            pstmt.executeUpdate();
            logger.info("Deleted " + entity);
        } catch (SQLException e) {
            logger.error(e);
        }
    }

    public PaymentEntity findById(Long id) {
        for (PaymentEntity e: findAll()) {
            if (e.getId() == (id)) {
                logger.info("Found " + e);
                return e;
            }
        }
        throw new EntityNotFoundException("Entity not found");
    }

    public void updateStatus(PaymentStatus from, PaymentStatus to) {
        try(PreparedStatement pstmt = connection.prepareStatement("UPDATE " + tableName + " SET "
                        + " status_id = " + to.getId()  +
                        " WHERE status_id = " + from.getId() +";",
                Statement.RETURN_GENERATED_KEYS);) {

            pstmt.executeUpdate();
            logger.info("Updated entities status from  " + from + " to " + to);
        } catch (SQLException e) {
            logger.error(e);
        }
    }

    public List<PaymentStatus> getStatuses() {
        return statuses;
    }
}
