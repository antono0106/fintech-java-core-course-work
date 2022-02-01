package com.moroz.logging;

import java.io.*;
import java.time.LocalDateTime;

/**
 * @author : anton
 * @since : 01.02.2022, вт
 **/
public class CustomLogger {
    private final String FILE_NAME = "src/main/resources/logs.log";
    private final File LOG = new File(FILE_NAME);

    private Class<?> clazz;

    private PrintWriter writer;

    private static CustomLogger logger;




    private CustomLogger(Class<?> clazz) {
        this.clazz = clazz;
        try {
            if (LOG.exists()) {
                LOG.createNewFile();
            }
            this.writer = new PrintWriter(LOG);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static CustomLogger getInstance(Class<?> clazz) {
        if (logger == null) {
            logger = new CustomLogger(clazz);
        }

        return logger;
    }

    public void info(String info) {
        this.writer.printf("%s INFO: %s \n", LocalDateTime.now(), info);
    }

    public void error(String error) {
        this.writer.printf("%s ERROR: %s \n", LocalDateTime.now(), error);
    }

    public void warning(String warning) {
        this.writer.printf("%s WARNING FROM %s: %s \n", LocalDateTime.now(), clazz.getSimpleName(), warning);
    }

    public void close() {
        /*Runtime runtime = Runtime.getRuntime()*/
    }
}
