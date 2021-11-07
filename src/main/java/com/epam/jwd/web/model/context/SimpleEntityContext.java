package com.epam.jwd.web.model.context;

import java.util.Objects;

public class SimpleEntityContext {
    private final Long id;
    private final String name;

    private SimpleEntityContext(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public static SimpleEntityContext of(Long id, String name){
        return new SimpleEntityContext(id,name);
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SimpleEntityContext that = (SimpleEntityContext) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public String toString() {
        return "SimpleEntityContext{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
