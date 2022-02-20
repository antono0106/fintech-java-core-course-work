package com.moroz.logging;

import com.moroz.persistence.ConnectionUtil;
import com.moroz.persistence.notdbentities.PlacesOccupancy;

import java.io.*;
import java.sql.Connection;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

/**
 * @author : anton
 * @since : 01.02.2022, вт
 **/
public class CustomLogger {
    private static final String FILE_NAME = "src" + File.separator + "main" + File.separator + "resources" + File.separator + "logs.log";
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

    public void error(Throwable t) {
        writer.printf("%s ERROR FROM %s: Exception: %s, stacktrace:\n %s \n", LocalDateTime.now(), clazz.getSimpleName(), t, Arrays.toString(t.getStackTrace()));
        writer.flush();
    }

    public void warning(String warning) {
        writer.printf("%s WARNING FROM %s: %s \n", LocalDateTime.now(), clazz.getSimpleName(), warning);
        writer.flush();
    }

    public void printPlacesOccupancy(List<PlacesOccupancy> placesOccupancies) {
        for (PlacesOccupancy placeOccupancy: placesOccupancies) {
            writer.println(placeOccupancy.getCinemaName());
            for (int i = 0; i < placeOccupancy.getPlacesOccupancy().length; i++) {
                String rowOccupancy = Arrays.toString(placeOccupancy.getPlacesOccupancy()[i]).replaceAll("true", "1");
                writer.println(rowOccupancy);
            }
        }
        writer.flush();
    }

    public static void closeWriter() {
        writer.printf("%s INFO FROM %s: %s \n", LocalDateTime.now(), CustomLogger.class.getSimpleName(),"Closed logger writer");
        writer.close();
    }
}
