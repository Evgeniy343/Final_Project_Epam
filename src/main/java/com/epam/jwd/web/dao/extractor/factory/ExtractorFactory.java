package com.epam.jwd.web.dao.extractor.impl.factory;

import com.epam.jwd.web.dao.extractor.ResultSetExtractor;
import com.epam.jwd.web.model.Entity;
import com.epam.jwd.web.model.TypeEntity;

public interface ExtractorFactory {
    ResultSetExtractor<? extends Entity> createExtractor(TypeEntity type);
    static ExtractorFactory newInstance(){

    }
}
