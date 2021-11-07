package com.epam.jwd.web.model.impl;

import com.epam.jwd.web.model.Entity;

import java.math.BigDecimal;
import java.util.Objects;

public class Meal implements Entity {
    private final Long id;
    private final String name;
    private final BigDecimal price;

    private Meal(Long id, String name, BigDecimal price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public static Meal of(Long id, String name, BigDecimal price){
        return new Meal(id,name,price);
    }

    @Override
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
        Meal meal = (Meal) o;
        return Objects.equals(id, meal.id) && Objects.equals(name, meal.name) && Objects.equals(price, meal.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, price);
    }

    @Override
    public String toString() {
        return "Meal{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                '}';
    }

    public BigDecimal getPrice() {
        return price;
    }
}
