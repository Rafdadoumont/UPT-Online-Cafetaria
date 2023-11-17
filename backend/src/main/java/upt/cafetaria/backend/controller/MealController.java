package upt.cafetaria.backend.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import upt.cafetaria.backend.model.domain.Meal;
import upt.cafetaria.backend.model.domain.MealTypeEnum;
import upt.cafetaria.backend.model.web.MealDto;
import upt.cafetaria.backend.service.MealService;

import java.util.List;

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
