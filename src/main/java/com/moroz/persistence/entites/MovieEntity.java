package com.moroz.persistence.entites;

/**
 * @author : anton
 * @since : 01.02.2022, вт
 **/
public class MovieEntity extends AbstractEntity implements Entity {
    private String name;

    public MovieEntity(String name) {
        this.name = name;
    }

    public MovieEntity() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
