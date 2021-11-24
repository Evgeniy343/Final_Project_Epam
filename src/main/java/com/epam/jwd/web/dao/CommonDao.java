package com.epam.jwd.web.dao;

import com.epam.jwd.web.dao.extractor.ResultSetExtractor;
import com.epam.jwd.web.db.impl.ConnectionPool;
import com.epam.jwd.web.exception.EntityExtractorNotFoundException;
import com.epam.jwd.web.exception.EntityNotFoundException;
import com.epam.jwd.web.model.Entity;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static java.lang.String.format;
import static java.lang.String.join;

public abstract class CommonDao<T extends Entity> implements EntityDao<T> {

    private static final String WHERE_FIELD = "where %s = ?";
    private static final String SPACE = " ";
    private static final String COMMA = ", ";
    private static final String INSERT_SQL = "insert into %s (%s)";
    private static final String SELECT_ALL_SQL = "select %s from %s";
    private static final String UPDATE_SQL = "update %s set %s " + WHERE_FIELD;
    private static final String DELETE_SQL = "delete from %s " + WHERE_FIELD;
    protected final Logger logger;
    private final String selectAllExpression;
    private final String selectByIdExpression;
    private final String insertSql;
    private final String updateSql;
    private final String deleteSql;
    private final ConnectionPool pool;
    private final ResultSetExtractor<T> extractor;


    public CommonDao(ConnectionPool pool, ResultSetExtractor<T> extractor, Logger logger)
            throws EntityNotFoundException {
        this.pool = pool;
        this.extractor = extractor;
        this.logger = logger;
        this.selectAllExpression = format(SELECT_ALL_SQL, join(COMMA, getFields()), getTableName());
        this.selectByIdExpression = this.selectAllExpression + SPACE + format(WHERE_FIELD, getIdFieldName());
        this.insertSql = format(INSERT_SQL, getTableName(), join(COMMA, getFields()));
        this.updateSql = format(UPDATE_SQL, getTableName(), join(COMMA, getFieldForUpdate()), getIdFieldName());
        this.deleteSql = format(DELETE_SQL, getTableName(), getIdFieldName());
    }

    @Override
    public T create(T entity) {
        Optional<T> result = executeUpdateStatement(insertSql, pool, TypeStatementPreparator.CREATE, entity, null);
        return result.orElse(null);//todo exception
    }

    @Override
    public List<T> read() throws SQLException {
        return executeReadStatement(selectAllExpression, extractor, pool);
    }

    @Override
    public Optional<T> read(Long id) throws SQLException {
        return executeUpdateStatement(selectByIdExpression, pool, TypeStatementPreparator.READ_BY_ID, null, id);
    }

    @Override
    public T update(T entity) {
        Optional<T> result = executeUpdateStatement(updateSql, pool, TypeStatementPreparator.UPDATE
                , entity, entity.getId());
        return result.orElse(null);//todo exception
    }

    @Override
    public boolean delete(Long id) {
        Optional<T> result = executeUpdateStatement(deleteSql, pool, TypeStatementPreparator.DELETE, null, id);
        return result.isPresent();
    }

    public List<T> executeReadStatement(String sql, ResultSetExtractor<T> extractor
            , ConnectionPool pool) {
        try (final Connection connection = pool.takeConnection();
             final PreparedStatement statement = connection.prepareStatement(sql);
             final ResultSet resultSet = statement.executeQuery()) {
            return extractor.extractAll(resultSet);
        } catch (SQLException e) {
            logger.error("sql exception occurred", e);
            logger.debug("sql: {}", sql);
        } catch (InterruptedException e) {
            logger.info("takeConnection interrupted", e);
            Thread.currentThread().interrupt();
        } catch (EntityNotFoundException | EntityExtractorNotFoundException e) {
            logger.error(e.getMessage());
        }
        return Collections.emptyList();
    }

    public Optional<T> executeUpdateStatement(String sql, ConnectionPool pool, TypeStatementPreparator type
            , T entity, Long id) {
        try (final Connection connection = pool.takeConnection();
             final PreparedStatement statement = connection.prepareStatement(sql)) {
            switch (type) {
                case CREATE:
                case UPDATE:
                    fillEntity(statement, entity);
                case DELETE:
                    statement.setLong(1, id);
            }
            int rowUpdatable = statement.executeUpdate();
            if (rowUpdatable > 0) {
                return Optional.ofNullable(entity);
            }
        } catch (SQLException e) {
            logger.error("sql exception occurred", e);
            logger.debug("sql: {}", sql);
        } catch (InterruptedException e) {
            logger.info("takeConnection interrupted", e);
            Thread.currentThread().interrupt();
        }
        return Optional.empty();
    }

    protected abstract String getTableName() throws EntityNotFoundException;

    protected abstract List<String> getFields() throws EntityNotFoundException;

    protected abstract String getIdFieldName();

    protected abstract String getFieldForUpdate() throws EntityNotFoundException;

    protected abstract void fillEntity(PreparedStatement statement, T entity) throws SQLException;
}
