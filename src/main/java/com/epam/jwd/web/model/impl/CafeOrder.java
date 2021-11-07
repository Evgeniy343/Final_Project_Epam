package com.epam.jwd.web.model.impl;

import com.epam.jwd.web.model.Entity;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

public class CafeOrder implements Entity {

    private final Long id;
    private final BigDecimal price;
    private final Date date;
    private final String review;
    private final int rating;

    private CafeOrder(Long id, BigDecimal price, Date date, String review, int rating) {
        this.id = id;
        this.price = price;
        this.date = date;
        this.review = review;
        this.rating = rating;
    }

    public static CafeOrder of(Long id, BigDecimal price, Date date, String review, int rating){
        return new CafeOrder(id,price,date,review,rating);
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CafeOrder cafeOrder = (CafeOrder) o;
        return rating == cafeOrder.rating
                && Objects.equals(id, cafeOrder.id)
                && Objects.equals(price, cafeOrder.price)
                && Objects.equals(date, cafeOrder.date)
                && Objects.equals(review, cafeOrder.review);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, price, date, review, rating);
    }

    @Override
    public String toString() {
        return "CafeOrder{" +
                "id=" + id +
                ", price=" + price +
                ", date=" + date +
                ", review='" + review + '\'' +
                ", rating=" + rating +
                '}';
    }

    public BigDecimal getPrice() {
        return price;
    }

    public Date getDate() {
        return date;
    }

    public String getReview() {
        return review;
    }

    public int getRating() {
        return rating;
    }
}
