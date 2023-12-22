package upt.cafetaria.backend.domain;

import upt.cafetaria.backend.model.domain.Meal;

/**
 * The MealBuilder class provides methods to construct Meal objects
 * with various attributes for testing or initialization purposes.
 */
public class MealBuilder {
    private long productId;
    private String name;
    private double price;
    private String description;
    private boolean isActive;

    public static MealBuilder newMeal(){
        return new MealBuilder();
    }

    public static MealBuilder newMeal1(){
        return  newMeal()
                .withName("Meal10")
                .withPrice(1.0)
                .withDescription("Information")
                .withIsActive(true);
    }

    public static MealBuilder newMeal2(){
        return  newMeal()
                .withName("Soup20")
                .withPrice(1.0)
                .withDescription("Information")
                .withIsActive(true);
    }

    public MealBuilder withName(String name){
        this.name = name;
        return this;
    }

    public MealBuilder withPrice(double price){
        this.price = price;
        return this;
    }

    public MealBuilder withDescription(String description){
        this.description = description;
        return this;
    }

    public MealBuilder withIsActive(boolean isActive){
        this.isActive = isActive;
        return this;
    }

    public Meal build(){
        Meal meal = new Meal();
        meal.setName(name);
        meal.setPrice(price);
        meal.setDescription(description);
        meal.setActive(isActive);
        return meal;
    }
}
