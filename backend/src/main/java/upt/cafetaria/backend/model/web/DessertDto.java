package upt.cafetaria.backend.model.web;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DessertDto {
    private long id;

    @NotEmpty(message = "dessert.name.empty")
    private String name;

    @NotNull(message = "dessert.price.null")
    @Min(value = 0, message = "dessert.price.min.0")
    private Double price;

    private String description;
}
