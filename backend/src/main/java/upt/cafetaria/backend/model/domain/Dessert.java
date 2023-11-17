package upt.cafetaria.backend.model.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "dessert")
public class Dessert extends Product {
    public Dessert() {}
}
