package com.epam.jwd.web.service.impl;

import com.epam.jwd.web.dao.SimpleEntityDao;
import com.epam.jwd.web.model.SimpleEntity;
import com.epam.jwd.web.service.EntityService;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.locks.ReentrantLock;

public class SimpleEntityService implements EntityService<SimpleEntity> {
    private final SimpleEntityDao simpleEntityDao;
    private static SimpleEntityService instance;
    private static final ReentrantLock lock = new ReentrantLock();

    private SimpleEntityService(SimpleEntityDao simpleEntityDao) {
        this.simpleEntityDao = simpleEntityDao;
    }

    static SimpleEntityService getInstance(SimpleEntityDao simpleEntityDao) {
        if (instance == null) {
            lock.lock();
            {
                if (instance == null) {
                    instance = new SimpleEntityService(simpleEntityDao);
                }
            }
            lock.unlock();
        }
        return instance;
    }

    @Override
    public SimpleEntity get(Long id) throws SQLException {
        Optional<SimpleEntity> simpleEntity = simpleEntityDao.read(id);
        return simpleEntity.orElse(null);//todo exception
    }

    @Override
    public List<SimpleEntity> findAll() throws SQLException, InterruptedException {
        return simpleEntityDao.read();
    }
}
