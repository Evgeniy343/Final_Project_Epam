package com.epam.jwd.web.dao.factory;

import com.epam.jwd.web.dao.EntityDao;
import com.epam.jwd.web.dao.factory.impl.EntityDaoFactory;
import com.epam.jwd.web.db.impl.ConnectionPool;
import com.epam.jwd.web.exception.EntityDaoNotFoundException;
import com.epam.jwd.web.exception.EntityExtractorNotFoundException;
import com.epam.jwd.web.exception.EntityNotFoundException;
import com.epam.jwd.web.model.Entity;
import com.epam.jwd.web.model.TypeEntity;

public interface DaoFactory {
    <T extends Entity> EntityDao<T> createDao(TypeEntity type, ConnectionPool pool) throws EntityExtractorNotFoundException, EntityNotFoundException, EntityDaoNotFoundException;

    static DaoFactory newInstance() {
        return EntityDaoFactory.getInstance();
    }
}
