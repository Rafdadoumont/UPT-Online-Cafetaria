package upt.cafetaria.backend.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import upt.cafetaria.backend.model.domain.Meal;
import upt.cafetaria.backend.model.enums.MealTypeEnum;
import upt.cafetaria.backend.model.web.MealDto;
import upt.cafetaria.backend.service.MealService;

import java.util.List;

/**
 * Manages CRUD(Create, Read, Update, Delete) operations for meals offered in the cafeteria.
 * Provides endpoints to retrieve all meals, add a new meal,
 * update an existing meal, delete a meal, and retrieve all meal types.
 *
 * Endpoints:
 * - /all: GET request to retrieve all meals.
 * - /add: POST request to add a new meal.
 * - /update/{id}: PUT request to update an existing meal by ID.
 * - /delete/{id}: DELETE request to delete a meal by ID.
 * - /mealtypes: GET request to retrieve all available meal types.
 */

@RestController
@RequestMapping("api/meal")
public class MealController {
    @Autowired
    private final MealService mealService;

    public MealController(MealService mealService) {
        this.mealService = mealService;
    }

    @GetMapping("/all")
    List<Meal> allMeals() {
        return mealService.getMeals();
    }

    @PostMapping("/add")
    Meal addMeal(@Valid @RequestBody MealDto mealDto) {
        return mealService.addMeal(mealDto);
    }

    @PutMapping("/update/{id}")
    Meal updateMeal(@Valid @RequestBody MealDto newMeal, @PathVariable Long id) {
        Meal updatedMeal = mealService.getMeal(id);
        mealService.updateMeal(id, newMeal);
        return updatedMeal;
    }

    @DeleteMapping("/delete/{id}")
    public Meal deleteMeal(@PathVariable Long id) {
        Meal deletedMeal = mealService.getMeal(id);
        mealService.deleteMeal(id);
        return deletedMeal;
    }

    @GetMapping("/mealtypes")
    public List<MealTypeEnum> allMealTypes() {
        return mealService.getMealTypes();
    }
}
