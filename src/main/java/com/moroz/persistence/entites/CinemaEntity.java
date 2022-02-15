package com.moroz.persistence.entites;

/**
 * @author : anton
 * @since : 01.02.2022, вт
 **/
public class CinemaEntity extends AbstractEntity implements Entity {
    private String name;
    private int rowsCount;
    private int placesCount;

    public CinemaEntity(String name, int rowsCount, int placesCount) {
        this.name = name;
        this.rowsCount = rowsCount;
        this.placesCount = placesCount;
    }

    public CinemaEntity(String name) {
        this.name = name;
        this.rowsCount = 15;
        this.placesCount = 13;
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

    public void setRowsCount(int rowsCount) {
        this.rowsCount = rowsCount;
    }

    public int getPlacesCount() {
        return placesCount;
    }

    public void setPlacesCount(int placesCount) {
        this.placesCount = placesCount;
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
