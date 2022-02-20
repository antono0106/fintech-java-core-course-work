package com.moroz.handlers;

import com.moroz.commands.FileCommands;
import com.moroz.exceptions.EntityNotFoundException;
import com.moroz.exceptions.NoSplitterException;
import com.moroz.exceptions.TooMuchArgsException;
import com.moroz.logging.CustomLogger;
import com.moroz.persistence.entites.*;
import com.moroz.persistence.notdbentities.PlacesOccupancy;
import com.moroz.service.*;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author : anton
 * @since : 15.02.2022, вт
 **/
public class CommandsHandler {
    static final CustomLogger logger = new CustomLogger(CommandsHandler.class);

    private List<String> fileLines;
    private static final Set<PlacesOccupancy> placesOccupancies = new HashSet<>();

    public CommandsHandler(List<String> fileLines) {
        this.fileLines = fileLines;
    }

    public static List<PlacesOccupancy> getPlacesOccupancies() {
        return new ArrayList<>(placesOccupancies);
    }

    public void handle() {
        logger.info("Processing lines...");
        for (String line : fileLines) {
            if (!line.contains("|")) {
                logger.error("No \"|\" splitter in line");
                continue;
            }

            String[] splitter = line.split("\\|");


            if (splitter[0].equals(FileCommands.REGISTER.toString())) {
                if (splitter.length != 4) {
                    logger.error("Can't create user entity");
                    continue;
                }
                UserService userService = new UserService();
                userService.create(new UserEntity(splitter[1], splitter[2], splitter[3]));
            } else if (splitter[0].equals(FileCommands.CREATE_CINEMA.toString())) {
                if (splitter.length == 2 || splitter.length == 4) {
                    CinemaService cinemaService = new CinemaService();
                    cinemaService.create(new CinemaEntity(splitter[1]));
                } else {
                    logger.error("Can't create cinema entity");
                }
            } else if (splitter[0].equals(FileCommands.CREATE_MOVIE.toString())) {
                if (splitter.length != 2) {
                    logger.error("Can't create movie entity");
                    continue;
                }
                MovieService movieService = new MovieService();
                movieService.create(new MovieEntity(splitter[1]));
            } else if (splitter[0].equals(FileCommands.CREATE_MOVIE_SHOW.toString())) {
                if (splitter.length != 5) {
                    logger.error("Can't create movie show entity");
                    continue;
                }

                try {
                    MovieService movieService = new MovieService();
                    CinemaService cinemaService = new CinemaService();
                    MovieShowService movieShowService = new MovieShowService();
                    movieShowService.create(new MovieShowEntity(movieService.findByName(splitter[1]), cinemaService.findByName(splitter[2]),
                            LocalTime.parse(splitter[3]), Integer.parseInt(splitter[4])));
                } catch (EntityNotFoundException e) {
                    logger.error(e);
                }
            } else if (splitter[0].equals(FileCommands.BUY_TICKET.toString())) {
                if (splitter.length != 4) {
                    logger.error("Can't create ticket entity");
                    continue;
                }

                try {
                    TicketService ticketService = new TicketService();
                    CinemaService cinemaService = new CinemaService();
                    cinemaService.initPlacesOccupancy(ticketService.findAll());
                    MovieShowService movieShowService = new MovieShowService();
                    ticketService.create(new TicketEntity(movieShowService.findById(Integer.parseInt(splitter[1])), Integer.parseInt(splitter[2]), Integer.parseInt(splitter[3])));
                    cinemaService.initPlacesOccupancy(ticketService.findAll());

                    for (CinemaEntity cinemaEntity: cinemaService.getAllPlacesOccupancy()) {
                        placesOccupancies.add(new PlacesOccupancy(cinemaEntity.getName(), cinemaEntity.getPlacesOccupancy()));
                    }

                } catch (EntityNotFoundException e) {
                    logger.error(e);
                }
            } else {
                logger.error("No such command called " + splitter[0]);
            }
        }
    }
}
