package upt.cafetaria.backend.model.web;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import upt.cafetaria.backend.model.domain.SugarLevelEnum;

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

    public boolean isSparkling() {
        return isSparkling;
    }

    public void setSparkling(boolean sparkling) {
        isSparkling = sparkling;
    }

    public boolean isHasCaffeine() {
        return hasCaffeine;
    }

    public void setHasCaffeine(boolean hasCaffeine) {
        this.hasCaffeine = hasCaffeine;
    }

    public SugarLevelEnum getSugarLevel() {
        return sugarLevel;
    }

    public void setSugarLevel(SugarLevelEnum sugarLevel) {
        this.sugarLevel = sugarLevel;
    }
}
