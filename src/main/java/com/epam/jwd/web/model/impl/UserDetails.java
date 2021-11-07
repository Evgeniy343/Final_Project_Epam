package com.epam.jwd.web.model.impl;

import com.epam.jwd.web.model.Entity;

import java.util.Objects;

public class UserDetails implements Entity {
    private final Long id;
    private final Integer point;
    private final Boolean ban;

    private UserDetails(Long id, Integer point, Boolean ban) {
        this.id = id;
        this.point = point;
        this.ban = ban;
    }

    public static UserDetails of(Long id, Integer point, Boolean ban){
        return new UserDetails(id,point,ban);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserDetails that = (UserDetails) o;
        return Objects.equals(point, that.point) && Objects.equals(ban, that.ban);
    }

    @Override
    public int hashCode() {
        return Objects.hash(point, ban);
    }

    @Override
    public String toString() {
        return "UserDetails{" +
                "point=" + point +
                ", ban=" + ban +
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
}
