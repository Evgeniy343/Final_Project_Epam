package com.epam.jwd.web.dao.extractor.factory;

import com.epam.jwd.web.dao.exception.EntityExtractorNotFoundException;
import com.epam.jwd.web.dao.extractor.impl.*;
import com.epam.jwd.web.dao.extractor.ResultSetExtractor;
import com.epam.jwd.web.dao.extractor.TypeExtractor;
import com.epam.jwd.web.model.Entity;

import java.sql.ResultSet;
import java.util.concurrent.locks.ReentrantLock;

public class ExtractorEntityFactory implements ExtractorFactory<Entity> {

    private static final ReentrantLock lock = new ReentrantLock();
    private static ExtractorEntityFactory instance;

    private ExtractorEntityFactory() {
    }

    @Override
    public ResultSetExtractor<? extends Entity> createExtractor(ResultSet resultSet
            , TypeExtractor type) throws EntityExtractorNotFoundException {
        switch (type) {
            case USER_EXTRACTOR:
                return UserSetExtractor.of();
            case ORDER_EXTRACTOR:
                return CafeOrderSetExtractor.of();
            case CARD_EXTRACTOR:
                return CardSetExtractor.of();
            case CITY_EXTRACTOR:
            case BONUS_EXTRACTOR:
            case STATUS_EXTRACTOR:
            case STREET_EXTRACTOR:
            case CATEGORY_EXTRACTOR:
            case INGREDIENT_EXTRACTOR:
            case ROLE_EXTRACTOR:
                return SimpleEntitySetExtractor.of(type);
            case MEAL_EXTRACTOR:
                return MealSetExtractor.of();
            case ORDER_ELEMENT_EXTRACTOR:
                return OrderElementExtractor.of();
            case ADDRESS_EXTRACTOR:
                return AddressSetExtractor.of();
            case USER_DETAILS_EXTRACTOR:
                return UserDetailsSetExtractor.of();
            default:
                throw new EntityExtractorNotFoundException("Entity extractor not found!");
        }
    }

    public static ExtractorEntityFactory getInstance() {
        if (instance == null) {
            lock.lock();
            {
                if (instance == null) {
                    instance = new ExtractorEntityFactory();
                }
            }
            lock.unlock();
        }
        return instance;
    }

}
