package com.epam.jwd.web.dao.extractor.impl;

import com.epam.jwd.web.dao.extractor.ResultSetExtractor;
import com.epam.jwd.web.exception.EntityNotFoundException;
import com.epam.jwd.web.model.Bonus;
import com.epam.jwd.web.model.SimpleEntity;
import com.epam.jwd.web.model.TypeEntity;
import com.epam.jwd.web.model.UserDetails;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDetailsSetExtractor implements ResultSetExtractor<UserDetails> {
    private static final String ID_FIELD_NAME = "ud.id";
    private static final String POINT_FIELD_NAME = "ud.point";
    private static final String BAN_FIELD_NAME = "ud.ban";
    private static final String BONUS_NAME_FIELD_NAME = "b.name";

    private UserDetailsSetExtractor() {
    }

    public static UserDetailsSetExtractor of() {
        return new UserDetailsSetExtractor();
    }

    @Override
    public UserDetails extract(ResultSet resultSet) throws SQLException, EntityNotFoundException {
        return UserDetails.with()
                .id(resultSet.getLong(ID_FIELD_NAME))
                .point(resultSet.getInt(POINT_FIELD_NAME))
                .ban(resultSet.getBoolean(BAN_FIELD_NAME))
                .bonus(createBonus(resultSet))
                .build();
    }

    private Bonus createBonus(ResultSet resultSet) throws EntityNotFoundException, SQLException {
        return (Bonus) SimpleEntity.newInstance(TypeEntity.BONUS, null, resultSet.getString(BONUS_NAME_FIELD_NAME));
    }
}
