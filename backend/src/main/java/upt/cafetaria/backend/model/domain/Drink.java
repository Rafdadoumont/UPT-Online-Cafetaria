package upt.cafetaria.backend.model.domain;

public class Drink {

    Boolean sparkling;
    Boolean Cafeine;
    SugarLevelEnum sugarLevel;

    public Drink(Boolean cafeine, Boolean sparkling, SugarLevelEnum sugarLevel) {
        this.Cafeine = cafeine;
        this.sparkling = sparkling;
        this.sugarLevel = sugarLevel;
    };


    public void setSparkling(Boolean sparkling) {
        this.sparkling = sparkling;
    }

    public void setSugarLevel(SugarLevelEnum sugarLevel) {
        this.sugarLevel = sugarLevel;
    }

    public void setCafeine(Boolean cafeine) {
        Cafeine = cafeine;
    }

    public Boolean getCafeine() {
        return Cafeine;
    }

    public Boolean getSparkling() {
        return sparkling;
    }

    public SugarLevelEnum getSugarLevel() {
        return sugarLevel;
    }
}
