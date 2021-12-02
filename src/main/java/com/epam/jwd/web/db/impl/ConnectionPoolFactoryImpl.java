package com.epam.jwd.web.db.impl;

import com.epam.jwd.web.db.*;

public class ConnectionPoolFactoryImpl implements ConnectionPoolFactory {

    private static ConnectionPoolFactoryImpl instance;

    @Override
    public ConnectionPool getBy(ConnectionPoolType type) {
        switch (type){
            case SIMPLE_CONNECTION_POOL:
                return LockingConnectionPool.getInstance();
            case TRANSACTION_CONNECTION_POOL:
                ConnectionPool connectionPool = getBy(ConnectionPoolType.SIMPLE_CONNECTION_POOL);
                TransactionManager transactionManager = TransactionManagerFactory.newInstance()
                        .managerFor(TransactionManagerType.SIMPLE_TRANSACTION_MANAGER);
                return TransactionConnectionPool.getInstance(connectionPool,transactionManager);
        }
        return null;//todo exception
    }

    public static ConnectionPoolFactoryImpl getInstance(){
        if(instance == null){
            instance = new ConnectionPoolFactoryImpl();
        }
        return instance;
    }
}
