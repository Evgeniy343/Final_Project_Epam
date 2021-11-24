package com.epam.jwd.web.model.impl;

import com.epam.jwd.web.model.SimpleEntity;

import java.util.Objects;

public class Status implements SimpleEntity {
    private final Long id;
    private final String name;

    private Status(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public static Status of(Long id, String name) {
        return new Status(id,name);
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
        Status status = (Status) o;
        return Objects.equals(id, status.id) && Objects.equals(name, status.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public String toString() {
        return "Status{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
