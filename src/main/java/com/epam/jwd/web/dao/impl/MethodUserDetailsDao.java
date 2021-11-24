package com.epam.jwd.web.dao.impl;

import com.epam.jwd.web.dao.CommonDao;
import com.epam.jwd.web.dao.UserDetailsDao;
import com.epam.jwd.web.dao.extractor.impl.UserDetailsSetExtractor;
import com.epam.jwd.web.db.impl.ConnectionPool;
import com.epam.jwd.web.exception.EntityExtractorNotFoundException;
import com.epam.jwd.web.exception.EntityNotFoundException;
import com.epam.jwd.web.model.UserDetails;
import org.apache.logging.log4j.LogManager;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import static java.lang.String.join;

public class MethodUserDetailsDao extends CommonDao<UserDetails> implements UserDetailsDao {

    private static final String USER_DETAILS_TABLE_NAME = "user_details ud " +
            "join bonus b on b.id = ud.bonus_id " +
            "join cafe_user u on u.id = ud.user_id";
    private static final List<String> FIELDS = Arrays.asList("ud.id", "ud.point", "ud.ban", "b.name");
    private static final String ID_FIELD_NAME = "ud.id";

    private MethodUserDetailsDao(ConnectionPool pool) throws EntityNotFoundException {
        super(pool, UserDetailsSetExtractor.of(), LogManager.getLogger(MethodUserDetailsDao.class));
    }

    public static MethodUserDetailsDao of(ConnectionPool pool) throws EntityExtractorNotFoundException
            , EntityNotFoundException {
        return new MethodUserDetailsDao(pool);
    }

    @Override
    protected String getTableName() {
        return USER_DETAILS_TABLE_NAME;
    }

    @Override
    protected List<String> getFields() {
        return FIELDS;
    }

    @Override
    protected String getIdFieldName() {
        return ID_FIELD_NAME;
    }

    @Override
    protected String getFieldForUpdate() {
        return join(" = ?, ", getFields());
    }

    @Override
    protected void fillEntity(PreparedStatement statement, UserDetails entity) throws SQLException {
        statement.setLong(1, entity.getId());
        statement.setInt(2, entity.getPoint());
        statement.setLong(3, entity.getBonus().getId());
        statement.setBoolean(4, entity.getBan());
        statement.setLong(5, entity.getUser().getId());
    }
}
