package upt.cafetaria.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import upt.cafetaria.backend.exceptions.ServiceException;
import upt.cafetaria.backend.model.domain.Meal;
import upt.cafetaria.backend.model.enums.MealTypeEnum;
import upt.cafetaria.backend.model.web.MealDto;
import upt.cafetaria.backend.repository.MealRepository;

import java.util.List;

@Service
public class MealService {
    @Autowired
    private MealRepository mealRepository;

    public List<Meal> getMeals() { return mealRepository.findAll();}

    public Meal getMeal(long id) {
        return mealRepository.findById(id).orElseThrow(() -> new ServiceException("GET", "meal.not.found"));
    }

    public Meal addMeal(MealDto dto) {
        Meal meal = new Meal();

        meal.setName(dto.getName());
        meal.setPrice(dto.getPrice());
        meal.setDescription(dto.getDescription());
        meal.setMealType(dto.getMealType());
        return mealRepository.save(meal);
    }

    public Meal updateMeal(Long id, MealDto dto) {
        Meal meal = getMeal(id);

        meal.setName(dto.getName());
        meal.setPrice(dto.getPrice());
        meal.setDescription(dto.getDescription());
        meal.setMealType(dto.getMealType());

        return mealRepository.save(meal);
    }

    public void deleteMeal(Long id) {
        mealRepository.delete(getMeal(id));
    }

    public List<MealTypeEnum> getMealTypes() {
        return List.of(MealTypeEnum.values());
    }
}
