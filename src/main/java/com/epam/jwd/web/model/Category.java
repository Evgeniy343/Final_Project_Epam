package com.epam.jwd.web.model;

import java.util.Objects;

public class Category implements SimpleEntity {

    private final Long id;
    private final String name;

    private Category(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public static Category of(Long id, String name) {
        return new Category(id, name);
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
        Category category = (Category) o;
        return Objects.equals(id, category.id) && Objects.equals(name, category.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
