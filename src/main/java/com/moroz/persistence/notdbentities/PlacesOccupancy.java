package com.moroz.persistence.notdbentities;

/**
 * @author : anton
 * @since : 20.02.2022, вс
 **/
public class PlacesOccupancy {
    private String cinemaName;
    private boolean[][] placesOccupancy;

    public PlacesOccupancy(String cinemaName, boolean[][] placesOccupancy) {
        this.cinemaName = cinemaName;
        this.placesOccupancy = placesOccupancy;
    }

    public String getCinemaName() {
        return cinemaName;
    }

    public boolean[][] getPlacesOccupancy() {
        return placesOccupancy;
    }
}
