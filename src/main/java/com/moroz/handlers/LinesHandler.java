package com.moroz.handlers;

import com.moroz.commands.FileCommands;
import com.moroz.exceptions.NoSplitterException;
import com.moroz.exceptions.NoSuchCommandException;
import com.moroz.logging.CustomLogger;
import com.moroz.persistence.entites.UserEntity;
import com.moroz.resolvers.UserRegister;

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

    public void handle() throws NoSplitterException {
        for (String line : fileLines) {
            if (!line.contains("|")) {
                throw new NoSplitterException("No \"|\" splitter in line");
            }

            String[] splitter = line.split("\\|");

            if (splitter[0].equals(FileCommands.REGISTER.toString())) {
                UserRegister userRegister = new UserRegister();
                userRegister.register(new UserEntity(splitter[1], splitter[2], splitter[3]));
            } else {
                try {
                    throw new NoSuchCommandException();
                } catch (NoSuchCommandException e) {
                    logger.error(e);
                }
            }
        }
    }
}
