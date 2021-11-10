package com.epam.jwd.web.dao.extractor.impl;

import com.epam.jwd.web.dao.extractor.ResultSetExtractor;
import com.epam.jwd.web.model.TypeModel;
import com.epam.jwd.web.model.context.EntityContext;
import com.epam.jwd.web.model.exception.EntityNotFoundException;
import com.epam.jwd.web.model.factory.EntityFactory;
import com.epam.jwd.web.model.impl.Meal;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MealSetExtractor implements ResultSetExtractor<Meal> {
    private static final String ID_FIELD_NAME = "id";
    private static final String NAME_FIELD_NAME = "name";
    private static final String PRICE_FIELD_NAME = "price";

    private MealSetExtractor() {
    }

    public static MealSetExtractor of() {
        return new MealSetExtractor();
    }

    @Override
    public Meal extract(ResultSet resultSet, EntityFactory factory) throws SQLException, EntityNotFoundException {
        EntityContext mealContext = pushMealContext(resultSet);
        return (Meal) factory.createModel(TypeModel.MEAL_MODEL, mealContext, null);
    }

    private EntityContext pushMealContext(ResultSet resultSet) throws SQLException {
        return EntityContext.with()
                .id(resultSet.getLong(ID_FIELD_NAME))
                .mealName(resultSet.getString(NAME_FIELD_NAME))
                .mealPrice(resultSet.getBigDecimal(PRICE_FIELD_NAME))
                .build();
    }
}
