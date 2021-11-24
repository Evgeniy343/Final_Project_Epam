package com.epam.jwd.web.model;

import java.util.Objects;

public class Address implements Entity {
    private Long id;
    private City city;
    private Street street;
    private String house;
    private Integer flat;


    private Address(Long id, City city, Street street, String house, Integer flat) {
        this.id = id;
        this.city = city;
        this.street = street;
        this.house = house;
        this.flat = flat;
    }

    private Address() {

    }

    static Address of() {
        return new Address();
    }

    public static Builder with() {
        return new Builder();
    }

    public static final class Builder {
        private Long id;
        private City city;
        private Street street;
        private String house;
        private Integer flat;

        public Builder() {
        }

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder city(City city) {
            this.city = city;
            return this;
        }

        public Builder street(Street street) {
            this.street = street;
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

        public Address build() {
            return new Address(id, city, street, house, flat);
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
        Address address = (Address) o;
        return Objects.equals(id, address.id)
                && Objects.equals(city, address.city)
                && Objects.equals(street, address.street)
                && Objects.equals(house, address.house)
                && Objects.equals(flat, address.flat);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, city, street, house, flat);
    }

    @Override
    public String toString() {
        return "Address{" +
                "id=" + id +
                ", city=" + city +
                ", street=" + street +
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

    public City getCity() {
        return city;
    }

    public Street getStreet() {
        return street;
    }
}
