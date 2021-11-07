package com.epam.jwd.web.dao.extractor.impl;

import com.epam.jwd.web.dao.extractor.ResultSetExtractor;
import com.epam.jwd.web.model.impl.UserDetails;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDetailsSetExtractor implements ResultSetExtractor<UserDetails>{
    private static final String ID_FIELD_NAME = "id";
    private static final String POINT_FIELD_NAME = "point";
    private static final String BAN_FIELD_NAME = "ban";



    @Override
    public UserDetails extract(ResultSet resultSet) throws SQLException {
        return UserDetails.of(
                resultSet.getLong(ID_FIELD_NAME),
                resultSet.getInt(POINT_FIELD_NAME),
                resultSet.getBoolean(BAN_FIELD_NAME)
        );
    }
}
