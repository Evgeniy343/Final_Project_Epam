package com.epam.jwd.web.dao.extractor.factory;

import com.epam.jwd.web.dao.extractor.ResultSetExtractor;
import com.epam.jwd.web.dao.extractor.impl.*;
import com.epam.jwd.web.model.Entity;
import com.epam.jwd.web.model.TypeEntity;

public class EntityExtractorFactory implements ExtractorFactory {

    EntityExtractorFactory() {
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T extends Entity> ResultSetExtractor<T> createExtractor(TypeEntity type) {
        return (ResultSetExtractor<T>) chooseExtractor(type);
    }

    private ResultSetExtractor<? extends Entity> chooseExtractor(TypeEntity type){
        switch (type){
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
        }
        return null;
    }
}
