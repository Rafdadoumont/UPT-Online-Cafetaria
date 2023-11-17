package upt.cafetaria.backend.model.domain;

public class Meal {
    MealTypeEnum mealType;



    public Meal(MealTypeEnum mealTypeEnum){
        this.mealType = mealTypeEnum;
    };

    public MealTypeEnum getMealType() {
        return mealType;
    }

    public void setMealType(MealTypeEnum newMealType){
        this.mealType = newMealType;
    };
}
