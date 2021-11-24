package com.epam.jwd.web.dao.impl;

import com.epam.jwd.web.dao.CommonDao;
import com.epam.jwd.web.dao.UserDao;
import com.epam.jwd.web.dao.extractor.factory.ExtractorFactory;
import com.epam.jwd.web.db.impl.ConnectionPool;
import com.epam.jwd.web.exception.EntityExtractorNotFoundException;
import com.epam.jwd.web.exception.EntityNotFoundException;
import com.epam.jwd.web.model.TypeEntity;
import com.epam.jwd.web.model.User;
import org.apache.logging.log4j.LogManager;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import static java.lang.String.join;

public class MethodUserDao extends CommonDao<User> implements UserDao {

    private static final String USER_TABLE_NAME = "cafe_user u " +
            "join role r on r.id = u.role_id";
    private static final List<String> FIELDS = Arrays.asList("u.id", "u.login", "u.password", "r.name");
    private static final String ID_FIELD_NAME = "u.id";

    private MethodUserDao(ConnectionPool pool) throws EntityExtractorNotFoundException, EntityNotFoundException {
        super(pool, ExtractorFactory.newInstance().createExtractor(TypeEntity.USER)
                , LogManager.getLogger(MethodUserDao.class));
    }

    public static MethodUserDao of(ConnectionPool pool) throws EntityExtractorNotFoundException
            , EntityNotFoundException {
        return new MethodUserDao(pool);
    }

    @Override
    protected String getTableName() {
        return USER_TABLE_NAME;
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
    protected void fillEntity(PreparedStatement statement, User entity) throws SQLException {
        statement.setLong(1, entity.getId());
        statement.setString(2, entity.getLogin());
        statement.setString(3, entity.getPassword());
        statement.setLong(4, entity.getRole().ordinal());
    }
}
