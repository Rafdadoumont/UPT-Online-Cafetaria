package upt.cafetaria.backend.model.domain;

public enum SugarLevelEnum {
    ZERO_SUGARS("Zero sugar"),
    NO_ADDED_SUGARS("No added sugar"),
    ADDED_SUGARS("Added sugar");

    private final String stringValue;

    SugarLevelEnum(String stringValue) {
        this.stringValue = stringValue;
    }

    @Override
    public String toString() {
        return stringValue;
    }
}
