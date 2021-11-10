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
    private final Float cardAmount;
    private final String mealName;
    private final BigDecimal mealPrice;
    private final String login;
    private final String password;
    private final Integer point;
    private final Boolean ban;
    private final Double totalMealPrice;
    private final Integer mealAmount;

    private EntityContext(Long id, String house, Integer flat, BigDecimal cafeOrderPrice, Date date
            , String review, Integer rating, Float cardAmount, String mealName, BigDecimal mealPrice, String login
            , String password, Integer point, Boolean ban, Double totalMealPrice, Integer mealAmount) {
        this.id = id;
        this.house = house;
        this.flat = flat;
        this.cafeOrderPrice = cafeOrderPrice;
        this.date = date;
        this.review = review;
        this.rating = rating;
        this.cardAmount = cardAmount;
        this.mealName = mealName;
        this.mealPrice = mealPrice;
        this.login = login;
        this.password = password;
        this.point = point;
        this.ban = ban;
        this.totalMealPrice = totalMealPrice;
        this.mealAmount = mealAmount;
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
                && Objects.equals(cardAmount, that.cardAmount)
                && Objects.equals(mealName, that.mealName)
                && Objects.equals(mealPrice, that.mealPrice)
                && Objects.equals(login, that.login)
                && Objects.equals(password, that.password)
                && Objects.equals(point, that.point)
                && Objects.equals(ban, that.ban)
                && Objects.equals(totalMealPrice, that.totalMealPrice)
                && Objects.equals(mealAmount, that.mealAmount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, house, flat, cafeOrderPrice, date, review, rating, cardAmount, mealName, mealPrice, login, password, point, ban, totalMealPrice, mealAmount);
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
                ", cardAmount=" + cardAmount +
                ", mealName='" + mealName + '\'' +
                ", mealPrice=" + mealPrice +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", point=" + point +
                ", ban=" + ban +
                ", totalMealPrice=" + totalMealPrice +
                ", mealAmount=" + mealAmount +
                '}';
    }

    public static Builder with(){
        return new Builder();
    }

    public static final class Builder {
        private Long id;
        private String house;
        private Integer flat;
        private BigDecimal cafeOrderPrice;
        private Date date;
        private String review;
        private Integer rating;
        private Float cardAmount;
        private String mealName;
        private BigDecimal mealPrice;
        private String login;
        private String password;
        private Integer point;
        private Boolean ban;
        private Double totalMealPrice;
        private Integer mealAmount;

        public Builder() {
        }

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder house(String house) {
            this.house = house;
            return this;
        }

        public Builder flat(Integer flat) {
            this.flat = flat;
            return this;
        }

        public Builder cafeOrderPrice(BigDecimal cafeOrderPrice) {
            this.cafeOrderPrice = cafeOrderPrice;
            return this;
        }

        public Builder date(Date date) {
            this.date = date;
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

        public Builder cardAmount(Float cardAmount) {
            this.cardAmount = cardAmount;
            return this;
        }

        public Builder mealName(String mealName) {
            this.mealName = mealName;
            return this;
        }

        public Builder mealPrice(BigDecimal mealPrice) {
            this.mealPrice = mealPrice;
            return this;
        }

        public Builder login(String login) {
            this.login = login;
            return this;
        }

        public Builder password(String password) {
            this.password = password;
            return this;
        }

        public Builder point(Integer point) {
            this.point = point;
            return this;
        }

        public Builder ban(Boolean ban) {
            this.ban = ban;
            return this;
        }

        public Builder totalMealPrice(Double totalMealPrice) {
            this.totalMealPrice = totalMealPrice;
            return this;
        }

        public Builder mealAmount(Integer mealAmount) {
            this.mealAmount = mealAmount;
            return this;
        }

        public EntityContext build(){
            return new EntityContext(id, house, flat, cafeOrderPrice, date
                    , review, rating, cardAmount, mealName, mealPrice
                    , login, password, point, ban, totalMealPrice, mealAmount);
        }
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

    public Float getCardAmount() {
        return cardAmount;
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

    public Double getTotalMealPrice() {
        return totalMealPrice;
    }

    public Integer getMealAmount() {
        return mealAmount;
    }
}
