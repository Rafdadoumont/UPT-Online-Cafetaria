package upt.cafetaria.backend.model.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

import static jakarta.persistence.InheritanceType.JOINED;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Inheritance(strategy=JOINED)
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long productId;

    private String name;

    private double price;

    private String description;

    @ManyToMany(mappedBy = "products")
    private List<Reservation> reservations;
}
