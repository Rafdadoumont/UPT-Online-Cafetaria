package upt.cafetaria.backend.domain;

import upt.cafetaria.backend.model.domain.Product;
import upt.cafetaria.backend.model.web.ProductDto;

public class ProductBuilder {
    private long productId;
    private String name;
    private double price;
    private String description;
    private boolean isActive;
    public static ProductBuilder product(){return new ProductBuilder();}
    public static ProductBuilder product1(){
        return product()
                .withProductId(1L)
                .withName("Dinner")
                .withPrice(5.0)
                .withDescription("Description")
                .withIsActive(true);
    };

    public static ProductBuilder product2(){
        return product()
                .withProductId(2L)
                .withName("Dinner")
                .withPrice(5.0)
                .withDescription("Description")
                .withIsActive(true);
    };

    public static ProductBuilder product3(){
        return product()
                .withProductId(2L)
                .withName("Dinner")
                .withPrice(10.0)
                .withDescription("Description")
                .withIsActive(true);
    };
    public ProductBuilder withName(String name){
        this.name = name;
        return this;
    };

    public ProductBuilder withPrice(Double price){
        this.price = price;
        return this;
    };
    public ProductBuilder withDescription(String description){
        this.description = description;
        return this;
    };
    public ProductBuilder withIsActive(Boolean isActive){
        this.isActive = isActive;
        return this;
    };
    public ProductBuilder withProductId(long productId){
        this.productId = productId;
        return this;
    };

    public Product build(){
        Product product = new Product();
        product.setName(name);
        product.setPrice(price);
        product.setProductId(productId);
        product.setDescription(description);
        product.setActive(isActive);
        return product;
    };

    public ProductDto build2(){
        ProductDto product = new ProductDto();
        product.setName(name);
        product.setPrice(price);
        product.setId(productId);
        product.setDescription(description);
        product.setActive(isActive);
        return product;
    };
}
