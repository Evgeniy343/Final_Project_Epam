package com.epam.jwd.web.dao.impl;

import com.epam.jwd.web.dao.CommonDao;
import com.epam.jwd.web.dao.MealDao;
import com.epam.jwd.web.dao.extractor.impl.MealSetExtractor;
import com.epam.jwd.web.db.impl.ConnectionPool;
import com.epam.jwd.web.exception.EntityExtractorNotFoundException;
import com.epam.jwd.web.exception.EntityNotFoundException;
import com.epam.jwd.web.model.Meal;
import org.apache.logging.log4j.LogManager;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import static java.lang.String.join;

public class MethodMealDao extends CommonDao<Meal> implements MealDao {
    private static final String MEAL_TABLE_NAME = "meal m " +
            "join category cat on cat.id = m.category_id";
    private static final List<String> FIELDS = Arrays.asList("m.id", "m.name", "m.price", "cat.name");
    private static final String ID_FIELD_NAME = "m.id";


    private MethodMealDao(ConnectionPool pool) throws EntityNotFoundException {
        super(pool, MealSetExtractor.of(), LogManager.getLogger(MethodMealDao.class));
    }

    public static MethodMealDao of(ConnectionPool pool) throws EntityExtractorNotFoundException
            , EntityNotFoundException {
        return new MethodMealDao(pool);
    }

    @Override
    protected String getTableName() {
        return MEAL_TABLE_NAME;
    }

    @Override
    protected List<String> getFields() {
        return FIELDS;
    }

    @Override
    protected String getIdFieldName() {
        return null;
    }

    @Override
    protected String getFieldForUpdate() {
        return join(" = ?, ", getFields());
    }

    @Override
    protected void fillEntity(PreparedStatement statement, Meal entity) throws SQLException {
        statement.setLong(1, entity.getId());
        statement.setString(2, entity.getName());
        statement.setInt(3, entity.getPrice());
        statement.setLong(4, entity.getCategory().getId());
    }
}
