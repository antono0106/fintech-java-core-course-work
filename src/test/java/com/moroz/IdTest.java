package com.moroz;

import com.moroz.persistence.entites.MovieEntity;
import org.junit.Test;
import static org.junit.Assert.*;
/**
 * @author : anton
 * @since : 03.02.2022, чт
 **/
public class IdTest {
    @Test
    public void IdTest() {
        MovieEntity movieEntity = new MovieEntity("SomeMovie");
        MovieEntity movieEntity1 = new MovieEntity("SomeMovie1");

        assertEquals(1, movieEntity.getId());
        assertEquals(2, movieEntity1.getId());
    }
}
