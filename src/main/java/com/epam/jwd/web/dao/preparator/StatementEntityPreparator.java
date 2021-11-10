package com.epam.jwd.web.dao.preparator;

import com.epam.jwd.web.dao.exception.EntityExtractionFailedException;
import com.epam.jwd.web.dao.exception.EntityExtractorNotFoundException;
import com.epam.jwd.web.dao.extractor.ResultSetExtractor;
import com.epam.jwd.web.model.Entity;
import com.epam.jwd.web.model.exception.EntityNotFoundException;
import com.epam.jwd.web.model.factory.EntityFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

public class StatementEntityPreparator implements StatementPreparator<Entity>{
    private static final Logger LOGGER = LogManager.getLogger(StatementEntityPreparator.class);
    private static final EntityFactory factory = EntityFactory.getInstance();


    @Override
    public List<? extends Entity> executeStatement(String sql, ResultSetExtractor<? extends Entity> extractor) {
        try (final Connection connection = pool.takeConnection();
             final PreparedStatement statement = connection.prepareStatement(sql);
             final ResultSet resultSet = statement.executeQuery()) {
             return extractor.extractAll(resultSet,factory);
        } catch (SQLException e) {
            LOGGER.error("sql exception occurred", e);
            LOGGER.debug("sql: {}", sql);
        } catch (InterruptedException e) {
            LOGGER.info("takeConnection interrupted", e);
            Thread.currentThread().interrupt();
        }
        catch (EntityNotFoundException | EntityExtractionFailedException | EntityExtractorNotFoundException e) {
            LOGGER.error(e.getMessage());
        }
        return Collections.emptyList();
    }
}
