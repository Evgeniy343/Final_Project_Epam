package com.epam.jwd.web.dao.extractor.impl;

import com.epam.jwd.web.dao.extractor.ResultSetExtractor;
import com.epam.jwd.web.model.TypeModel;
import com.epam.jwd.web.model.context.EntityContext;
import com.epam.jwd.web.model.exception.EntityNotFoundException;
import com.epam.jwd.web.model.factory.EntityFactory;
import com.epam.jwd.web.model.impl.CafeOrder;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CafeOrderSetExtractor implements ResultSetExtractor<CafeOrder> {

    private static final String ID_FIELD_NAME = "id";

    private static final String PRICE_FIELD_NAME = "price";
    private static final String DATE_FIELD_NAME = "date";
    private static final String REVIEW_FIELD_NAME = "review";
    private static final String RATING_FIELD_NAME = "rating";

    private CafeOrderSetExtractor() {
    }

    public static CafeOrderSetExtractor of() {
        return new CafeOrderSetExtractor();
    }

    @Override
    public CafeOrder extract(ResultSet resultSet, EntityFactory factory) throws SQLException, EntityNotFoundException {

        EntityContext cafeOrderContext = pushCafeOrderContext(resultSet);
        return (CafeOrder) factory.createModel(TypeModel.CAFE_ORDER_MODEL, cafeOrderContext, null);
    }

    private EntityContext pushCafeOrderContext(ResultSet resultSet) throws SQLException {
        return EntityContext.with()
                .id(resultSet.getLong(ID_FIELD_NAME))
                .cafeOrderPrice(resultSet.getBigDecimal(PRICE_FIELD_NAME))
                .date(resultSet.getDate(DATE_FIELD_NAME))
                .review(resultSet.getString(REVIEW_FIELD_NAME))
                .rating(resultSet.getInt(RATING_FIELD_NAME))
                .build();
    }
}
