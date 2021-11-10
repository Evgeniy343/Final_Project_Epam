package com.epam.jwd.web.model.factory;

import com.epam.jwd.web.model.*;
import com.epam.jwd.web.model.context.EntityContext;
import com.epam.jwd.web.model.context.SimpleEntityContext;
import com.epam.jwd.web.model.exception.EntityNotFoundException;
import com.epam.jwd.web.model.impl.*;

import java.util.concurrent.locks.ReentrantLock;

public class EntityFactory {
    private static final ReentrantLock lock = new ReentrantLock();
    private static EntityFactory instance;

    private EntityFactory() {
    }

    public Entity createModel(TypeModel type, EntityContext context, SimpleEntityContext simpleContext)
            throws EntityNotFoundException {
        switch (type) {
            case CAFE_ORDER_MODEL:
                return CafeOrder.of(context.getId(), context.getCafeOrderPrice(), context.getDate(),
                        context.getReview(), context.getRating());
            case ORDER_ELEMENT:
                return OrderElement.of(context.getId(),context.getTotalMealPrice()
                        ,context.getMealAmount(), context.getMealName());
            case ADDRESS_MODEL:
                return Address.of(context.getId(), context.getHouse(), context.getFlat());
            case MEAL_MODEL:
                return Meal.of(context.getId(), context.getMealName(), context.getMealPrice());
            case USER_MODEL:
                return User.of(context.getId(), context.getLogin(), context.getPassword());
            case USER_DETAILS_MODEL:
                return UserDetails.of(context.getId(), context.getPoint(), context.getBan());
            case CARD_MODEL:
                return Card.of(context.getId(), context.getCardAmount());
            case CITY_MODEL:
                return City.of(simpleContext.getId(), simpleContext.getName());
            case CATEGORY_MODEL:
                return Category.of(simpleContext.getId(), simpleContext.getName());
            case STATUS_MODEL:
                return Status.of(simpleContext.getId(), simpleContext.getName());
            case STREET_MODEL:
                return Street.of(simpleContext.getId(), simpleContext.getName());
            case ROLE_MODEL:
                return Role.of(simpleContext.getId(), simpleContext.getName());
            case INGREDIENT_MODEL:
                return Ingredient.of(simpleContext.getId(), simpleContext.getName());
            case BONUS_MODEL:
                return Bonus.of(simpleContext.getId(), simpleContext.getName());
            default:
                throw new EntityNotFoundException("This Entity not found");
        }
    }

    public static EntityFactory getInstance() {
        if (instance == null) {
            lock.lock();
            {
                if (instance == null) {
                    instance = new EntityFactory();
                }
            }
            lock.unlock();
        }
        return instance;
    }
}
