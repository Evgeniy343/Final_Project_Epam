package com.epam.jwd.web.dao.impl;

import com.epam.jwd.web.dao.CommonDao;
import com.epam.jwd.web.dao.SimpleEntityDao;
import com.epam.jwd.web.dao.extractor.impl.SimpleEntitySetExtractor;
import com.epam.jwd.web.db.impl.ConnectionPool;
import com.epam.jwd.web.exception.EntityExtractorNotFoundException;
import com.epam.jwd.web.exception.EntityNotFoundException;
import com.epam.jwd.web.model.SimpleEntity;
import com.epam.jwd.web.model.TypeEntity;
import org.apache.logging.log4j.LogManager;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import static java.lang.String.join;

public class MethodSimpleEntityDao extends CommonDao<SimpleEntity> implements SimpleEntityDao {


    private final static String BONUS_TABLE = "bonus";
    private final static String CITY_TABLE = "city";
    private final static String STREET_TABLE = "street";
    private final static String CATEGORY_TABLE = "category";
    private final static String INGREDIENT_TABLE = "ingredient";
    private static final List<String> BONUS_FIELDS = Arrays.asList("id", "b_name");
    private static final List<String> STREET_FIELDS = Arrays.asList("id", "s_name");
    private static final List<String> CITY_FIELDS = Arrays.asList("id", "c_name");
    private static final List<String> CATEGORY_FIELDS = Arrays.asList("id", "c_name");
    private static final List<String> INGREDIENT_FIELDS = Arrays.asList("id", "i_name");
    private static final String ID_FIELD_NAME = "id";
    private final TypeEntity type;


    private MethodSimpleEntityDao(ConnectionPool pool, TypeEntity typeEntity) throws EntityNotFoundException {
        super(pool, SimpleEntitySetExtractor.of(typeEntity), LogManager.getLogger(MethodSimpleEntityDao.class));
        this.type = typeEntity;
    }

    public static MethodSimpleEntityDao of(ConnectionPool pool, TypeEntity typeEntity)
            throws EntityExtractorNotFoundException, EntityNotFoundException {
        return new MethodSimpleEntityDao(pool, typeEntity);
    }

    @Override
    protected String getTableName() throws EntityNotFoundException {
        switch (type) {
            case BONUS:
                return BONUS_TABLE;
            case CATEGORY:
                return CATEGORY_TABLE;
            case CITY:
                return CITY_TABLE;
            case INGREDIENT:
                return INGREDIENT_TABLE;
            case STREET:
                return STREET_TABLE;
            default:
                throw new EntityNotFoundException("This type of Entity not found!");
        }
    }

    @Override
    protected List<String> getFields() throws EntityNotFoundException {
        switch (type) {
            case BONUS:
                return BONUS_FIELDS;
            case CATEGORY:
                return CATEGORY_FIELDS;
            case CITY:
                return CITY_FIELDS;
            case INGREDIENT:
                return INGREDIENT_FIELDS;
            case STREET:
                return STREET_FIELDS;
            default:
                throw new EntityNotFoundException("This type of Entity not found!");
        }
    }

    @Override
    protected String getIdFieldName() {
        return ID_FIELD_NAME;
    }

    @Override
    protected String getFieldForUpdate() throws EntityNotFoundException {
        return join(" = ?, ", getFields());
    }

    @Override
    protected void fillEntity(PreparedStatement statement, SimpleEntity entity) throws SQLException {
        statement.setLong(1, entity.getId());
        statement.setString(2, entity.getName());
    }
}
