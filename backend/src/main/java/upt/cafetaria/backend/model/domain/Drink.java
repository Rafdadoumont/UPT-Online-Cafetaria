package upt.cafetaria.backend.model.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import upt.cafetaria.backend.model.enums.SugarLevelEnum;

/**
 *Defining this class as an entity to map it to database.
 * It extends Product which make it inherit its fields.
 * It also adds new fields
 */
@Entity
@Setter
@Getter
@NoArgsConstructor
public class Drink extends Product{
    boolean isSparkling;

    boolean hasCaffeine;

    SugarLevelEnum sugarLevel;


}