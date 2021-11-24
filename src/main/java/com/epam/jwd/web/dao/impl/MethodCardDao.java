package com.epam.jwd.web.dao.impl;

import com.epam.jwd.web.dao.CardDao;
import com.epam.jwd.web.dao.CommonDao;
import com.epam.jwd.web.dao.extractor.impl.CardSetExtractor;
import com.epam.jwd.web.db.impl.ConnectionPool;
import com.epam.jwd.web.exception.EntityExtractorNotFoundException;
import com.epam.jwd.web.exception.EntityNotFoundException;
import com.epam.jwd.web.model.Card;
import org.apache.logging.log4j.LogManager;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import static java.lang.String.join;

public class MethodCardDao extends CommonDao<Card> implements CardDao {
    private static final String CARD_TABLE_NAME = "card";
    private static final List<String> FIELDS = Arrays.asList("id", "amount");
    private static final String ID_FIELD_NAME = "id";

    private MethodCardDao(ConnectionPool pool) throws EntityNotFoundException {
        super(pool, CardSetExtractor.of(), LogManager.getLogger(MethodCardDao.class));
    }

    public static MethodCardDao of(ConnectionPool pool) throws EntityExtractorNotFoundException
            , EntityNotFoundException {
        return new MethodCardDao(pool);
    }


    @Override
    protected String getTableName() {
        return CARD_TABLE_NAME;
    }

    @Override
    protected List<String> getFields() {
        return FIELDS;
    }

    @Override
    protected String getIdFieldName() {
        return null;
    }

    @Override
    protected String getFieldForUpdate() {
        return join(" = ?, ", getFields());
    }

    @Override
    protected void fillEntity(PreparedStatement statement, Card entity) throws SQLException {
        statement.setLong(1, entity.getId());
        statement.setFloat(2, entity.getAmount());
    }
}
