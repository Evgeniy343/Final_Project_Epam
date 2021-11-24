package com.epam.jwd.web.service;

import com.epam.jwd.web.model.Entity;

import java.sql.SQLException;
import java.util.List;

public interface EntityService<T extends Entity> {
    T get(Long id) throws SQLException;

    List<T> findAll() throws SQLException, InterruptedException;

}
