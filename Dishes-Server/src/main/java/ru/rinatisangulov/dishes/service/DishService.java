package ru.rinatisangulov.dishes.service;

import ru.rinatisangulov.dishes.model.Dish;

import java.util.List;

public interface DishService {

    //Возвращает список всех имеющихся блюд
    List<Dish> readAll();

    //Возвращает блюдо по его ID
    Dish read(int id);

    // Создает новое блюдо
    void create(Dish dish);

    // Обновляет блюдо с заданным ID
    boolean update(Dish dish, int id);

    //Удаляет блюдо с заданным ID
    boolean delete(int id);
}