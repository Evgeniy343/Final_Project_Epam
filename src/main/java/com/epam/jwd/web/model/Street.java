package com.epam.jwd.web.model;

import java.util.Objects;

public class Street implements SimpleEntity {
    private final Long id;
    private final String name;

    private Street(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public static Street of(Long id, String name) {
        return new Street(id, name);
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
        Street street = (Street) o;
        return Objects.equals(id, street.id) && Objects.equals(name, street.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public String toString() {
        return "Street{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
