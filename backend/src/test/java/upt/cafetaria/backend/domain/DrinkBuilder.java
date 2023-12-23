package upt.cafetaria.backend.domain;

import upt.cafetaria.backend.model.domain.Drink;
import upt.cafetaria.backend.model.web.DrinkDto;

public class DrinkBuilder {
    private long productId;
    private String name;
    private double price;
    private String description;
    private boolean isActive;
    private boolean isSparkling;
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

    public static DrinkBuilder newDrink3(){
        return newDrink()
                .withName("Jelly")
                .withPrice(2.0)
                .withDescription("Information");
    };
    public static DrinkBuilder newDrink4(){
        return newDrink()
                .withName("Jelly")
                .withPrice(2.0)
                .withDescription("Information");
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
    public DrinkBuilder withIsSparkling(boolean isSparkling){
        this.isSparkling = isSparkling;
        return this;
    };
    public Drink build(){
        Drink drink = new Drink();
        drink.setName(name);
        drink.setPrice(price);
        drink.setDescription(description);
        drink.setActive(isActive);
        return drink;
    }

    public DrinkDto build2(){
        DrinkDto drink = new DrinkDto();
        drink.setName(name);
        drink.setPrice(price);
        drink.setDescription(description);
        return drink;
    }
}
