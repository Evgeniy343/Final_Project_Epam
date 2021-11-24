package com.epam.jwd.web.model.impl;

import com.epam.jwd.web.model.SimpleEntity;

import java.util.Objects;

public class City implements SimpleEntity {

    private final Long id;
    private final String name;

    private City(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public static City of(Long id, String name) {
        return new City(id,name);
    }



    @Override
    public Long getId() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        City city = (City) o;
        return Objects.equals(id, city.id) && Objects.equals(name, city.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public String toString() {
        return "City{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
