package com.epam.jwd.web.model.impl;

import com.epam.jwd.web.model.Entity;

import java.util.Objects;

public class Address implements Entity {
    private final Long id;
    private final String house;
    private final Integer flat;


    private Address(Long id, String house, Integer flat) {
        this.id = id;
        this.house = house;
        this.flat = flat;
    }

    public static Address of(Long id, String house, Integer flat){
        return new Address(id,house,flat);
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return Objects.equals(id, address.id)
                && Objects.equals(house, address.house)
                && Objects.equals(flat, address.flat);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, house, flat);
    }

    @Override
    public String toString() {
        return "Address{" +
                "id=" + id +
                ", house='" + house + '\'' +
                ", flat=" + flat +
                '}';
    }

    public String getHouse() {
        return house;
    }

    public Integer getFlat() {
        return flat;
    }
}
