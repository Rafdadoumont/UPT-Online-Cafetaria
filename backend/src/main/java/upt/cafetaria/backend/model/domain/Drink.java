package upt.cafetaria.backend.model.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "drink")
public class Drink extends Product{
    boolean isSparkling;
    boolean hasCaffeine;
    SugarLevelEnum sugarLevel;

    public Drink(){};

    public void setIsSparkling(boolean sparkling) {
        this.isSparkling = sparkling;
    }

    public void setSugarLevel(SugarLevelEnum sugarLevel) {
        this.sugarLevel = sugarLevel;
    }

    public void setHasCaffeine(boolean hasCaffeine) {
        this.hasCaffeine = hasCaffeine;
    }

    public boolean getHasCaffeine() {
        return hasCaffeine;
    }

    public boolean getIsSparkling() {
        return isSparkling;
    }

    public SugarLevelEnum getSugarLevel() {
        return sugarLevel;
    }
}
