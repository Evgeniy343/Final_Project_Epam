package com.epam.jwd.web.model.context;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

public class EntityContext {
    private final Long id;
    private final String house;
    private final Integer flat;
    private final BigDecimal cafeOrderPrice;
    private final Date date;
    private final String review;
    private final Integer rating;
    private final Float amount;
    private final String mealName;
    private final BigDecimal mealPrice;
    private final String login;
    private final String password;
    private final Integer point;
    private final Boolean ban;

    private EntityContext(Long id, String house, Integer flat, BigDecimal cafeOrderPrice, Date date
            , String review, Integer rating, Float amount, String mealName, BigDecimal mealPrice, String login
            , String password, Integer point, Boolean ban) {
        this.id = id;
        this.house = house;
        this.flat = flat;
        this.cafeOrderPrice = cafeOrderPrice;
        this.date = date;
        this.review = review;
        this.rating = rating;
        this.amount = amount;
        this.mealName = mealName;
        this.mealPrice = mealPrice;
        this.login = login;
        this.password = password;
        this.point = point;
        this.ban = ban;
    }

    public static EntityContext of(Long id, String house, Integer flat, BigDecimal cafeOrderPrice, Date date
            , String review, Integer rating, Float amount, String mealName, BigDecimal mealPrice, String login
            , String password, Integer point, Boolean ban){
        return new EntityContext(id,house,flat,cafeOrderPrice,date,review,rating,amount,mealName,mealPrice
                ,login,password,point,ban);
    }

    public Long getId() {
        return id;
    }

    public String getHouse() {
        return house;
    }

    public Integer getFlat() {
        return flat;
    }

    public BigDecimal getCafeOrderPrice() {
        return cafeOrderPrice;
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

    public Float getAmount() {
        return amount;
    }

    public String getMealName() {
        return mealName;
    }

    public BigDecimal getMealPrice() {
        return mealPrice;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public Integer getPoint() {
        return point;
    }

    public Boolean getBan() {
        return ban;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EntityContext that = (EntityContext) o;
        return Objects.equals(id, that.id)
                && Objects.equals(house, that.house)
                && Objects.equals(flat, that.flat)
                && Objects.equals(cafeOrderPrice, that.cafeOrderPrice)
                && Objects.equals(date, that.date)
                && Objects.equals(review, that.review)
                && Objects.equals(rating, that.rating)
                && Objects.equals(amount, that.amount)
                && Objects.equals(mealName, that.mealName)
                && Objects.equals(mealPrice, that.mealPrice)
                && Objects.equals(login, that.login)
                && Objects.equals(password, that.password)
                && Objects.equals(point, that.point)
                && Objects.equals(ban, that.ban);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, house, flat, cafeOrderPrice, date, review, rating, amount, mealName
                , mealPrice, login, password, point, ban);
    }

    @Override
    public String toString() {
        return "EntityContext{" +
                "id=" + id +
                ", house='" + house + '\'' +
                ", flat=" + flat +
                ", cafeOrderPrice=" + cafeOrderPrice +
                ", date=" + date +
                ", review='" + review + '\'' +
                ", rating=" + rating +
                ", amount=" + amount +
                ", mealName='" + mealName + '\'' +
                ", mealPrice=" + mealPrice +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", point=" + point +
                ", ban=" + ban +
                '}';
    }
}