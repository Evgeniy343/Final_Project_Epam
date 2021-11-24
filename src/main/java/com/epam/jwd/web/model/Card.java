package com.epam.jwd.web.model.impl;

import com.epam.jwd.web.model.Entity;

import java.util.Objects;

public class Card implements Entity {

    private final Long id;
    private final Float amount;

    private Card(Long id, Float amount) {
        this.id = id;
        this.amount = amount;
    }


    public static Builder with() {
        return new Builder();
    }

    public static final class Builder {
        private Long id;
        private Float amount;

        public Builder() {
        }

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder amount(Float amount) {
            this.amount = amount;
            return this;
        }

        public Card build() {
            return new Card(id, amount);
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
        Card card = (Card) o;
        return Objects.equals(id, card.id) && Objects.equals(amount, card.amount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, amount);
    }

    @Override
    public String toString() {
        return "Card{" +
                "id=" + id +
                ", amount=" + amount +
                '}';
    }

    public Float getAmount() {
        return amount;
    }
}
