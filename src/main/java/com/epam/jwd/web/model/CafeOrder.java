package com.epam.jwd.web.model.impl;

import com.epam.jwd.web.model.Entity;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

public class CafeOrder implements Entity {

    private final Long id;
    private final User user;
    private final Address address;
    private final BigDecimal price;
    private final Date date;
    private final Status status;
    private final String review;
    private final Integer rating;

    private CafeOrder(Long id, User user, Address address, BigDecimal price, Date date, Status status
            , String review, Integer rating) {
        this.id = id;
        this.user = user;
        this.address = address;
        this.price = price;
        this.date = date;
        this.status = status;
        this.review = review;
        this.rating = rating;
    }

    public static Builder with() {
        return new Builder();
    }

    public static final class Builder {
        private Long id;
        private User user;
        private Address address;
        private BigDecimal price;
        private Date date;
        private Status status;
        private String review;
        private Integer rating;

        public Builder() {
        }

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder user(User user) {
            this.user = user;
            return this;
        }

        public Builder address(Address address) {
            this.address = address;
            return this;
        }

        public Builder price(BigDecimal price) {
            this.price = price;
            return this;
        }

        public Builder date(Date date) {
            this.date = date;
            return this;
        }

        public Builder status(Status status) {
            this.status = status;
            return this;
        }

        public Builder review(String review) {
            this.review = review;
            return this;
        }

        public Builder rating(Integer rating) {
            this.rating = rating;
            return this;
        }

        public CafeOrder build() {
            return new CafeOrder(id, user,address,price,date,status,review,rating);
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
        CafeOrder cafeOrder = (CafeOrder) o;
        return rating == cafeOrder.rating
                && Objects.equals(id, cafeOrder.id)
                && Objects.equals(user, cafeOrder.user)
                && Objects.equals(address, cafeOrder.address)
                && Objects.equals(price, cafeOrder.price)
                && Objects.equals(date, cafeOrder.date)
                && Objects.equals(status, cafeOrder.status)
                && Objects.equals(review, cafeOrder.review);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, user, address, price, date, status, review, rating);
    }

    @Override
    public String toString() {
        return "CafeOrder{" +
                "id=" + id +
                ", user=" + user +
                ", address=" + address +
                ", price=" + price +
                ", date=" + date +
                ", status=" + status +
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

    public Integer getRating() {
        return rating;
    }

    public User getUser() {
        return user;
    }

    public Address getAddress() {
        return address;
    }

    public Status getStatus() {
        return status;
    }
}
