package com.epam.jwd.web.dao.factory.impl;

import com.epam.jwd.web.dao.EntityDao;
import com.epam.jwd.web.dao.factory.DaoFactory;
import com.epam.jwd.web.dao.impl.*;
import com.epam.jwd.web.db.impl.ConnectionPool;
import com.epam.jwd.web.exception.EntityDaoNotFoundException;
import com.epam.jwd.web.exception.EntityExtractorNotFoundException;
import com.epam.jwd.web.exception.EntityNotFoundException;
import com.epam.jwd.web.model.Entity;
import com.epam.jwd.web.model.TypeEntity;

import java.util.concurrent.locks.ReentrantLock;

public class EntityDaoFactory implements DaoFactory {
    private static EntityDaoFactory instance;
    private static final ReentrantLock lock = new ReentrantLock();

    private EntityDaoFactory() {
    }

    public static EntityDaoFactory getInstance() {
        if (instance == null) {
            lock.lock();
            {
                if (instance == null) {
                    instance = new EntityDaoFactory();
                }
            }
            lock.unlock();
        }
        return instance;
    }


    @Override
    @SuppressWarnings("unchecked")
    public <T extends Entity> EntityDao<T> createDao(TypeEntity type, ConnectionPool pool)
            throws EntityExtractorNotFoundException, EntityNotFoundException, EntityDaoNotFoundException {
        return (EntityDao<T>) chooseDao(type, pool);
    }

    private EntityDao<? extends Entity> chooseDao(TypeEntity type, ConnectionPool pool)
            throws EntityExtractorNotFoundException, EntityNotFoundException, EntityDaoNotFoundException {
        switch (type) {
            case ADDRESS:
                return MethodAddressDao.of(pool);
            case CAFE_ORDER:
                return MethodCafeOrderDao.of(pool);
            case CARD:
                return MethodCardDao.of(pool);
            case MEAL:
                return MethodMealDao.of(pool);
            case ORDER_ELEMENT:
                return MethodOrderElementDao.of(pool);
            case USER_DETAILS:
                return MethodUserDetailsDao.of(pool);
            case USER:
                return MethodUserDao.of(pool);
            default:
                throw new EntityDaoNotFoundException("This type of Dao is not find!");
        }
    }


}
