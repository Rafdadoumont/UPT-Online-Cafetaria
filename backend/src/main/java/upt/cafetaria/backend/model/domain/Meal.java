package upt.cafetaria.backend.model.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "meal")
public class Meal extends Product{
    MealTypeEnum mealType;

    public Meal() {}

    public MealTypeEnum getMealType() {
        return mealType;
    }

    public void setMealType(MealTypeEnum mealType) {
        this.mealType = mealType;
    }
}
