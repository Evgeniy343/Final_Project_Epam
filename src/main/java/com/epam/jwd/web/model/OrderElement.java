package com.epam.jwd.web.model.impl;

import com.epam.jwd.web.model.Entity;

import java.util.Objects;

public class OrderElement implements Entity {

    private final Long id;
    private final Meal meal;
    private final Double mealPrice;
    private final Integer mealAmount;
    private final String mealName;
    private final CafeOrder order;

    private OrderElement(Long id, Meal meal, Double mealPrice, Integer mealAmount, String mealName, CafeOrder order) {
        this.id = id;
        this.meal = meal;
        this.mealPrice = mealPrice;
        this.mealAmount = mealAmount;
        this.mealName = mealName;
        this.order = order;
    }

    public static Builder with() {
        return new Builder();
    }

    public static final class Builder {
        private Long id;
        private Meal meal;
        private Double mealPrice;
        private Integer mealAmount;
        private String mealName;
        private CafeOrder order;

        public Builder() {
        }

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder meal(Meal meal) {
            this.meal = meal;
            return this;
        }

        public Builder mealPrice(Double mealPrice) {
            this.mealPrice = mealPrice;
            return this;
        }

        public Builder mealAmount(Integer mealAmount) {
            this.mealAmount = mealAmount;
            return this;
        }

        public Builder mealName(String mealName) {
            this.mealName = mealName;
            return this;
        }

        public Builder order(CafeOrder order) {
            this.order = order;
            return this;
        }

        public OrderElement build() {
            return new OrderElement(id, meal, mealPrice, mealAmount,mealName,order);
        }
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderElement that = (OrderElement) o;
        return Objects.equals(id, that.id)
                && Objects.equals(meal, that.meal)
                && Objects.equals(mealPrice, that.mealPrice)
                && Objects.equals(mealAmount, that.mealAmount)
                && Objects.equals(mealName, that.mealName)
                && Objects.equals(order, that.order);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, meal, mealPrice, mealAmount, mealName, order);
    }

    @Override
    public String toString() {
        return "OrderElement{" +
                "id=" + id +
                ", meal=" + meal +
                ", mealPrice=" + mealPrice +
                ", mealAmount=" + mealAmount +
                ", mealName='" + mealName + '\'' +
                ", order=" + order +
                '}';
    }

    @Override
    public Long getId() {
        return id;
    }

    public Double getMealPrice() {
        return mealPrice;
    }

    public Integer getMealAmount() {
        return mealAmount;
    }

    public String getMealName() {
        return mealName;
    }

    public Meal getMeal() {
        return meal;
    }

    public CafeOrder getOrder() {
        return order;
    }
}
