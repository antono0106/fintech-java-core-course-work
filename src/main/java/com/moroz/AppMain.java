package com.moroz;

import com.moroz.dispatcher.Dispatcher;
import com.moroz.exceptions.NotEnoughArgsException;
import com.moroz.exceptions.TooMuchArgsException;
import com.moroz.logging.CustomLogger;
import com.moroz.persistence.ConnectionUtil;

import java.io.IOException;

/**
 * Hello world!
 */
public class AppMain {
    static CustomLogger logger = new CustomLogger(AppMain.class);

    static {
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            logger.info("Closing connection and logger writer...");
            ConnectionUtil.closeConnection();
            CustomLogger.closeWriter();
        }));
    }

    public static void main(String[] args) {
        try {
            Dispatcher dispatcher = new Dispatcher(args);
            dispatcher.dispatch();
        } catch (TooMuchArgsException | IOException | NotEnoughArgsException e) {
            logger.error(e);
        }
    }
}
