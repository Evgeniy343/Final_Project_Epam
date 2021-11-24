package com.epam.jwd.web.model.impl;

import com.epam.jwd.web.model.Entity;

import java.math.BigDecimal;
import java.util.Objects;

public class Meal implements Entity {
    private final Long id;
    private final String name;
    private final BigDecimal price;
    private final Category category;

    private Meal(Long id, String name, BigDecimal price, Category category) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.category = category;
    }

    public static Builder with() {
        return new Builder();
    }

    public static final class Builder {
        private Long id;
        private String name;
        private BigDecimal price;
        private Category category;

        public Builder() {
        }

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder price(BigDecimal price) {
            this.price = price;
            return this;
        }

        public Builder category(Category category) {
            this.category = category;
            return this;
        }

        public Meal build() {
            return new Meal(id, name, price, category);
        }
    }


    @Override
    public Long getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Meal meal = (Meal) o;
        return Objects.equals(id, meal.id) && Objects.equals(name, meal.name) && Objects.equals(price, meal.price) && Objects.equals(category, meal.category);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, price, category);
    }

    @Override
    public String toString() {
        return "Meal{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", category=" + category +
                '}';
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public Category getCategory() {
        return category;
    }
}
