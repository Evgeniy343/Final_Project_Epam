package com.epam.jwd.web.db;

import com.epam.jwd.web.db.impl.LockingConnectionPool;
import com.epam.jwd.web.db.impl.TransactionConnectionPool;

import java.sql.Connection;

public interface ConnectionPool {
    boolean init();

    boolean shutdown();

    boolean isInitialized();

    Connection takeConnection() throws InterruptedException;

    void returnConnection(Connection connection);

    static ConnectionPool locking() {
        return LockingConnectionPool.getInstance();
    }

    static ConnectionPool transactional() {
        return TransactionConnectionPool.getInstance();
    }
}
