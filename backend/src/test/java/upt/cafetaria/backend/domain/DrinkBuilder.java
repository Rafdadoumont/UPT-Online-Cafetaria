package upt.cafetaria.backend.domain;

import upt.cafetaria.backend.model.domain.Drink;
public class DrinkBuilder {
    private long productId;
    private String name;
    private double price;
    private String description;
    private boolean isActive;
    public static DrinkBuilder newDrink(){
        return new DrinkBuilder();
    };
    public static DrinkBuilder newDrink1(){
        return  newDrink()
                .withName("Jelly")
                .withPrice(2.0)
                .withDescription("Information")
                .withIsActive(true);
    };
    public static DrinkBuilder newDrink2(){
        return newDrink()
                .withName("Jelly")
                .withPrice(2.0)
                .withDescription("Information")
                .withIsActive(true);
    };

    public DrinkBuilder withName(String name){
        this.name = name;
        return this;
    };
    public DrinkBuilder withPrice(double price){
        this.price = price;
        return this;
    };

    public DrinkBuilder withDescription(String description){
        this.description = description;
        return this;
    };
    public DrinkBuilder withIsActive(boolean isActive){
        this.isActive = isActive;
        return this;
    }
    public Drink build(){
        Drink drink = new Drink();
        drink.setName(name);
        drink.setPrice(price);
        drink.setDescription(description);
        drink.setActive(isActive);
        return drink;
    }
}
