package com.moroz;

import com.moroz.commands.FileCommands;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * @author : anton
 * @since : 11.02.2022, пт
 **/
public class EnumTest {
    @Test
    public void printTest() {
        assertEquals(FileCommands.BUY_TICKET.toString(),"BUY_TICKET");
    }
}
