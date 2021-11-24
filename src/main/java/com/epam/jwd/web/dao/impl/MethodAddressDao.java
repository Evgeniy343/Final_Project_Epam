package com.epam.jwd.web.dao.impl;

import com.epam.jwd.web.dao.AddressDao;
import com.epam.jwd.web.dao.CommonDao;
import com.epam.jwd.web.dao.extractor.impl.AddressSetExtractor;
import com.epam.jwd.web.db.impl.ConnectionPool;
import com.epam.jwd.web.exception.EntityExtractorNotFoundException;
import com.epam.jwd.web.exception.EntityNotFoundException;
import com.epam.jwd.web.model.Address;
import org.apache.logging.log4j.LogManager;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import static java.lang.String.join;

public class MethodAddressDao extends CommonDao<Address> implements AddressDao {

    private static final String ADDRESS_TABLE_NAME = "address ad " +
            "join city c on c.id = ad.city_id " +
            "join street s on s.id = ad.street_id";
    private static final List<String> FIELDS = Arrays.asList("ad.id", "c.с_name", "s.s_name", "ad.house", "ad.flat");
    private static final String ID_FIELD_NAME = "ad.id";

    private MethodAddressDao(ConnectionPool pool) throws EntityNotFoundException {
        super(pool, AddressSetExtractor.of(), LogManager.getLogger(MethodAddressDao.class));
    }

    public static MethodAddressDao of(ConnectionPool pool) throws EntityExtractorNotFoundException
            , EntityNotFoundException {
        return new MethodAddressDao(pool);
    }

    @Override
    protected String getTableName() {
        return ADDRESS_TABLE_NAME;
    }

    @Override
    protected List<String> getFields() {
        return FIELDS;
    }

    @Override
    protected String getIdFieldName() {
        return ID_FIELD_NAME;
    }

    @Override
    protected String getFieldForUpdate() {
        return join(" = ?, ", getFields());
    }

    @Override
    protected void fillEntity(PreparedStatement statement, Address entity) throws SQLException {
        statement.setLong(1, entity.getId());
        statement.setLong(2, entity.getCity().getId());
        statement.setLong(3, entity.getStreet().getId());
        statement.setString(4, entity.getHouse());
        statement.setInt(5, entity.getFlat());
    }
}
