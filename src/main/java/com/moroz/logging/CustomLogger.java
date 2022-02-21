package com.moroz.logging;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.Arrays;

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

    public static void closeWriter() {
        writer.printf("%s INFO FROM %s: %s \n", LocalDateTime.now(), CustomLogger.class.getSimpleName(),"Closed logger writer");
        writer.close();
    }
}
