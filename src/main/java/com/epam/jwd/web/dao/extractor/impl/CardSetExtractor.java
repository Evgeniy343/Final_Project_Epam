package com.epam.jwd.web.dao.extractor.impl;

import com.epam.jwd.web.dao.extractor.ResultSetExtractor;
import com.epam.jwd.web.model.Card;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CardSetExtractor implements ResultSetExtractor<Card> {
    private static final String ID_FIELD_NAME = "id";
    private static final String AMOUNT_FIELD_NAME = "amount";

    private CardSetExtractor() {
    }

    public static CardSetExtractor of() {
        return new CardSetExtractor();
    }

    @Override
    public Card extract(ResultSet resultSet) throws SQLException {
        return Card.with()
                .id(resultSet.getLong(ID_FIELD_NAME))
                .amount(resultSet.getFloat(AMOUNT_FIELD_NAME))
                .build();
    }
}
