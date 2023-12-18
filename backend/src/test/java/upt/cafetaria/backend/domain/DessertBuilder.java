package upt.cafetaria.backend.domain;

import upt.cafetaria.backend.model.domain.Dessert;

public class DessertBuilder {
    private long productId;
    private String name;
    private double price;
    private String description;
    private boolean isActive;
public static DessertBuilder newDessert(){
    return new DessertBuilder();
};
    public static DessertBuilder newDessert1(){
        return  newDessert()
                .withName("Jelly")
                .withPrice(2.0)
                .withDescription("Information")
                .withIsActive(true);
    };
    public static DessertBuilder newDessert2(){
        return newDessert()
                .withName("Jelly")
                .withPrice(2.0)
                .withDescription("Information")
                .withIsActive(true);
    };

    public DessertBuilder withName(String name){
        this.name = name;
        return this;
    };
    public DessertBuilder withPrice(double price){
        this.price = price;
        return this;
    };

    public DessertBuilder withDescription(String description){
        this.description = description;
        return this;
    };
    public DessertBuilder withIsActive(boolean isActive){
        this.isActive = isActive;
        return this;
    }
    public Dessert build(){
        Dessert dessert = new Dessert();
        dessert.setName(name);
        dessert.setPrice(price);
        dessert.setDescription(description);
        dessert.setActive(isActive);
        return dessert;
    }
}
