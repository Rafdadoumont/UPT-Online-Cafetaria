package upt.cafetaria.backend.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import upt.cafetaria.backend.model.domain.Drink;
import upt.cafetaria.backend.model.enums.SugarLevelEnum;
import upt.cafetaria.backend.model.web.DrinkDto;
import upt.cafetaria.backend.service.DrinkService;
import java.util.List;

/**
 * Manages CRUD(Create, Read, Update, Delete) operations for drinks available in the cafeteria.
 * Provides endpoints to retrieve all drinks, add a new drink,
 * update an existing drink, delete a drink, and retrieve all sugar levels.
 *
 * Endpoints:
 * - /all: GET request to retrieve all drinks.
 * - /add: POST request to add a new drink.
 * - /update/{id}: PUT request to update an existing drink by ID.
 * - /delete/{id}: DELETE request to delete a drink by ID.
 * - /sugarlevels: GET request to retrieve all available sugar levels.
 * @author Rúben Santos
 */
@RestController
@RequestMapping("api/drink")
public class DrinkController {

    @Autowired
    private final DrinkService drinkService;

    public DrinkController(DrinkService drinkService) {
        this.drinkService = drinkService;
    }

    @GetMapping("/all")
    List<Drink> allDrinks() {
        return drinkService.getDrinks();
    }

    @PostMapping("/add")
    Drink addDrink(@Valid @RequestBody DrinkDto drinkDto) {
        return drinkService.addDrink(drinkDto);
    }

    @PutMapping("/update/{id}")
    Drink updateDrink(@Valid @RequestBody DrinkDto newDrink, @PathVariable Long id) {
        Drink updatedDrink = drinkService.getDrink(id);
        drinkService.updateDrink(id, newDrink);
        return updatedDrink;
    }

    @DeleteMapping("/delete/{id}")
    public Drink deleteDrink(@PathVariable Long id) {
        Drink deletedDrink = drinkService.getDrink(id);
        drinkService.deleteDrink(id);
        return deletedDrink;
    }

    @GetMapping("/sugarlevels")
    public List<SugarLevelEnum> allSugarLevels() {
        return drinkService.getSugarLevels();
    }
}
