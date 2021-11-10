package com.epam.jwd.web.dao.extractor.impl;

import com.epam.jwd.web.dao.extractor.ResultSetExtractor;
import com.epam.jwd.web.model.TypeModel;
import com.epam.jwd.web.model.context.EntityContext;
import com.epam.jwd.web.model.exception.EntityNotFoundException;
import com.epam.jwd.web.model.factory.EntityFactory;
import com.epam.jwd.web.model.impl.OrderElement;

import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderElementExtractor implements ResultSetExtractor<OrderElement> {
    private static final String ID_FIELD_NAME = "id";
    private static final String PRICE_FIELD_NAME = "name";
    private static final String AMOUNT_FIELD_NAME = "price";
    private static final String MEAL_NAME_FIELD_NAME = "price";

    private OrderElementExtractor() {
    }

    public static OrderElementExtractor of() {
        return new OrderElementExtractor();
    }

    @Override
    public OrderElement extract(ResultSet resultSet, EntityFactory factory) throws SQLException, EntityNotFoundException {
        EntityContext orderElementContext = pushOrderElementContext(resultSet);
        return (OrderElement) factory.createModel(TypeModel.CAFE_ORDER_MODEL, orderElementContext, null);
    }

    private EntityContext pushOrderElementContext(ResultSet resultSet) throws SQLException {
        return EntityContext.with()
                .id(resultSet.getLong(ID_FIELD_NAME))
                .totalMealPrice(resultSet.getDouble(PRICE_FIELD_NAME))
                .mealAmount(resultSet.getInt(AMOUNT_FIELD_NAME))
                .mealName(resultSet.getString(MEAL_NAME_FIELD_NAME))
                .build();
    }
}
