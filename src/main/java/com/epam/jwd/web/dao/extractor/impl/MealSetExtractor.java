package com.epam.jwd.web.dao.extractor.impl;

import com.epam.jwd.web.dao.extractor.ResultSetExtractor;
import com.epam.jwd.web.exception.EntityNotFoundException;
import com.epam.jwd.web.model.Category;
import com.epam.jwd.web.model.Meal;
import com.epam.jwd.web.model.SimpleEntity;
import com.epam.jwd.web.model.TypeEntity;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MealSetExtractor implements ResultSetExtractor<Meal> {
    private static final String ID_FIELD_NAME = "m.id";
    private static final String MEAL_NAME_FIELD_NAME = "m.name";
    private static final String PRICE_FIELD_NAME = "m.price";
    private static final String CATEGORY_NAME_FIELD_NAME = "cat.name";

    private MealSetExtractor() {
    }

    public static MealSetExtractor of() {
        return new MealSetExtractor();
    }

    @Override
    public Meal extract(ResultSet resultSet) throws SQLException, EntityNotFoundException {
        return Meal.with()
                .id(resultSet.getLong(ID_FIELD_NAME))
                .name(resultSet.getString(MEAL_NAME_FIELD_NAME))
                .price(resultSet.getInt(PRICE_FIELD_NAME))
                .category(createCategory(resultSet))
                .build();
    }

    private Category createCategory(ResultSet resultSet) throws EntityNotFoundException, SQLException {
        return (Category) SimpleEntity.newInstance(TypeEntity.CATEGORY, null
                , resultSet.getString(CATEGORY_NAME_FIELD_NAME));
    }

}
