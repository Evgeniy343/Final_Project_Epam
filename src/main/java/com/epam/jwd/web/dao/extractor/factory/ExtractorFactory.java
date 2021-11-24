package com.epam.jwd.web.dao.extractor.factory;

import com.epam.jwd.web.dao.extractor.ResultSetExtractor;
import com.epam.jwd.web.dao.extractor.factory.impl.EntityExtractorFactory;
import com.epam.jwd.web.exception.EntityExtractorNotFoundException;
import com.epam.jwd.web.model.Entity;
import com.epam.jwd.web.model.TypeEntity;

public interface ExtractorFactory {
    <T extends Entity> ResultSetExtractor<T> createExtractor(TypeEntity type) throws EntityExtractorNotFoundException;

    static ExtractorFactory newInstance() {
        return EntityExtractorFactory.getInstance();
    }
}
