package com.epam.jwd.web.model.impl;

import com.epam.jwd.web.model.Entity;

import java.util.Objects;

public class User implements Entity {

    private final Long id;
    private final String login;
    private final String password;

    private User(Long id, String login, String password) {
        this.id = id;
        this.login = login;
        this.password = password;
    }

    public static User of(Long id, String login, String password){
        return new User(id,login,password);
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) && Objects.equals(login, user.login) && Objects.equals(password, user.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, login, password);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }
}
