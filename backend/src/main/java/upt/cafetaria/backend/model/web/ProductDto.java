package upt.cafetaria.backend.model.web;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

/**
 * Defining class ProductDto that connect backend and frontend.
 * Automatically creating getters and setters.
 */
@Getter
@Setter
public class ProductDto {
    private long id;

    @NotEmpty(message = "product.name.empty")
    private String name;

    @NotNull(message = "product.price.null")
    @Min(value = 0, message = "product.price.min.0")
    private Double price;

    private String description;

    private boolean isActive;
}
