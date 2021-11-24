package com.epam.jwd.web.service.impl;

import com.epam.jwd.web.dao.UserDetailsDao;
import com.epam.jwd.web.model.UserDetails;
import com.epam.jwd.web.service.EntityService;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.locks.ReentrantLock;

public class UserDetailsService implements EntityService<UserDetails> {
    private final UserDetailsDao userDetailsDao;
    private static UserDetailsService instance;
    private static final ReentrantLock lock = new ReentrantLock();

    private UserDetailsService(UserDetailsDao userDetailsDao) {
        this.userDetailsDao = userDetailsDao;
    }

    static UserDetailsService getInstance(UserDetailsDao userDetailsDao) {
        if (instance == null) {
            lock.lock();
            {
                if (instance == null) {
                    instance = new UserDetailsService(userDetailsDao);
                }
            }
            lock.unlock();
        }
        return instance;
    }

    @Override
    public UserDetails get(Long id) throws SQLException {
        Optional<UserDetails> userDetails = userDetailsDao.read(id);
        return userDetails.orElse(null);//todo exception
    }

    @Override
    public List<UserDetails> findAll() throws SQLException, InterruptedException {
        return userDetailsDao.read();
    }
}
