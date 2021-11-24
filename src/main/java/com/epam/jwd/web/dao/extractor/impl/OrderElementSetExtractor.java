package com.epam.jwd.web.dao.extractor.impl;

import com.epam.jwd.web.dao.extractor.ResultSetExtractor;
import com.epam.jwd.web.model.OrderElement;

import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderElementExtractor implements ResultSetExtractor<OrderElement> {
    private static final String MEAL_PRICE_FIELD_NAME = "meal_price";
    private static final String MEAL_AMOUNT_FIELD_NAME = "meal_amount";
    private static final String MEAL_NAME_FIELD_NAME = "meal_name";


    private OrderElementExtractor() {
    }

    public static OrderElementExtractor of() {
        return new OrderElementExtractor();
    }

    @Override
    public OrderElement extract(ResultSet resultSet) throws SQLException {
        return OrderElement.with()
                .mealPrice(resultSet.getFloat(MEAL_PRICE_FIELD_NAME))
                .mealAmount(resultSet.getInt(MEAL_AMOUNT_FIELD_NAME))
                .mealName(resultSet.getString(MEAL_NAME_FIELD_NAME))
                .build();
    }
    }
