package com.epam.jwd.web.service.impl;

import com.epam.jwd.web.dao.MealDao;
import com.epam.jwd.web.model.Meal;
import com.epam.jwd.web.service.EntityService;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.locks.ReentrantLock;

public class MealService implements EntityService<Meal> {
    private final MealDao mealDao;
    private static MealService instance;
    private static final ReentrantLock lock = new ReentrantLock();

    private MealService(MealDao mealDao) {
        this.mealDao = mealDao;
    }

    static MealService getInstance(MealDao mealDao) {
        if (instance == null) {
            lock.lock();
            {
                if (instance == null) {
                    instance = new MealService(mealDao);
                }
            }
            lock.unlock();
        }
        return instance;
    }

    @Override
    public Meal get(Long id) throws SQLException {
        Optional<Meal> meal = mealDao.read(id);
        return meal.orElse(null);//todo exception
    }

    @Override
    public List<Meal> findAll() throws SQLException, InterruptedException {
        return mealDao.read();
    }
}
