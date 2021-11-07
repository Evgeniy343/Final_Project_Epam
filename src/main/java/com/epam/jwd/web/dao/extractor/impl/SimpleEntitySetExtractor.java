package com.epam.jwd.web.dao.extractor.impl;

import com.epam.jwd.web.dao.exception.EntityExtractorNotFoundException;
import com.epam.jwd.web.dao.extractor.ResultSetExtractor;
import com.epam.jwd.web.dao.extractor.TypeExtractor;
import com.epam.jwd.web.model.SimpleEntity;
import com.epam.jwd.web.model.impl.*;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SimpleEntitySetExtractor implements ResultSetExtractor<SimpleEntity> {
    private static final String ID_FIELD_NAME = "id";
    private static final String NAME_FIELD_NAME = "name";

    private final TypeExtractor type;

    public SimpleEntitySetExtractor(TypeExtractor type) {
        this.type = type;
    }

    @Override
    public SimpleEntity extract(ResultSet resultSet) throws SQLException, EntityExtractorNotFoundException {
        switch (type){
            case STREET_EXTRACTOR:
                return Street.of(
                        resultSet.getLong(ID_FIELD_NAME),
                        resultSet.getString(NAME_FIELD_NAME)
                );
            case INGREDIENT_EXTRACTOR:
                return Ingredient.of(
                        resultSet.getLong(ID_FIELD_NAME),
                        resultSet.getString(NAME_FIELD_NAME)
                );
            case BONUS_EXTRACTOR:
                return Bonus.of(
                        resultSet.getLong(ID_FIELD_NAME),
                        resultSet.getString(NAME_FIELD_NAME)
                );
            case ROLE_EXTRACTOR:
                return Role.of(
                        resultSet.getLong(ID_FIELD_NAME),
                        resultSet.getString(NAME_FIELD_NAME)
                );
            case CITY_EXTRACTOR:
                return City.of(
                        resultSet.getLong(ID_FIELD_NAME),
                        resultSet.getString(NAME_FIELD_NAME)
                );
            case CATEGORY_EXTRACTOR:
                return Category.of(
                        resultSet.getLong(ID_FIELD_NAME),
                        resultSet.getString(NAME_FIELD_NAME)
                );
            case STATUS_EXTRACTOR:
                return Status.of(
                        resultSet.getLong(ID_FIELD_NAME),
                        resultSet.getString(NAME_FIELD_NAME)
                );
            default:
                throw new EntityExtractorNotFoundException("Entity extractor not found!");
        }
    }

    public TypeExtractor getType() {
        return type;
    }
}
