package com.epam.jwd.web.dao.extractor;

import com.epam.jwd.web.dao.exception.EntityExtractorNotFoundException;
import com.epam.jwd.web.model.exception.EntityNotFoundException;
import com.epam.jwd.web.model.factory.EntityFactory;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@FunctionalInterface
public interface ResultSetExtractor<T> {
    T extract(ResultSet resultSet, EntityFactory factory) throws SQLException, EntityExtractorNotFoundException
            , EntityNotFoundException;

    default List<T> extractAll(ResultSet resultSet, EntityFactory factory) throws EntityExtractorNotFoundException
            , SQLException, EntityNotFoundException {
        List<T> entities = new ArrayList<>();
        while (resultSet.next()) {
            final T entity = this.extract(resultSet,factory);
            entities.add(entity);
        }
        return entities;
    }
}
