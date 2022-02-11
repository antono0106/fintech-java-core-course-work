package com.moroz.parsers;

import com.moroz.commands.FileCommands;
import com.moroz.exceptions.UnsupportedFileExtension;
import com.moroz.logging.CustomLogger;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author : anton
 * @since : 03.02.2022, чт
 **/
public class ArgsParser {
    static final CustomLogger logger = new CustomLogger(ArgsParser.class);

    private final List<FileCommands> fileCommands = List.of(FileCommands.REGISTER, FileCommands.CREATE_MOVIE,
            FileCommands.CREATE_CINEMA, FileCommands.BUY_TICKET, FileCommands.CREATE_MOVIE_SHOW);

    private File file;

    public ArgsParser(File file) throws UnsupportedFileExtension {
        if (!file.getName().endsWith(".txt")) {
            throw new UnsupportedFileExtension("This program does not support " + file.getName().substring(file.getName().indexOf('.')) + " extension");
        }
        this.file = file;
    }

    public void parse() {
        List<String> fileLines;
        try (Stream<String> lines = Files.lines(Paths.get(file.getName()))) {
            fileLines = lines.collect(Collectors.toList());
        } catch (IOException e) {
            logger.error(e);
            return;
        }


    }
}
