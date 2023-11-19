package upt.cafetaria.backend.model.web;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import upt.cafetaria.backend.model.enums.SugarLevelEnum;

@Data
public class DrinkDto {
    private long id;

    @NotEmpty(message = "drink.name.empty")
    private String name;

    @NotNull(message = "drink.price.null")
    @Min(value = 0, message = "drink.price.min.0")
    private Double price;

    private String description;

    @NotNull(message = "drink.issparkling.null")
    private boolean isSparkling;

    @NotNull(message = "drink.hascaffeine.null")
    private boolean hasCaffeine;

    @NotNull(message = "drink.sugarlevel.null")
    private SugarLevelEnum sugarLevel;
}
