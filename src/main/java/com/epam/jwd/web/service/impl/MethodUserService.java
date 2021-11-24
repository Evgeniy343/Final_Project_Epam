package com.epam.jwd.web.service.impl;

import com.epam.jwd.web.dao.UserDao;
import com.epam.jwd.web.model.User;
import com.epam.jwd.web.service.UserService;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.locks.ReentrantLock;

public class MethodUserService implements UserService {
    private final UserDao userDao;
    private static MethodUserService instance;
    private static final ReentrantLock lock = new ReentrantLock();

    private MethodUserService(UserDao userDao) {
        this.userDao = userDao;
    }

    static MethodUserService getInstance(UserDao userDao) {
        if (instance == null) {
            lock.lock();
            {
                if (instance == null) {
                    instance = new MethodUserService(userDao);
                }
            }
            lock.unlock();
        }
        return instance;
    }


    @Override
    public User get(Long id) throws SQLException {
        Optional<User> user = userDao.read(id);
        return user.orElse(null);//todo exception
    }

    @Override
    public List<User> findAll() throws SQLException, InterruptedException {
        return userDao.read();
    }

    @Override
    public Optional<User> authenticate(String email, String password) {//todo implement
        return Optional.empty();
    }
}
