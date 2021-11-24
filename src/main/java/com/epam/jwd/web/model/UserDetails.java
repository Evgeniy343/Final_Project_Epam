package com.epam.jwd.web.model;

import java.util.Objects;

public class UserDetails implements Entity {
    private Long id;
    private Integer point;
    private Bonus bonus;
    private Boolean ban;
    private User user;

    private UserDetails(Long id, Integer point, Bonus bonus, Boolean ban, User user) {
        this.id = id;
        this.point = point;
        this.bonus = bonus;
        this.ban = ban;
        this.user = user;
    }

    private UserDetails() {

    }

    static UserDetails of() {
        return new UserDetails();
    }

    public static Builder with() {
        return new Builder();
    }

    public static final class Builder {
        private Long id;
        private Integer point;
        private Bonus bonus;
        private Boolean ban;
        private User user;

        public Builder() {
        }

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder point(Integer point) {
            this.point = point;
            return this;
        }

        public Builder bonus(Bonus bonus) {
            this.bonus = bonus;
            return this;
        }

        public Builder ban(Boolean ban) {
            this.ban = ban;
            return this;
        }

        public Builder user(User user) {
            this.user = user;
            return this;
        }

        public UserDetails build() {
            return new UserDetails(id, point, bonus, ban, user);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserDetails that = (UserDetails) o;
        return Objects.equals(id, that.id)
                && Objects.equals(point, that.point)
                && Objects.equals(bonus, that.bonus)
                && Objects.equals(ban, that.ban)
                && Objects.equals(user, that.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, point, bonus, ban, user);
    }

    @Override
    public String toString() {
        return "UserDetails{" +
                "id=" + id +
                ", point=" + point +
                ", bonus=" + bonus +
                ", ban=" + ban +
                ", user=" + user +
                '}';
    }

    @Override
    public Long getId() {
        return id;
    }

    public Integer getPoint() {
        return point;
    }

    public Boolean getBan() {
        return ban;
    }

    public Bonus getBonus() {
        return bonus;
    }

    public User getUser() {
        return user;
    }
}
