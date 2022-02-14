package com.moroz.dispatcher;

import com.moroz.exceptions.NoSplitterException;
import com.moroz.exceptions.NotEnoughArgsException;
import com.moroz.exceptions.TooMuchArgsException;
import com.moroz.handlers.ArgsHandler;
import com.moroz.handlers.LinesHandler;
import com.moroz.logging.CustomLogger;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author : anton
 * @since : 14.02.2022, пн
 **/
public class Dispatcher {
    static CustomLogger logger = new CustomLogger(Dispatcher.class);

    private ArgsHandler argsHandler;

    public Dispatcher(String[] args) throws TooMuchArgsException, NotEnoughArgsException, IOException {
        argsHandler = new ArgsHandler(args);
    }

    public void dispatch() {
        try (Stream<String> lines = Files.lines(Paths.get(argsHandler.getFile().getAbsolutePath()))) {
            LinesHandler linesHandler = new LinesHandler(lines.collect(Collectors.toList()));
            linesHandler.handle();
        } catch(IOException | NoSplitterException e) {
            logger.error(e);
        }
    }
}
