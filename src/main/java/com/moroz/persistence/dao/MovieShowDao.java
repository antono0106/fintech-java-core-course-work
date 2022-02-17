package com.moroz.persistence.dao;

import com.moroz.persistence.ConnectionUtil;
import com.moroz.persistence.entites.CinemaEntity;
import com.moroz.persistence.entites.MovieEntity;
import com.moroz.persistence.entites.MovieShowEntity;
import com.moroz.persistence.entites.UserEntity;

import java.sql.*;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author : anton
 * @since : 17.02.2022, чт
 **/
public class MovieShowDao implements BaseDao<MovieShowEntity, Long> {
    private final String tableName = "movie_show";
    private final Connection connection = ConnectionUtil.getConnection();

    private final MovieDao movieDao = new MovieDao();
    private final CinemaDao cinemaDao = new CinemaDao();

    @Override
    public List<MovieShowEntity> findAll() {
        List<MovieShowEntity> movieShowEntities = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM " + tableName +" AS ms "
                    + "LEFT JOIN movie m on m.id = ms.movie_id "
                    + "LEFT JOIN cinema c on c.id = ms.cinema_id;");

            while(resultSet.next()) {
                MovieEntity movieEntity = movieDao.findById(resultSet.getInt("m.id"));
                CinemaEntity cinemaEntity = cinemaDao.findById(resultSet.getInt("c.id"));

                movieShowEntities.add(new MovieShowEntity(movieEntity, cinemaEntity, resultSet.getTime("time").toLocalTime(), resultSet.getInt("price")));
            }
        } catch (SQLException e) {
            logger.error(e);
        }
        return movieShowEntities;
    }

    @Override
    public void saveEntity(MovieShowEntity entity) {
        try {
            PreparedStatement pstmt = connection.prepareStatement("INSERT INTO " + tableName
                            + " (movie_id, cinema_id, time, price) VALUES ('" + entity.getMovieEntity().getId() + ", " + entity.getCinemaEntity().getId()
                            + ", " + entity.getTime()  + ", " + entity.getPrice() +"');",
                    Statement.RETURN_GENERATED_KEYS);
            pstmt.executeUpdate();
            logger.info("Saved " + entity);
        } catch (SQLException e) {
            logger.error(e);
        }
    }

    @Override
    public void updateEntity(MovieShowEntity entity, Long id) {
        try {
            PreparedStatement pstmt = connection.prepareStatement("UPDATE " + tableName + " SET "
                            + "movie_id = " + entity.getMovieEntity().getId() + ", "
                            + "cinema_id =" + entity.getCinemaEntity().getId() + ", "
                            + "time = '" + entity.getTime() + "', "
                            + "price = " + entity.getPrice()
                            + " WHERE id = " + id + ";",
                    Statement.RETURN_GENERATED_KEYS);
            pstmt.executeUpdate();
            logger.info("Updated " + entity);
        } catch (SQLException e) {
            logger.error(e);
        }
    }

    @Override
    public void deleteEntity(MovieShowEntity entity) {
        try {
            PreparedStatement pstmt = connection.prepareStatement("DELETE FROM " + tableName
                    + " WHERE "
                    + "movie_id = " + entity.getMovieEntity().getId() + " AND "
                    + "cinema_id = " + entity.getCinemaEntity().getId() + " AND "
                    + "time = '" + entity.getTime() + "' AND "
                    + "price = " + entity.getPrice() + ";",
                    Statement.RETURN_GENERATED_KEYS);
            pstmt.executeUpdate();
            logger.info("Deleted " + entity);
        } catch (SQLException e) {
            logger.error(e);
        }
    }

    public MovieShowEntity findById(long id) {
        for (MovieShowEntity e: findAll()) {
            if (e.getId() == id) {
                logger.info("Found " + e);
                return e;
            }
        }
        throw new RuntimeException("Entity not found");
    }

    public void deleteById(long id) {
        try {
            PreparedStatement pstmt = connection.prepareStatement("DELETE FROM " + tableName + " WHERE "
                            + " id = " + id + ";",
                    Statement.RETURN_GENERATED_KEYS);
            pstmt.executeUpdate();
            logger.info("Deleted entity by id =" + id);
        } catch (SQLException e) {
            logger.error(e);
        }
    }
}
