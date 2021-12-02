package com.epam.jwd.web.db;

import com.epam.jwd.web.db.impl.ThreadLocalTransactionManager;

import java.util.Optional;

public interface TransactionManager {

    boolean isTransaction();

    void initTransaction();

    void commitTransaction();

    Optional<TransactionId> getTransactionId();

    static TransactionManager newInstance(){
        return ThreadLocalTransactionManager.getInstance();
    }
}
