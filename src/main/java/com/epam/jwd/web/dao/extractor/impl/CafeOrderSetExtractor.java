package com.epam.jwd.web.dao.extractor.impl;

import com.epam.jwd.web.dao.extractor.ResultSetExtractor;
import com.epam.jwd.web.model.impl.CafeOrder;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CafeOrderSetExtractor implements ResultSetExtractor<CafeOrder> {

    private static final String ID_FIELD_NAME = "id";

    private static final String PRICE_FIELD_NAME = "price";
    private static final String DATE_FIELD_NAME = "date";
    private static final String REVIEW_FIELD_NAME = "review";
    private static final String RATING_FIELD_NAME = "rating";

    @Override
    public CafeOrder extract(ResultSet resultSet) throws SQLException {
        return CafeOrder.of(
                resultSet.getLong(ID_FIELD_NAME),
                resultSet.getBigDecimal(PRICE_FIELD_NAME),
                resultSet.getDate(DATE_FIELD_NAME),
                resultSet.getString(REVIEW_FIELD_NAME),
                resultSet.getInt(RATING_FIELD_NAME)
        );
    }
}
