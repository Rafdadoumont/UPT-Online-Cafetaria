package upt.cafetaria.backend.model;

import jakarta.persistence.*;

@Entity
@Table(name = "cafetaria")
public class Cafetaria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String university;

    private String address;

    private boolean isOpen;
}
