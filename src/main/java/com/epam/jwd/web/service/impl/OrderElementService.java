package com.epam.jwd.web.service.impl;

import com.epam.jwd.web.dao.OrderElementDao;
import com.epam.jwd.web.model.OrderElement;
import com.epam.jwd.web.service.EntityService;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.locks.ReentrantLock;

public class OrderElementService implements EntityService<OrderElement> {
    private final OrderElementDao orderElementDao;
    private static OrderElementService instance;
    private static final ReentrantLock lock = new ReentrantLock();

    private OrderElementService(OrderElementDao orderElementDao) {
        this.orderElementDao = orderElementDao;
    }

    static OrderElementService getInstance(OrderElementDao orderElementDao) {
        if (instance == null) {
            lock.lock();
            {
                if (instance == null) {
                    instance = new OrderElementService(orderElementDao);
                }
            }
            lock.unlock();
        }
        return instance;
    }


    @Override
    public OrderElement get(Long id) throws SQLException {
        Optional<OrderElement> orderElement = orderElementDao.read(id);
        return orderElement.orElse(null);//todo exception
    }

    @Override
    public List<OrderElement> findAll() throws SQLException, InterruptedException {
        return orderElementDao.read();
    }
}
