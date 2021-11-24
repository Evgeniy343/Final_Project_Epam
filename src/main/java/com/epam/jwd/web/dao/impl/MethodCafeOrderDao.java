package com.epam.jwd.web.dao.impl;

import com.epam.jwd.web.dao.CafeOrderDao;
import com.epam.jwd.web.dao.CommonDao;
import com.epam.jwd.web.dao.extractor.impl.CafeOrderSetExtractor;
import com.epam.jwd.web.db.impl.ConnectionPool;
import com.epam.jwd.web.exception.EntityExtractorNotFoundException;
import com.epam.jwd.web.exception.EntityNotFoundException;
import com.epam.jwd.web.model.CafeOrder;
import org.apache.logging.log4j.LogManager;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import static java.lang.String.join;

public class MethodCafeOrderDao extends CommonDao<CafeOrder> implements CafeOrderDao {


    private static final String CAFE_ORDER_TABLE_NAME = "cafe_order order " +
            "join cafe_user u on u.id = order.user_id " +
            "join address ad on ad.id = order.address_id " +
            "join status st on st.id = order.status_id";
    private static final List<String> FIELDS = Arrays.asList("order.user_id", "st.s_name", "c.c_name", "str.s_name"
            , "ad.house", "ad.flat", "order.id", "order.price", "order.date", "order.review", "order.rating");
    private static final String ID_FIELD_NAME = "order.id";


    private MethodCafeOrderDao(ConnectionPool pool) throws EntityNotFoundException {
        super(pool, CafeOrderSetExtractor.of(), LogManager.getLogger(MethodCafeOrderDao.class));
    }

    public static MethodCafeOrderDao of(ConnectionPool pool)
            throws EntityExtractorNotFoundException, EntityNotFoundException {
        return new MethodCafeOrderDao(pool);
    }

    @Override
    protected String getTableName() {
        return CAFE_ORDER_TABLE_NAME;
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
    protected void fillEntity(PreparedStatement statement, CafeOrder entity) throws SQLException {
        statement.setLong(1, entity.getId());
        statement.setLong(2, entity.getUser().getId());
        statement.setLong(3, entity.getAddress().getId());
        statement.setFloat(4, entity.getPrice());
        statement.setDate(5, entity.getDate());
        statement.setLong(6, entity.getStatus().ordinal());
        statement.setString(7, entity.getReview());
        statement.setInt(8, entity.getRating());
    }
}
