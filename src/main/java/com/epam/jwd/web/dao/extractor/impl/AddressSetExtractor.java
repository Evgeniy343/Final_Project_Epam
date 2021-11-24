package com.epam.jwd.web.dao.extractor.impl;

import com.epam.jwd.web.dao.extractor.ResultSetExtractor;
import com.epam.jwd.web.exception.EntityNotFoundException;
import com.epam.jwd.web.model.*;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AddressSetExtractor implements ResultSetExtractor<Address> {

    private static final String ID_FIELD_NAME = "ad.id";
    private static final String CITY_NAME_FIELD_NAME = "c.с_name";
    private static final String STREET_NAME_FIELD_NAME = "s.s_name";
    private static final String HOUSE_FIELD_NAME = "ad.house";
    private static final String FLAT_FIELD_NAME = "ad.flat";

    private AddressSetExtractor() {
    }

    public static AddressSetExtractor of() {
        return new AddressSetExtractor();
    }

    @Override
    public Address extract(ResultSet resultSet) throws SQLException, EntityNotFoundException {
        return Address.with()
                .id(resultSet.getLong(ID_FIELD_NAME))
                .city(createCity(resultSet))
                .street(createStreet(resultSet))
                .house(resultSet.getString(HOUSE_FIELD_NAME))
                .flat(resultSet.getInt(FLAT_FIELD_NAME))
                .build();
    }

    private Street createStreet(ResultSet resultSet) throws EntityNotFoundException, SQLException {
        return (Street) SimpleEntity.newInstance(TypeEntity.STREET, null
                , resultSet.getString(STREET_NAME_FIELD_NAME));
    }

    private City createCity(ResultSet resultSet) throws EntityNotFoundException, SQLException {
        return (City) SimpleEntity.newInstance(TypeEntity.CITY, null, resultSet.getString(CITY_NAME_FIELD_NAME));
    }


}
