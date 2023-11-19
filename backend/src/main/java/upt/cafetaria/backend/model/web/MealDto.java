package upt.cafetaria.backend.model.web;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import upt.cafetaria.backend.model.enums.MealTypeEnum;

public class MealDto {
    private long id;

    @NotEmpty(message = "meal.name.empty")
    private String name;

    @NotNull(message = "meal.price.null")
    @Min(value = 0, message = "meal.price.min.0")
    private Double price;

    private String description;

    @NotNull(message = "meal.mealtype.null")
    private MealTypeEnum mealType;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public MealTypeEnum getMealType() {
        return mealType;
    }

    public void setMealType(MealTypeEnum mealType) {
        this.mealType = mealType;
    }
}
