package com.epam.jwd.web.db;

import com.epam.jwd.web.db.impl.ConnectionPoolFactoryImpl;

public interface ConnectionPoolFactory {
    ConnectionPool getBy(ConnectionPoolType type);

    static ConnectionPoolFactory newInstance(){
        return ConnectionPoolFactoryImpl.getInstance();
    }
}
