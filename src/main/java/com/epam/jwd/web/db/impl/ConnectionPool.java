package com.epam.jwd.web.db.impl;

import com.epam.jwd.web.db.LockingConnectionPool;

import java.sql.Connection;

public interface ConnectionPool {
    boolean init();
    boolean shutdown();
    Connection takeConnection() throws InterruptedException;
    void returnConnection(Connection connection);
    static ConnectionPool newInstance(){
        return LockingConnectionPool.getInstance();
    }
}
