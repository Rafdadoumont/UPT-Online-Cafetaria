package upt.cafetaria.backend.model.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import upt.cafetaria.backend.model.enums.MealTypeEnum;

/**
 *Defining this class as an entity to map it to database.
 * It extends Product which make it inherit its fields.
 * It also adds new field.
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
public class Meal extends Product{
    MealTypeEnum mealType;
}
