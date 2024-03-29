package com.moroz.persistence.dao;

import com.moroz.exceptions.EntityNotFoundException;
import com.moroz.persistence.ConnectionUtil;
import com.moroz.persistence.entites.CinemaEntity;
import com.moroz.persistence.entites.MovieEntity;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author : anton
 * @since : 15.02.2022, вт
 **/
public class MovieDao implements BaseDao<MovieEntity, Long> {
    private final String tableName = "movie";
    private final Connection connection = ConnectionUtil.getConnection();

    @Override
    public List<MovieEntity> findAll() {
        List<MovieEntity> movies = new ArrayList<>();
        try(Statement statement = connection.createStatement();) {

            ResultSet resultSet = statement.executeQuery("SELECT * FROM " + tableName + ";");

            while(resultSet.next()) {
                MovieEntity movieEntity = new MovieEntity(
                        resultSet.getString("name"));
                movieEntity.setId(resultSet.getInt("m_id"));
                movies.add(movieEntity);
            }
        } catch (SQLException e) {
            logger.error(e);
        }
        return movies;
    }

    @Override
    public void saveEntity(MovieEntity entity) {
        try(PreparedStatement pstmt = connection.prepareStatement("INSERT INTO " + tableName
                        + " (name) VALUES ('" + entity.getName() + "');",
                Statement.RETURN_GENERATED_KEYS);) {

            pstmt.executeUpdate();
            logger.info("Saved " + entity);
        } catch (SQLException e) {
            logger.error(e);
        }
    }

    @Override
    public void updateEntity(MovieEntity entity, Long id) {
        try(PreparedStatement pstmt = connection.prepareStatement("UPDATE " + tableName + " SET "
                        + "name = '" + entity.getName() + "' WHERE m_id = " + id + ";",
                Statement.RETURN_GENERATED_KEYS);) {

            pstmt.executeUpdate();
            logger.info("Updated " + entity);
        } catch (SQLException e) {
            logger.error(e);
        }
    }

    @Override
    public void deleteEntity(MovieEntity entity) {
        try(PreparedStatement pstmt = connection.prepareStatement("DELETE FROM " + tableName + " WHERE "
                        + "name = '" + entity.getName() + "');",
                Statement.RETURN_GENERATED_KEYS);) {

            pstmt.executeUpdate();
            logger.info("Deleted " + entity);
        } catch (SQLException e) {
            logger.error(e);
        }
    }

    public MovieEntity findById(long id) {
        for (MovieEntity e: findAll()) {
            if (e.getId() == id) {
                logger.info("Found " + e);
                return e;
            }
        }
        throw new EntityNotFoundException("Entity not found");
    }

    public MovieEntity findByName(String name) {
        for (MovieEntity e: findAll()) {
            if (e.getName().equals(name)) {
                logger.info("Found " + e);
                return e;
            }
        }
        throw new EntityNotFoundException("Entity not found");
    }

    public void deleteById(long id) {
        try(PreparedStatement pstmt = connection.prepareStatement("DELETE FROM " + tableName + " WHERE "
                        + " m_id = " + id + ";",
                Statement.RETURN_GENERATED_KEYS);) {
            pstmt.executeUpdate();
            logger.info("Deleted entity by id =" + id);
        } catch (SQLException e) {
            logger.error(e);
        }
    }
}
