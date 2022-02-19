package com.moroz.handlers;

import com.moroz.commands.FileCommands;
import com.moroz.exceptions.NoSplitterException;
import com.moroz.exceptions.NoSuchCommandException;
import com.moroz.exceptions.TooMuchArgsException;
import com.moroz.logging.CustomLogger;
import com.moroz.persistence.entites.CinemaEntity;
import com.moroz.persistence.entites.MovieEntity;
import com.moroz.persistence.entites.MovieShowEntity;
import com.moroz.persistence.entites.UserEntity;
import com.moroz.service.CinemaService;
import com.moroz.service.MovieService;
import com.moroz.service.MovieShowService;
import com.moroz.service.UserService;

import java.time.LocalTime;
import java.util.List;

/**
 * @author : anton
 * @since : 15.02.2022, вт
 **/
public class LinesHandler {
    static final CustomLogger logger = new CustomLogger(LinesHandler.class);

    private List<String> fileLines;

    public LinesHandler(List<String> fileLines) {
        this.fileLines = fileLines;
    }

    public void handle() throws NoSplitterException, TooMuchArgsException {
        logger.info("Processing lines...");
        for (String line : fileLines) {
            if (!line.contains("|")) {
                logger.error("No \"|\" splitter in line");
                continue;
            }

            String[] splitter = line.split("\\|");

            if (splitter[0].equals(FileCommands.REGISTER.toString())) {
                UserService userService = new UserService();
                userService.create(new UserEntity(splitter[1], splitter[2], splitter[3]));
            }  else if (splitter[0].equals(FileCommands.CREATE_CINEMA.toString())) {
                CinemaService cinemaService = new CinemaService();
                cinemaService.create(new CinemaEntity(splitter[1]));
            } else if (splitter[0].equals(FileCommands.CREATE_MOVIE.toString())) {
                if (splitter.length > 2) {
                    throw new TooMuchArgsException("Can't create movie because of excess of arguments");
                }
                MovieService movieService = new MovieService();
                movieService.create(new MovieEntity(splitter[1]));
            } else if (splitter[0].equals(FileCommands.CREATE_MOVIE_SHOW.toString())) {
                CinemaService cinemaService = new CinemaService();
                MovieService movieService = new MovieService();
                MovieShowService movieShowService = new MovieShowService();
                movieShowService.create(new MovieShowEntity(movieService.findByName(splitter[1]), cinemaService.findByName(splitter[2]),
                        LocalTime.parse(splitter[3]), Integer.parseInt(splitter[4])));
            } else {
                throw new NoSuchCommandException();
            }
        }
    }
}
