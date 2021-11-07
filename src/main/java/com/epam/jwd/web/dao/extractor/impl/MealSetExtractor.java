package com.epam.jwd.web.dao.extractor.impl;

import com.epam.jwd.web.dao.extractor.ResultSetExtractor;
import com.epam.jwd.web.model.impl.Meal;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MealSetExtractor implements ResultSetExtractor<Meal> {
    private static final String ID_FIELD_NAME = "id";
    private static final String NAME_FIELD_NAME = "name";
    private static final String PRICE_FIELD_NAME = "price";

    @Override
    public Meal extract(ResultSet resultSet) throws SQLException {
        return Meal.of(
                resultSet.getLong(ID_FIELD_NAME),
                resultSet.getString(NAME_FIELD_NAME),
                resultSet.getBigDecimal(PRICE_FIELD_NAME)
        );
    }
}
