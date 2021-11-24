package com.epam.jwd.web.dao.extractor.impl;

import com.epam.jwd.web.dao.extractor.ResultSetExtractor;
import com.epam.jwd.web.exception.EntityExtractorNotFoundException;
import com.epam.jwd.web.exception.EntityNotFoundException;
import com.epam.jwd.web.model.SimpleEntity;
import com.epam.jwd.web.model.TypeEntity;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SimpleEntitySetExtractor implements ResultSetExtractor<SimpleEntity> {
    private static final String ID_FIELD_NAME = "id";
    private static final String NAME_FIELD_NAME = "name";

    private final TypeEntity type;

    private SimpleEntitySetExtractor(TypeEntity type) {
        this.type = type;
    }

    public static SimpleEntitySetExtractor of(TypeEntity type) {
        return new SimpleEntitySetExtractor(type);
    }

    @Override
    public SimpleEntity extract(ResultSet resultSet) throws SQLException, EntityNotFoundException
            , EntityExtractorNotFoundException {
        switch (type) {
            case CITY:
                return SimpleEntity.newInstance(TypeEntity.CITY, resultSet.getLong(ID_FIELD_NAME)
                        , resultSet.getString(NAME_FIELD_NAME));
            case BONUS:
                return SimpleEntity.newInstance(TypeEntity.BONUS, resultSet.getLong(ID_FIELD_NAME)
                        , resultSet.getString(NAME_FIELD_NAME));
            case STREET:
                return SimpleEntity.newInstance(TypeEntity.STREET, resultSet.getLong(ID_FIELD_NAME)
                        , resultSet.getString(NAME_FIELD_NAME));
            case INGREDIENT:
                return SimpleEntity.newInstance(TypeEntity.INGREDIENT, resultSet.getLong(ID_FIELD_NAME)
                        , resultSet.getString(NAME_FIELD_NAME));
            case CATEGORY:
                return SimpleEntity.newInstance(TypeEntity.CATEGORY, resultSet.getLong(ID_FIELD_NAME)
                        , resultSet.getString(NAME_FIELD_NAME));
            default:
                throw new EntityExtractorNotFoundException("This extractor not found!");
        }
    }

    public TypeEntity getType() {
        return type;
    }
}
