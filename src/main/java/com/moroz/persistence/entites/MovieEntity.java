package com.moroz.persistence.entites;

/**
 * @author : anton
 * @since : 01.02.2022, вт
 **/
public class MovieEntity extends AbstractEntity implements Entity {
    private String name;

    private static int counter = 0;

    public MovieEntity(String name) {
        super(++counter);
        this.name = name;
    }

    public MovieEntity() {
        super(++counter);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
