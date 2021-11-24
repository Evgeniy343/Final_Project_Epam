package com.epam.jwd.web.model;

import com.epam.jwd.web.exception.EntityNotFoundException;

public interface SimpleEntity extends Entity {
    String getName();

    static SimpleEntity newInstance(TypeEntity type, Long id, String name) throws EntityNotFoundException {
        switch (type) {
            case BONUS:
                return Bonus.of(id, name);
            case STREET:
                return Street.of(id, name);
            case INGREDIENT:
                return Ingredient.of(id, name);
            case CITY:
                return City.of(id, name);
            case CATEGORY:
                return Category.of(id, name);
            default:
                throw new EntityNotFoundException("This type of Entity not found!");
        }
    }
}
