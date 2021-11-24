package com.epam.jwd.web.model;

import java.util.Objects;

public class OrderElement implements Entity {

    private Meal meal;
    private Float mealPrice;
    private Integer mealAmount;
    private String mealName;
    private CafeOrder order;

    private OrderElement(Meal meal, Float mealPrice, Integer mealAmount, String mealName, CafeOrder order) {
        this.meal = meal;
        this.mealPrice = mealPrice;
        this.mealAmount = mealAmount;
        this.mealName = mealName;
        this.order = order;
    }

    private OrderElement() {

    }

    static OrderElement of() {
        return new OrderElement();
    }

    public static Builder with() {
        return new Builder();
    }

    public static final class Builder {
        private Meal meal;
        private Float mealPrice;
        private Integer mealAmount;
        private String mealName;
        private CafeOrder order;

        public Builder() {
        }

        public Builder meal(Meal meal) {
            this.meal = meal;
            return this;
        }

        public Builder mealPrice(Float mealPrice) {
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
            return new OrderElement(meal, mealPrice, mealAmount, mealName, order);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderElement that = (OrderElement) o;
        return Objects.equals(meal, that.meal)
                && Objects.equals(mealPrice, that.mealPrice)
                && Objects.equals(mealAmount, that.mealAmount)
                && Objects.equals(mealName, that.mealName)
                && Objects.equals(order, that.order);
    }

    @Override
    public int hashCode() {
        return Objects.hash(meal, mealPrice, mealAmount, mealName, order);
    }

    @Override
    public String toString() {
        return "OrderElement{" +
                "meal=" + meal +
                ", mealPrice=" + mealPrice +
                ", mealAmount=" + mealAmount +
                ", mealName='" + mealName + '\'' +
                ", order=" + order +
                '}';
    }

    @Override
    public Long getId() {
        throw new UnsupportedOperationException();
    }

    public Float getMealPrice() {
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
