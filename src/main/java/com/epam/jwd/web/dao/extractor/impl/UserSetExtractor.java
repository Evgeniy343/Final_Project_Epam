package com.epam.jwd.web.dao.extractor.impl;

import com.epam.jwd.web.dao.extractor.ResultSetExtractor;
import com.epam.jwd.web.model.TypeModel;
import com.epam.jwd.web.model.context.EntityContext;
import com.epam.jwd.web.model.exception.EntityNotFoundException;
import com.epam.jwd.web.model.factory.EntityFactory;
import com.epam.jwd.web.model.impl.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserSetExtractor implements ResultSetExtractor<User> {

    private static final String ID_FIELD_NAME = "id";
    private static final String LOGIN_FIELD_NAME = "login";
    private static final String PASSWORD_FIELD_NAME = "password";

    private UserSetExtractor() {
    }

    public static UserSetExtractor of() {
        return new UserSetExtractor();
    }

    @Override
    public User extract(ResultSet resultSet, EntityFactory factory) throws SQLException, EntityNotFoundException {
        EntityContext userContext = pushUserContext(resultSet);
        return (User) factory.createModel(TypeModel.USER_MODEL, userContext, null);
    }

    private EntityContext pushUserContext(ResultSet resultSet) throws SQLException {
        return EntityContext.with()
                .id(resultSet.getLong(ID_FIELD_NAME))
                .login(resultSet.getString(LOGIN_FIELD_NAME))
                .password(resultSet.getString(PASSWORD_FIELD_NAME))
                .build();
    }


}
