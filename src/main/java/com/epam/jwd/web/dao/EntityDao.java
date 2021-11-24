package com.epam.jwd.web.dao;

import com.epam.jwd.web.model.Entity;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface EntityDao<T extends Entity> {

    T create(T entity) throws InterruptedException, SQLException;

    List<T> read() throws InterruptedException, SQLException;

    Optional<T> read(Long id) throws SQLException;

    T update(T entity);

    boolean delete(Long id);
}
