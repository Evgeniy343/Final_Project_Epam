package com.epam.jwd.web.dao.extractor.impl;

import com.epam.jwd.web.dao.extractor.ResultSetExtractor;
import com.epam.jwd.web.model.TypeModel;
import com.epam.jwd.web.model.context.EntityContext;
import com.epam.jwd.web.model.exception.EntityNotFoundException;
import com.epam.jwd.web.model.factory.EntityFactory;
import com.epam.jwd.web.model.impl.UserDetails;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDetailsSetExtractor implements ResultSetExtractor<UserDetails> {
    private static final String ID_FIELD_NAME = "id";
    private static final String POINT_FIELD_NAME = "point";
    private static final String BAN_FIELD_NAME = "ban";

    private UserDetailsSetExtractor() {
    }

    public static UserDetailsSetExtractor of() {
        return new UserDetailsSetExtractor();
    }

    @Override
    public UserDetails extract(ResultSet resultSet, EntityFactory factory) throws SQLException, EntityNotFoundException {
        EntityContext userDetailsContext = pushUserDetailsContext(resultSet);
        return (UserDetails) factory.createModel(TypeModel.USER_DETAILS_MODEL, userDetailsContext, null);
    }

    private EntityContext pushUserDetailsContext(ResultSet resultSet) throws SQLException {
        return EntityContext.with()
                .id(resultSet.getLong(ID_FIELD_NAME))
                .point(resultSet.getInt(POINT_FIELD_NAME))
                .ban(resultSet.getBoolean(BAN_FIELD_NAME))
                .build();
    }
}
