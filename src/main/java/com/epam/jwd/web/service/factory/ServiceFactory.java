package com.epam.jwd.web.service.factory;

import com.epam.jwd.web.model.Entity;
import com.epam.jwd.web.model.TypeEntity;
import com.epam.jwd.web.service.EntityService;

public interface ServiceFactory {
    <T extends Entity> EntityService<T> createService(TypeEntity type);
}
