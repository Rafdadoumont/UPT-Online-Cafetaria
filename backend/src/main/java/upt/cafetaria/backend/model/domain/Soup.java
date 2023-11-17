package upt.cafetaria.backend.model.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "soup")
public class Soup extends Product {
    public Soup() {super();}
}
