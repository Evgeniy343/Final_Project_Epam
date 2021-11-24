package com.epam.jwd.web.service.impl;

import com.epam.jwd.web.dao.CardDao;
import com.epam.jwd.web.model.Card;
import com.epam.jwd.web.service.EntityService;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.locks.ReentrantLock;

public class CardService implements EntityService<Card> {
    private final CardDao cardDao;
    private static CardService instance;
    private static final ReentrantLock lock = new ReentrantLock();

    private CardService(CardDao cardDao) {
        this.cardDao = cardDao;
    }

    static CardService getInstance(CardDao cardDao) {
        if (instance == null) {
            lock.lock();
            {
                if (instance == null) {
                    instance = new CardService(cardDao);
                }
            }
            lock.unlock();
        }
        return instance;
    }


    @Override
    public Card get(Long id) throws SQLException {
        Optional<Card> card = cardDao.read(id);
        return card.orElse(null);//todo exception
    }

    @Override
    public List<Card> findAll() throws SQLException, InterruptedException {
        return cardDao.read();
    }
}
