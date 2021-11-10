package com.epam.jwd.web.dao.extractor.impl;

import com.epam.jwd.web.dao.extractor.ResultSetExtractor;
import com.epam.jwd.web.model.TypeModel;
import com.epam.jwd.web.model.context.EntityContext;
import com.epam.jwd.web.model.exception.EntityNotFoundException;
import com.epam.jwd.web.model.factory.EntityFactory;
import com.epam.jwd.web.model.impl.Address;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AddressSetExtractor implements ResultSetExtractor<Address> {
    private static final String ID_FIELD_NAME = "id";
    private static final String HOUSE_FIELD_NAME = "house";
    private static final String FLAT_FIELD_NAME = "flat";

    private AddressSetExtractor() {
    }

    public static AddressSetExtractor of() {
        return new AddressSetExtractor();
    }

    @Override
    public Address extract(ResultSet resultSet, EntityFactory factory) throws SQLException, EntityNotFoundException {
        EntityContext addressContext = pushAddressContext(resultSet);
        return (Address) factory.createModel(TypeModel.ADDRESS_MODEL, addressContext, null);
    }

    private EntityContext pushAddressContext(ResultSet resultSet) throws SQLException {
        return EntityContext.with()
                .id(resultSet.getLong(ID_FIELD_NAME))
                .house(resultSet.getString(HOUSE_FIELD_NAME))
                .flat(resultSet.getInt(FLAT_FIELD_NAME))
                .build();
    }
}
