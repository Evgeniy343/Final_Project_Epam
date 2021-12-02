package com.epam.jwd.web.db.impl;

import com.epam.jwd.web.db.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Optional;
import java.util.concurrent.locks.ReentrantLock;

public class ThreadLocalTransactionManager implements TransactionManager {
    private static final Logger LOGGER = LogManager.getLogger(ThreadLocalTransactionManager.class);
    private static final ReentrantLock lock = new ReentrantLock();
    private static ThreadLocalTransactionManager instance;

    private static final ThreadLocal<TransactionId> THREAD_CONNECTION = ThreadLocal.withInitial(() -> {
        try {
            ConnectionPool connectionPool = ConnectionPoolFactory.newInstance()
                    .getBy(ConnectionPoolType.TRANSACTION_CONNECTION_POOL);
            return new SimpleTransactionId(connectionPool.takeConnection());
        } catch (InterruptedException e) {
            LOGGER.warn("Thread was interrupted");
            Thread.currentThread().interrupt();
            return null;
        }
    });

    private static final ThreadLocal<Boolean> TRANSACTION_ACTIVE = ThreadLocal.withInitial(() -> false);

    @Override
    public void initTransaction() {
        try {
            if (!TRANSACTION_ACTIVE.get()) {
                final Connection connection = THREAD_CONNECTION.get().getConnection();
                connection.setAutoCommit(false);
                TRANSACTION_ACTIVE.set(true);
            }
        } catch (SQLException e) {
            LOGGER.warn("SQL exception occurred trying to initialisation transaction", e);
        }
    }

    @Override
    public Optional<TransactionId> getTransactionId() {
        if (TRANSACTION_ACTIVE.get()) {
            return Optional.of(THREAD_CONNECTION.get());
        }
        return Optional.empty();
    }

    @Override
    public void commitTransaction() {
        try {
            if (TRANSACTION_ACTIVE.get()) {
                TRANSACTION_ACTIVE.set(false);
                Connection connection = THREAD_CONNECTION.get().getConnection();
                connection.commit();
                connection.setAutoCommit(true);
                THREAD_CONNECTION.remove();
                connection.close();
            } //todo : otherwise throw exception
        } catch (SQLException e) {
            LOGGER.warn("SQL exception occurred committing transaction", e);
        }
    }

    @Override
    public boolean isTransaction() {
        return TRANSACTION_ACTIVE.get();
    }

    public static ThreadLocalTransactionManager getInstance(){
        if (instance == null) {
            lock.lock();
            {
                if (instance == null) {
                    instance = new ThreadLocalTransactionManager();
                }
            }
            lock.unlock();
        }
        return instance;
    }
}
