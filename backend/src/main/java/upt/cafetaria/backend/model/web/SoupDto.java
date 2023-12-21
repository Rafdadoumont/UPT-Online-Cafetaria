package upt.cafetaria.backend.model.web;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

/**
 * Defining class SoupDto that connect backend and frontend.
 * Automatically creating getters and setters.
 */
@Getter
@Setter
public class SoupDto {
    private long id;

    @NotEmpty(message = "soup.name.empty")
    private String name;

    @NotNull(message = "soup.price.null")
    @Min(value = 0, message = "soup.price.min.0")
    private Double price;

    private String description;
}
