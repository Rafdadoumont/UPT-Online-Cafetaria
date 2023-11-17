package upt.cafetaria.backend.model.domain;

import jakarta.persistence.*;

import java.util.List;

import static jakarta.persistence.InheritanceType.JOINED;

@Entity
@Table(name = "product")
@Inheritance(strategy=JOINED)
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    private double price;

    private String description;

    @ManyToMany(mappedBy = "products")
    private List<Reservation> reservations;

    public Product() {}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
