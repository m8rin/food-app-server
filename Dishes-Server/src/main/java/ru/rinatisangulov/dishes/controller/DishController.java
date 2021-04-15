package ru.rinatisangulov.dishes.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.rinatisangulov.dishes.model.Dish;
import ru.rinatisangulov.dishes.service.DishService;

import java.util.List;

@RestController
public class DishController {

    private final DishService dishService;

    @Autowired
    public DishController(DishService dishService) {
        this.dishService = dishService;
    }

    @GetMapping(value = "/dishes")
    public ResponseEntity<List<Dish>> read() {
        final List<Dish> dishes = dishService.readAll();

        return dishes != null &&  !dishes.isEmpty()
                ? new ResponseEntity<>(dishes, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/dishes/{id}")
    public ResponseEntity<Dish> read(@PathVariable(name = "id") int id) {
        final Dish dish = dishService.read(id);

        return dish != null
                ? new ResponseEntity<>(dish, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping(value = "/dishes")
    public ResponseEntity<?> create(@RequestBody Dish dish) {
        dishService.create(dish);
        System.out.println("\nДобавлено блюдо:"  +"\nid: " +  dish.getId()+"\nname: " +  dish.getName() + "\nprice: " + dish.getPrice() +
                "\ndescription: " + dish.getDescription());

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping(value = "/dishes/{id}")
    public ResponseEntity<?> update(@PathVariable(name = "id") int id, @RequestBody Dish dish) {
        final boolean updated = dishService.update(dish, id);

        System.out.println("\nОбновлено блюдо:"  +"\nid: " +  dish.getId()+"\nname: " +  dish.getName() + "\nprice: " + dish.getPrice() +
                "\ndescription: " + dish.getDescription());

        return updated
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @DeleteMapping(value = "/dishes/{id}")
    public ResponseEntity<?> delete(@PathVariable(name = "id") int id) {
        final boolean deleted = dishService.delete(id);

        System.out.println("\nУдалено блюдо с id: " + id);

        return deleted
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }
}