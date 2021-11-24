package com.epam.jwd.web.model;

import com.epam.jwd.web.exception.EntityNotFoundException;

public interface Entity {
    Long getId();

    static Entity newInstance(TypeEntity type) throws EntityNotFoundException {
        switch (type) {
            case ADDRESS:
                return Address.of();
            case CAFE_ORDER:
                return CafeOrder.of();
            case CARD:
                return Card.of();
            case MEAL:
                return Meal.of();
            case ORDER_ELEMENT:
                return OrderElement.of();
            case USER:
                return User.of();
            case USER_DETAILS:
                return UserDetails.of();
            default:
                throw new EntityNotFoundException("This type of Entity not found!");
        }
    }
}
