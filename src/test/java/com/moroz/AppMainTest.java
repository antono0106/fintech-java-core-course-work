package com.moroz;

import static org.junit.Assert.assertTrue;

import com.moroz.logging.CustomLogger;
import org.junit.Test;

import java.io.FileReader;

/**
 * Unit test for simple AppMain.
 */
public class AppMainTest
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue() {
        CustomLogger customLogger = new CustomLogger(Object.class);
        customLogger.info("Some info");
    }
}
