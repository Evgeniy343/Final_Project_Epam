package com.epam.jwd.web.dao.extractor.impl;

import com.epam.jwd.web.dao.extractor.ResultSetExtractor;
import com.epam.jwd.web.exception.EntityNotFoundException;
import com.epam.jwd.web.model.*;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CafeOrderSetExtractor implements ResultSetExtractor<CafeOrder> {

    private static final String USER_ID_FIELD_NAME = "order.user_id";
    private static final String STATUS_NAME_FIELD_NAME = "st.s_name";
    private static final String CITY_NAME_FIELD_NAME = "c.c_name";
    private static final String STREET_NAME_FIELD_NAME = "str.s_name";
    private static final String HOUSE_NAME_FIELD_NAME = "ad.house";
    private static final String FLAT_NAME_FIELD_NAME = "ad.flat";
    private static final String ID_FIELD_NAME = "order.id";
    private static final String PRICE_FIELD_NAME = "order.price";
    private static final String DATE_FIELD_NAME = "order.date";
    private static final String REVIEW_FIELD_NAME = "order.review";
    private static final String RATING_FIELD_NAME = "order.rating";

    private CafeOrderSetExtractor() {

    }

    public static CafeOrderSetExtractor of() {
        return new CafeOrderSetExtractor();
    }

    @Override
    public CafeOrder extract(ResultSet resultSet) throws SQLException, EntityNotFoundException {
        return CafeOrder.with()
                .id(resultSet.getLong(ID_FIELD_NAME))
                .user(createUser(resultSet))
                .address(createAddress(resultSet))
                .price(resultSet.getFloat(PRICE_FIELD_NAME))
                .date(resultSet.getDate(DATE_FIELD_NAME))
                .status(Status.of(resultSet.getString(STATUS_NAME_FIELD_NAME)))
                .review(resultSet.getString(REVIEW_FIELD_NAME))
                .rating(resultSet.getInt(RATING_FIELD_NAME))
                .build();
    }

    private Address createAddress(ResultSet resultSet) throws EntityNotFoundException, SQLException {
        return Address.with()
                .city((City) SimpleEntity.newInstance(TypeEntity.CITY, 0L
                        , resultSet.getString(CITY_NAME_FIELD_NAME)))
                .street((Street) SimpleEntity.newInstance(TypeEntity.STREET, 0L
                        , resultSet.getString(STREET_NAME_FIELD_NAME)))
                .house(resultSet.getString(HOUSE_NAME_FIELD_NAME))
                .flat(resultSet.getInt(FLAT_NAME_FIELD_NAME))
                .build();
    }

    private User createUser(ResultSet resultSet) throws SQLException {
        return User.with()
                .id(resultSet.getLong(USER_ID_FIELD_NAME))
                .build();
    }

}
