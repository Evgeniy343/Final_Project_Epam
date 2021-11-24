package com.epam.jwd.web.dao.extractor;

import com.epam.jwd.web.exception.EntityExtractorNotFoundException;
import com.epam.jwd.web.exception.EntityNotFoundException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@FunctionalInterface
public interface ResultSetExtractor<T> {
    T extract(ResultSet resultSet) throws SQLException, EntityExtractorNotFoundException, EntityNotFoundException;

    default List<T> extractAll(ResultSet resultSet) throws EntityExtractorNotFoundException
            , SQLException, EntityNotFoundException {
        List<T> entities = new ArrayList<>();
        while (resultSet.next()) {
            final T entity = this.extract(resultSet);
            entities.add(entity);
        }
        return entities;
    }
}
