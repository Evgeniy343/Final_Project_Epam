package com.epam.jwd.web.service.impl;

import com.epam.jwd.web.dao.CafeOrderDao;
import com.epam.jwd.web.model.CafeOrder;
import com.epam.jwd.web.service.EntityService;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.locks.ReentrantLock;

public class CafeOrderService implements EntityService<CafeOrder> {
    private final CafeOrderDao cafeOrderDao;
    private static CafeOrderService instance;
    private static final ReentrantLock lock = new ReentrantLock();

    private CafeOrderService(CafeOrderDao cafeOrderDao) {
        this.cafeOrderDao = cafeOrderDao;
    }

    static CafeOrderService getInstance(CafeOrderDao cafeOrderDao) {
        if (instance == null) {
            lock.lock();
            {
                if (instance == null) {
                    instance = new CafeOrderService(cafeOrderDao);
                }
            }
            lock.unlock();
        }
        return instance;
    }


    @Override
    public CafeOrder get(Long id) throws SQLException {
        Optional<CafeOrder> cafeOrder = cafeOrderDao.read(id);
        return cafeOrder.orElse(null);//todo exception
    }

    @Override
    public List<CafeOrder> findAll() throws SQLException, InterruptedException {
        return cafeOrderDao.read();
    }
}
