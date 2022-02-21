package com.moroz.handlers;

import com.moroz.exceptions.*;

import java.io.File;
import java.io.IOException;

/**
 * @author : anton
 * @since : 03.02.2022, чт
 **/
public class ArgsHandler {
    private File file;

    public ArgsHandler(String[] args) throws TooMuchArgsException, NotEnoughArgsException, IOException {
        handleArgs(args);
    }

    public File getFile() {
        return file;
    }

    private void handleArgs(String[] args) throws TooMuchArgsException, NotEnoughArgsException, IOException {
        if (args.length > 1) {
            throw new TooMuchArgsException();
        } else if (args.length <= 0) {
            throw new NotEnoughArgsException();
        }

        String fileName = args[0];

        if (!new File(fileName).isFile() || !new File(fileName).exists()) {
            throw new IOException("The arg is not a file or file does not exist");
        } else if(!fileName.endsWith(".txt")) {
            throw new UnsupportedFileExtension("Unsupported file extension called " + fileName.substring('.'));
        } else {
            file = new File(fileName);
        }
    }
}

