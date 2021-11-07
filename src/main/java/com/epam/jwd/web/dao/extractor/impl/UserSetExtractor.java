package com.epam.jwd.web.dao.extractor.impl;

import com.epam.jwd.web.dao.extractor.ResultSetExtractor;
import com.epam.jwd.web.model.impl.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserSetExtractor implements ResultSetExtractor<User> {

    private static final String ID_FIELD_NAME = "id";
    private static final String LOGIN_FIELD_NAME = "login";
    private static final String PASSWORD_FIELD_NAME = "password";

    public UserSetExtractor() {
    }

    @Override
    public User extract(ResultSet resultSet) throws SQLException {
        return User.of(
                resultSet.getLong(ID_FIELD_NAME),
                resultSet.getString(LOGIN_FIELD_NAME),
                resultSet.getString(PASSWORD_FIELD_NAME)
        );
    }
}
