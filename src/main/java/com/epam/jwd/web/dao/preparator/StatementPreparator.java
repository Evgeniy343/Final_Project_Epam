package com.epam.jwd.web.dao.preparator;

import com.epam.jwd.web.dao.extractor.ResultSetExtractor;
import com.epam.jwd.web.model.Entity;

import java.sql.SQLException;
import java.util.List;

@FunctionalInterface
public interface StatementPreparator<T> {
    List<? extends Entity> executeStatement(String sql, ResultSetExtractor<? extends Entity> extractor) throws SQLException;
}
