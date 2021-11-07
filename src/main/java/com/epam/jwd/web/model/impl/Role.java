package com.epam.jwd.web.model.impl;

import com.epam.jwd.web.model.SimpleEntity;

import java.util.Objects;

public class Role implements SimpleEntity {
    private final Long id;
    private final String name;

    private Role(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public static Role of(Long id, String name) {
        return new Role(id,name);
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
        Role role = (Role) o;
        return Objects.equals(id, role.id) && Objects.equals(name, role.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
