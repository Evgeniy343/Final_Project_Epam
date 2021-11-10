package com.epam.jwd.web.dao.extractor.factory;

import com.epam.jwd.web.dao.exception.EntityExtractorNotFoundException;
import com.epam.jwd.web.dao.extractor.ResultSetExtractor;
import com.epam.jwd.web.dao.extractor.TypeExtractor;
import com.epam.jwd.web.model.Entity;

import java.sql.ResultSet;

public interface ExtractorFactory<T> {
    ResultSetExtractor<? extends Entity> createExtractor(ResultSet resultSet, TypeExtractor type)
            throws EntityExtractorNotFoundException;
    static ExtractorFactory<? extends Entity> newInstance(){
        return ExtractorEntityFactory.getInstance();
    }
}
