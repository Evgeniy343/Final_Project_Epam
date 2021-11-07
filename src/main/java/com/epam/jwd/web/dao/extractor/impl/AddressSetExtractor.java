package com.epam.jwd.web.dao.extractor.impl;

import com.epam.jwd.web.dao.extractor.ResultSetExtractor;
import com.epam.jwd.web.model.impl.Address;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AddressSetExtractor implements ResultSetExtractor<Address> {
    private static final String ID_FIELD_NAME = "id";
    private static final String HOUSE_FIELD_NAME = "house";
    private static final String FLAT_FIELD_NAME = "flat";

    @Override
    public Address extract(ResultSet resultSet) throws SQLException {
        return Address.of(
                resultSet.getLong(ID_FIELD_NAME),
                resultSet.getString(HOUSE_FIELD_NAME),
                resultSet.getInt(FLAT_FIELD_NAME)
        );
    }
}
