package com.epam.jwd.web.dao.extractor;

import com.epam.jwd.web.dao.exception.EntityExtractorNotFoundException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@FunctionalInterface
public interface ResultSetExtractor<T> {
    T extract(ResultSet resultSet) throws SQLException, EntityExtractorNotFoundException;

    default List<T> extractAll(ResultSet resultSet) throws EntityExtractorNotFoundException, SQLException {
        List<T> entities = new ArrayList<>();
        while (resultSet.next()) {
            final T entity = this.extract(resultSet);
            entities.add(entity);
        }
        return entities;
    }
}
