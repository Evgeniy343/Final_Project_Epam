package com.epam.jwd.web.dao.extractor.impl;

import com.epam.jwd.web.dao.extractor.ResultSetExtractor;
import com.epam.jwd.web.model.Role;
import com.epam.jwd.web.model.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserSetExtractor implements ResultSetExtractor<User> {

    private static final String ID_FIELD_NAME = "u.id";
    private static final String LOGIN_FIELD_NAME = "u.login";
    private static final String PASSWORD_FIELD_NAME = "u.password";
    private static final String ROLE_FIELD_NAME = "r.name";

    private UserSetExtractor() {
    }

    public static ResultSetExtractor<User> of() {
        return new UserSetExtractor();
    }

    @Override
    public User extract(ResultSet resultSet) throws SQLException {
        return User.with()
                .id(resultSet.getLong(ID_FIELD_NAME))
                .login(resultSet.getString(LOGIN_FIELD_NAME))
                .password(resultSet.getString(PASSWORD_FIELD_NAME))
                .role(Role.of(resultSet.getString(ROLE_FIELD_NAME)))
                .build();
    }


}
