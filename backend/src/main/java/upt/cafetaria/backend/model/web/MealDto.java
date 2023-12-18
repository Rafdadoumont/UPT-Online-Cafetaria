package upt.cafetaria.backend.model.web;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import upt.cafetaria.backend.model.enums.MealTypeEnum;

@Getter
@Setter
public class MealDto {
    private long id;

    @NotEmpty(message = "meal.name.empty")
    private String name;

    @NotNull(message = "meal.price.null")
    @Min(value = 0, message = "meal.price.min.0")
    private Double price;

    private String description;

    @NotNull(message = "meal.mealtype.null")
    private String mealType;
}
