package com.moroz.logging;

import java.io.*;
import java.time.LocalDateTime;

/**
 * @author : anton
 * @since : 01.02.2022, вт
 **/
public class CustomLogger {
    private static final String FILE_NAME = "logs.log";
    private static final File LOG = new File(FILE_NAME);
    private static PrintWriter writer;

    static {
        try {
            if (!LOG.exists()) {
                LOG.createNewFile();
            }
            writer = new PrintWriter(new FileWriter(LOG, true));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            writer.close();
        }));
    }

    private Class<?> clazz;

    public CustomLogger(Class<?> clazz) {
        this.clazz = clazz;
    }

    public File getLogFile() {
        return LOG;
    }

    public void info(String info) {
        writer.printf("%s INFO FROM %s: %s \n", LocalDateTime.now(), clazz.getSimpleName(), info);
        writer.flush();
    }

    public void error(String error) {
        writer.printf("%s ERROR FROM %s: %s \n", LocalDateTime.now(), clazz.getSimpleName(), error);
        writer.flush();
    }

    public void error(String error, Throwable t) {
        writer.printf("%s ERROR FROM %s: Exception: %s, message: %s \n", LocalDateTime.now(), clazz.getSimpleName(), t, error);
        writer.flush();
    }

    public void warning(String warning) {
        writer.printf("%s WARNING FROM %s: %s \n", LocalDateTime.now(), clazz.getSimpleName(), warning);
        writer.flush();
    }
}
