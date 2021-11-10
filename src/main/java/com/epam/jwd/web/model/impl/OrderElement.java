package com.epam.jwd.web.model.impl;

import com.epam.jwd.web.model.Entity;

import java.util.Objects;

public class OrderElement implements Entity{

    private final Long id;
    private final Double mealPrice;
    private final Integer mealAmount;
    private final String mealName;

    private OrderElement(Long id, Double mealPrice, Integer mealAmount, String mealName) {
        this.id = id;
        this.mealPrice = mealPrice;
        this.mealAmount = mealAmount;
        this.mealName = mealName;
    }

    public static OrderElement of(Long id, Double mealPrice, Integer mealAmount, String mealName){
        return new OrderElement(id,mealPrice,mealAmount,mealName);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderElement that = (OrderElement) o;
        return Objects.equals(id, that.id) && Objects.equals(mealPrice, that.mealPrice) && Objects.equals(mealAmount, that.mealAmount) && Objects.equals(mealName, that.mealName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, mealPrice, mealAmount, mealName);
    }

    @Override
    public String toString() {
        return "OrderElement{" +
                "id=" + id +
                ", mealPrice=" + mealPrice +
                ", mealAmount=" + mealAmount +
                ", mealName='" + mealName + '\'' +
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
}
