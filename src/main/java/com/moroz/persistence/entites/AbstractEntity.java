package com.moroz.persistence.entites;

/**
 * @author : anton
 * @since : 01.02.2022, вт
 **/
public abstract class AbstractEntity implements Entity{
    private long id;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
