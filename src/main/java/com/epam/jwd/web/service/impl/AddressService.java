package com.epam.jwd.web.service.impl;

import com.epam.jwd.web.dao.AddressDao;
import com.epam.jwd.web.model.Address;
import com.epam.jwd.web.service.EntityService;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.locks.ReentrantLock;

public class AddressService implements EntityService<Address> {
    private final AddressDao addressDao;
    private static AddressService instance;
    private static final ReentrantLock lock = new ReentrantLock();

    private AddressService(AddressDao addressDao) {
        this.addressDao = addressDao;
    }

    static AddressService getInstance(AddressDao addressDao) {
        if (instance == null) {
            lock.lock();
            {
                if (instance == null) {
                    instance = new AddressService(addressDao);
                }
            }
            lock.unlock();
        }
        return instance;
    }


    @Override
    public Address get(Long id) throws SQLException {
        Optional<Address> address = addressDao.read(id);
        return address.orElse(null);//todo exception
    }

    @Override
    public List<Address> findAll() throws SQLException, InterruptedException {
        return addressDao.read();
    }
}
