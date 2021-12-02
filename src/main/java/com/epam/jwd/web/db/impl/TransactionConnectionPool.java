package com.epam.jwd.web.db.impl;

import com.epam.jwd.web.db.ConnectionPool;
import com.epam.jwd.web.db.TransactionId;
import com.epam.jwd.web.db.TransactionManager;
import com.epam.jwd.web.exception.CouldNotInitializeConnectionPoolError;

import java.sql.Connection;
import java.util.Optional;

public final class TransactionConnectionPool implements ConnectionPool {
    private static TransactionConnectionPool instance;
    private final ConnectionPool connectionPool;
    private final TransactionManager transactionManager;

    private TransactionConnectionPool(ConnectionPool connectionPool, TransactionManager transactionManager) {
        this.connectionPool = connectionPool;
        this.transactionManager = transactionManager;
    }

    @Override
    public boolean isInitialized() {
        return connectionPool.isInitialized();
    }

    @Override
    public boolean init() throws CouldNotInitializeConnectionPoolError {
        return connectionPool.init();
    }

    @Override
    public boolean shutdown() {
        return connectionPool.shutdown();
    }

    @Override
    public Connection takeConnection() throws InterruptedException {
        final Optional<TransactionId> transactionId = transactionManager.getTransactionId();
        return transactionId.isPresent()
                ? transactionId.get().getConnection()
                : new ProxyConnection(connectionPool.takeConnection(), this);
    }

    @Override
    public void returnConnection(Connection connection) {
        if (!transactionManager.getTransactionId().isPresent()) {
            connectionPool.returnConnection(((ProxyConnection) connection).getConnection());
        }
    }

    public static ConnectionPool getInstance(ConnectionPool connectionPool, TransactionManager transactionManager) {
        if(instance == null){
            instance = new TransactionConnectionPool(connectionPool,transactionManager);
        }
        return instance;
    }
}
