package com.epam.jwd.web.db.impl;

import com.epam.jwd.web.db.TransactionManager;
import com.epam.jwd.web.db.TransactionManagerFactory;
import com.epam.jwd.web.db.TransactionManagerType;

public class TransactionManagerFactoryImpl implements TransactionManagerFactory {

    private static TransactionManagerFactoryImpl instance;

    private TransactionManagerFactoryImpl() {
    }

    @Override
    public TransactionManager managerFor(TransactionManagerType type) {
        if (type == TransactionManagerType.SIMPLE_TRANSACTION_MANAGER) {
            return ThreadLocalTransactionManager.getInstance();
        }
        return null;//todo exception
    }

    public static TransactionManagerFactoryImpl getInstance(){
        if(instance == null){
            instance = new TransactionManagerFactoryImpl();
        }
        return instance;
    }
}
