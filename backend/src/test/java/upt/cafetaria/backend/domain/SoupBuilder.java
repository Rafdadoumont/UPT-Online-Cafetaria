package upt.cafetaria.backend.domain;

import upt.cafetaria.backend.model.domain.Soup;
public class SoupBuilder {
    private long productId;
    private String name;
    private double price;
    private String description;
    private boolean isActive;

    public static SoupBuilder newSoup(){
        return new SoupBuilder();
    }

    public static SoupBuilder newSoup1(){
        return  newSoup()
                .withName("Soup10")
                .withPrice(1.0)
                .withDescription("Information")
                .withIsActive(true);
    }

    public static SoupBuilder newSoup2(){
        return  newSoup()
                .withName("Soup20")
                .withPrice(1.0)
                .withDescription("Information")
                .withIsActive(true);
    }

    public SoupBuilder withName(String name){
        this.name = name;
        return this;
    }

    public SoupBuilder withPrice(double price){
        this.price = price;
        return this;
    }

    public SoupBuilder withDescription(String description){
        this.description = description;
        return this;
    }

    public SoupBuilder withIsActive(boolean isActive){
        this.isActive = isActive;
        return this;
    }

    public Soup build(){
        Soup soup = new Soup();
        soup.setName(name);
        soup.setPrice(price);
        soup.setDescription(description);
        soup.setActive(isActive);
        return soup;
    }
}