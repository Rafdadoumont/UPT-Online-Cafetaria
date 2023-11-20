package upt.cafetaria.backend.model.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import upt.cafetaria.backend.model.enums.MealTypeEnum;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Meal extends Product{
    MealTypeEnum mealType;
}
