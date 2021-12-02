package com.epam.jwd.web.db;

import com.epam.jwd.web.db.impl.TransactionManagerFactoryImpl;

public interface TransactionManagerFactory {
    TransactionManager managerFor(TransactionManagerType type);

    static TransactionManagerFactory newInstance(){
        return TransactionManagerFactoryImpl.getInstance();
    }
}
