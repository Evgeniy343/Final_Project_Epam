package com.epam.jwd.web.model;

import java.util.Objects;

public class Bonus implements SimpleEntity {
    private final Long id;
    private final String name;

    private Bonus(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public static Bonus of(Long id, String name) {
        return new Bonus(id, name);
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
        Bonus bonus = (Bonus) o;
        return Objects.equals(id, bonus.id) && Objects.equals(name, bonus.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public String toString() {
        return "Bonus{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
