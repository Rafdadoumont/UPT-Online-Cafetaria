package upt.cafetaria.backend.model.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import upt.cafetaria.backend.model.enums.SugarLevelEnum;

@Entity
@Setter
@Getter
@NoArgsConstructor
@Table(name = "drink")
public class Drink extends Product{
    boolean isSparkling;
    boolean hasCaffeine;
    SugarLevelEnum sugarLevel;
}