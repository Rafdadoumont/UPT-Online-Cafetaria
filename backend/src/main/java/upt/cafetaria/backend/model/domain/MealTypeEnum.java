package upt.cafetaria.backend.model.domain;

public enum MealTypeEnum {
    MEAT("Meat"),
    FISH("Fish"),
    VEGGIE("Veggie");

    private final String stringValue;

    MealTypeEnum(String stringValue) {
        this.stringValue = stringValue;
    }

    @Override
    public String toString() {
        return stringValue;
    }
}
