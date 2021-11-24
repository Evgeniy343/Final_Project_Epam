package com.epam.jwd.web.dao.impl;

import com.epam.jwd.web.dao.CommonDao;
import com.epam.jwd.web.dao.OrderElementDao;
import com.epam.jwd.web.dao.extractor.impl.OrderElementSetExtractor;
import com.epam.jwd.web.db.impl.ConnectionPool;
import com.epam.jwd.web.exception.EntityExtractorNotFoundException;
import com.epam.jwd.web.exception.EntityNotFoundException;
import com.epam.jwd.web.model.OrderElement;
import org.apache.logging.log4j.LogManager;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import static java.lang.String.join;

public class MethodOrderElementDao extends CommonDao<OrderElement> implements OrderElementDao {

    private static final String ADDRESS_TABLE_NAME = "order_element orel " +
            "join meal m on m.id = orel.meal_id " +
            "join cafe_order order on order.id = orel.order_id";
    private static final List<String> FIELDS = Arrays.asList("meal_price", "meal_amount", "meal_name");
    private static final String ID_FIELD_NAME = "orel.id";

    private MethodOrderElementDao(ConnectionPool pool) throws EntityNotFoundException {
        super(pool, OrderElementSetExtractor.of(), LogManager.getLogger(MethodOrderElementDao.class));
    }

    public static MethodOrderElementDao of(ConnectionPool pool) throws EntityExtractorNotFoundException
            , EntityNotFoundException {
        return new MethodOrderElementDao(pool);
    }

    @Override
    protected String getTableName() {
        return ADDRESS_TABLE_NAME;
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
    protected void fillEntity(PreparedStatement statement, OrderElement entity) throws SQLException {
        statement.setLong(1, entity.getId());
        statement.setLong(2, entity.getMeal().getId());
        statement.setFloat(3, entity.getMealPrice());
        statement.setInt(4, entity.getMealAmount());
        statement.setString(5, entity.getMealName());
        statement.setLong(6, entity.getOrder().getId());
    }
}
