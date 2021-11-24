package com.epam.jwd.web.dao.extractor.factory.impl;

import com.epam.jwd.web.dao.extractor.ResultSetExtractor;
import com.epam.jwd.web.dao.extractor.factory.ExtractorFactory;
import com.epam.jwd.web.dao.extractor.impl.*;
import com.epam.jwd.web.exception.EntityExtractorNotFoundException;
import com.epam.jwd.web.model.Entity;
import com.epam.jwd.web.model.TypeEntity;

import java.util.concurrent.locks.ReentrantLock;

public class EntityExtractorFactory implements ExtractorFactory {
    private static EntityExtractorFactory instance;
    private static final ReentrantLock lock = new ReentrantLock();

    private EntityExtractorFactory() {
    }

    public static EntityExtractorFactory getInstance() {
        if (instance == null) {
            lock.lock();
            {
                if (instance == null) {
                    instance = new EntityExtractorFactory();
                }
            }
            lock.unlock();
        }
        return instance;
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T extends Entity> ResultSetExtractor<T> createExtractor(TypeEntity type)
            throws EntityExtractorNotFoundException {
        return (ResultSetExtractor<T>) chooseExtractor(type);
    }

    private ResultSetExtractor<? extends Entity> chooseExtractor(TypeEntity type)
            throws EntityExtractorNotFoundException {
        switch (type) {
            case USER:
                return UserSetExtractor.of();
            case USER_DETAILS:
                return UserDetailsSetExtractor.of();
            case ORDER_ELEMENT:
                return OrderElementSetExtractor.of();
            case MEAL:
                return MealSetExtractor.of();
            case CARD:
                return CardSetExtractor.of();
            case CAFE_ORDER:
                return CafeOrderSetExtractor.of();
            case ADDRESS:
                return AddressSetExtractor.of();
            default:
                throw new EntityExtractorNotFoundException("This type of extractor is not find!");
        }
    }
}
