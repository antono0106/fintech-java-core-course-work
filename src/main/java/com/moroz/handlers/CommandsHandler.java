package com.moroz.handlers;

import com.moroz.commands.FileCommands;
import com.moroz.exceptions.EntityNotFoundException;
import com.moroz.logging.CustomLogger;
import com.moroz.persistence.entites.*;
import com.moroz.persistence.enums.TicketStatus;
import com.moroz.randomgenerator.RandomCreditCardNumberGenerator;
import com.moroz.service.*;

import java.time.LocalTime;
import java.util.List;
import java.util.Random;

/**
 * @author : anton
 * @since : 15.02.2022, вт
 **/
public class CommandsHandler {
    static final CustomLogger logger = new CustomLogger(CommandsHandler.class);

    private List<String> fileLines;

    public CommandsHandler(List<String> fileLines) {
        this.fileLines = fileLines;
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
                    logger.error("Can't create user entity: args amount is not equals 4");
                    continue;
                }
                UserService userService = new UserService();
                userService.create(new UserEntity(splitter[1], splitter[2], splitter[3]));
            } else if (splitter[0].equals(FileCommands.CREATE_CINEMA.toString())) {
                if (splitter.length == 2 || splitter.length == 4) {
                    CinemaService cinemaService = new CinemaService();
                    cinemaService.create(new CinemaEntity(splitter[1]));
                } else {
                    logger.error("Can't create cinema entity: args amount is not equals 2 or 4");
                }
            } else if (splitter[0].equals(FileCommands.CREATE_MOVIE.toString())) {
                if (splitter.length != 2) {
                    logger.error("Can't create movie entity: args amount is not equals 2");
                    continue;
                }
                MovieService movieService = new MovieService();
                movieService.create(new MovieEntity(splitter[1]));
            } else if (splitter[0].equals(FileCommands.CREATE_MOVIE_SHOW.toString())) {
                if (splitter.length != 5) {
                    logger.error("Can't create movie show entity: args amount is not equals 5");
                    continue;
                }

                try {
                    MovieService movieService = new MovieService();
                    CinemaService cinemaService = new CinemaService();
                    MovieShowService movieShowService = new MovieShowService();

                    int amount = Integer.parseInt(splitter[4]);
                    if (amount < 0) {
                        logger.error("Cant create movie show entity: amount cannot be less than 0");
                        continue;
                    }

                    MovieShowEntity movieShowEntity = new MovieShowEntity(movieService.findByName(splitter[1]), cinemaService.findByName(splitter[2]),
                            LocalTime.parse(splitter[3]), amount);

                    boolean flag = false;

                    for (MovieShowEntity ms: movieShowService.findAll()) {
                        if ((ms.getCinemaEntity().getId() == movieShowEntity.getCinemaEntity().getId()
                                && ms.getMovieEntity().getId() == movieShowEntity.getMovieEntity().getId()
                                && ms.getTime().equals(movieShowEntity.getTime()))) {
                            logger.error("Can't create movie show entity: entity with that time, movie and cinema entities already exists");
                            flag = true;
                            break;
                        }
                    }

                    if(flag) {
                        continue;
                    }

                    movieShowService.create(movieShowEntity);
                } catch (EntityNotFoundException e) {
                    logger.error(e);
                }
            } else if (splitter[0].equals(FileCommands.BUY_TICKET.toString())) {
                if (splitter.length != 6) {
                    logger.error("Can't create ticket entity: args amount is not equals 6");
                    continue;
                }

                try {
                    TicketService ticketService = new TicketService();
                    MovieShowService movieShowService = new MovieShowService();
                    CinemaService cinemaService = new CinemaService();
                    MovieService movieService = new MovieService();
                    int row = Integer.parseInt(splitter[4]);
                    int place = Integer.parseInt(splitter[5]);

                    if (row <= 0 || place <= 0) {
                        logger.error("Cant create ticket entity: row or place cannot be less than 0");
                        continue;
                    }

                    TicketEntity ticketEntity = new TicketEntity(movieShowService.findByMovieAndCinemaAndTime(movieService.findByName(splitter[1]), cinemaService.findByName(splitter[2]), LocalTime.parse(splitter[3])), row, place);

                    boolean flag = false;
                    for (TicketEntity item: ticketService.findAll()) {
                        if (item.getMovieShowEntity().getId() == ticketEntity.getMovieShowEntity().getId()
                        && item.getRow() == ticketEntity.getRow() && item.getPlace() == ticketEntity.getPlace()) {
                            logger.error("Cant create ticket entity: place is already taken");
                            flag = true;
                            break;
                        }
                    }

                    if (flag) {
                        continue;
                    }

                    ticketService.create(ticketEntity);

                    PaymentService paymentService = new PaymentService();
                    PaymentEntity paymentEntity = new PaymentEntity(new Random().nextInt(300 - 50) + 50, RandomCreditCardNumberGenerator.getRandomCard());
                    paymentService.create(paymentEntity);

                    ticketEntity.setPaymentEntity(paymentService.getLastPayment());

                    ticketService.updateStatus(ticketEntity, TicketStatus.PROCESSING);
                } catch (EntityNotFoundException  | NumberFormatException e) {
                    logger.error(e);
                }
            } else {
                logger.error("No such command called " + splitter[0]);
            }
        }
    }
}
