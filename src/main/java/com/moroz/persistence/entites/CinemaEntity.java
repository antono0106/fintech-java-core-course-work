package com.moroz.persistence.entites;

import java.util.Objects;

/**
 * @author : anton
 * @since : 01.02.2022, вт
 **/
public class CinemaEntity extends AbstractEntity implements Entity {
    private String name;
    private final int rowsCount;
    private final int placesCount;

    private final boolean[][] placesOccupancy;

    public CinemaEntity(String name, int rowsCount, int placesCount) {
        this.name = name;
        this.rowsCount = rowsCount;
        this.placesCount = placesCount;
        placesOccupancy = new boolean[rowsCount][placesCount];
    }

    public CinemaEntity(String name) {
        this.name = name;
        this.rowsCount = 15;
        this.placesCount = 13;
        placesOccupancy = new boolean[15][13];
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRowsCount() {
        return rowsCount;
    }

    public int getPlacesCount() {
        return placesCount;
    }

    public boolean[][] getPlacesOccupancy() {
        return placesOccupancy;
    }

    public void takePlace(int row, int place) {
        if (!this.placesOccupancy[row - 1][place - 1]) {
            this.placesOccupancy[row - 1][place - 1] = true;
        }
    }

    public void freePlace(int row, int place) {
        if (this.placesOccupancy[row - 1][place - 1]) {
            this.placesOccupancy[row - 1][place - 1] = false;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CinemaEntity that = (CinemaEntity) o;
        return rowsCount == that.rowsCount && placesCount == that.placesCount && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, rowsCount, placesCount);
    }

    @Override
    public String toString() {
        return "CinemaEntity{" +
                "name='" + name + '\'' +
                ", rowsCount=" + rowsCount +
                ", placesCount=" + placesCount +
                '}';
    }
}
