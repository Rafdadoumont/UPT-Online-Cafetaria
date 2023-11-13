package upt.cafetaria.backend.model.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import upt.cafetaria.backend.model.domain.Product;
import upt.cafetaria.backend.model.domain.User;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "reservation")
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private LocalDate reservationDate;
    private LocalDate creationDate;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToMany
    @JoinTable(
            name = "reservation_product",
            joinColumns = @JoinColumn(name = "reservation_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    private List<Product> products;

    public Reservation() {}

    public Reservation(long id, LocalDate reservationDate, LocalDate creationDate) {
        this.id = id;
        this.reservationDate = reservationDate;
        this.creationDate = creationDate;
    }
}
