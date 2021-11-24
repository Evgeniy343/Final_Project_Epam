package com.epam.jwd.web.model.impl;

import com.epam.jwd.web.model.SimpleEntity;

import java.util.Objects;

public class Ingredient implements SimpleEntity {
    private final Long id;
    private final String name;

    private Ingredient(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public static Ingredient of(Long id, String name) {
        return new Ingredient(id,name);
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
        Ingredient that = (Ingredient) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public String toString() {
        return "Ingredient{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
