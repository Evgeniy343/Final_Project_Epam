package com.epam.jwd.web.dao.extractor.impl;

import com.epam.jwd.web.dao.exception.EntityExtractorNotFoundException;
import com.epam.jwd.web.dao.extractor.ResultSetExtractor;
import com.epam.jwd.web.dao.extractor.TypeExtractor;
import com.epam.jwd.web.model.SimpleEntity;
import com.epam.jwd.web.model.TypeModel;
import com.epam.jwd.web.model.context.SimpleEntityContext;
import com.epam.jwd.web.model.exception.EntityNotFoundException;
import com.epam.jwd.web.model.factory.EntityFactory;
import com.epam.jwd.web.model.impl.*;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SimpleEntitySetExtractor implements ResultSetExtractor<SimpleEntity> {
    private static final String ID_FIELD_NAME = "id";
    private static final String NAME_FIELD_NAME = "name";

    private final TypeExtractor type;

    private SimpleEntitySetExtractor(TypeExtractor type) {
        this.type = type;
    }

    public static SimpleEntitySetExtractor of(TypeExtractor type) {
        return new SimpleEntitySetExtractor(type);
    }

    @Override
    public SimpleEntity extract(ResultSet resultSet, EntityFactory factory) throws SQLException, EntityExtractorNotFoundException, EntityNotFoundException {
        SimpleEntityContext simpleContext = SimpleEntityContext.of(resultSet.getLong(ID_FIELD_NAME)
                , resultSet.getString(NAME_FIELD_NAME));
        switch (type) {
            case STREET_EXTRACTOR:
                return (Street) factory.createModel(TypeModel.STREET_MODEL, null, simpleContext);
            case INGREDIENT_EXTRACTOR:
                return (Ingredient) factory.createModel(TypeModel.INGREDIENT_MODEL, null, simpleContext);
            case BONUS_EXTRACTOR:
                return (Bonus) factory.createModel(TypeModel.BONUS_MODEL, null, simpleContext);
            case ROLE_EXTRACTOR:
                return (Role) factory.createModel(TypeModel.ROLE_MODEL, null, simpleContext);
            case CITY_EXTRACTOR:
                return (City) factory.createModel(TypeModel.CITY_MODEL, null, simpleContext);
            case CATEGORY_EXTRACTOR:
                return (Category) factory.createModel(TypeModel.CATEGORY_MODEL, null, simpleContext);
            case STATUS_EXTRACTOR:
                return (Status) factory.createModel(TypeModel.STATUS_MODEL, null, simpleContext);
            default:
                throw new EntityExtractorNotFoundException("Entity extractor not found!");
        }
    }

    public TypeExtractor getType() {
        return type;
    }
}
