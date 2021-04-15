package ru.rinatisangulov.dishes.service;

import org.springframework.stereotype.Service;
import ru.rinatisangulov.dishes.model.Dish;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class DishServiceImpl implements DishService {

    // Хранилище блюд
    private static final Map<Integer, Dish> DISH_REPOSITORY_MAP = new HashMap<>();

    // Переменная для генерации ID блюда
    private static final AtomicInteger DISH_ID_HOLDER = new AtomicInteger();

    @Override
    public void create(Dish dish) {
        final int dishId = DISH_ID_HOLDER.incrementAndGet();
        dish.setId(dishId);
        DISH_REPOSITORY_MAP.put(dishId, dish);
    }

    @Override
    public List<Dish> readAll() {
        return new ArrayList<>(DISH_REPOSITORY_MAP.values());
    }

    @Override
    public Dish read(int id) {
        return DISH_REPOSITORY_MAP.get(id);
    }

    @Override
    public boolean update(Dish dish, int id) {
        if (DISH_REPOSITORY_MAP.containsKey(id)) {
            dish.setId(id);
            DISH_REPOSITORY_MAP.put(id, dish);
            return true;
        }
        return false;
    }

    @Override
    public boolean delete(int id) {
        return DISH_REPOSITORY_MAP.remove(id) != null;
    }
}