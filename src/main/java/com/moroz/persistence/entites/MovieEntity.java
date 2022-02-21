package com.moroz.persistence.entites;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MovieEntity that = (MovieEntity) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return "MovieEntity{" +
                "name='" + name + '\'' +
                '}';
    }
}
