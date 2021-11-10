package com.epam.jwd.web.dao.extractor.impl;

import com.epam.jwd.web.dao.extractor.ResultSetExtractor;
import com.epam.jwd.web.model.TypeModel;
import com.epam.jwd.web.model.context.EntityContext;
import com.epam.jwd.web.model.exception.EntityNotFoundException;
import com.epam.jwd.web.model.factory.EntityFactory;
import com.epam.jwd.web.model.impl.Card;

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
    public Card extract(ResultSet resultSet, EntityFactory factory) throws SQLException, EntityNotFoundException {
        EntityContext cardContext = pushCardContext(resultSet);
        return (Card) factory.createModel(TypeModel.CARD_MODEL, cardContext, null);
    }

    private EntityContext pushCardContext(ResultSet resultSet) throws SQLException {
        return EntityContext.with()
                .id(resultSet.getLong(ID_FIELD_NAME))
                .cardAmount(resultSet.getFloat(AMOUNT_FIELD_NAME))
                .build();
    }
}
