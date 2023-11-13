package upt.cafetaria.backend.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import upt.cafetaria.backend.model.product.Product;

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

    @ManyToMany(mappedBy = "reservation_product")
    private List<Product> products;


    public Reservation() {}

    public Reservation(long id, LocalDate reservationDate, LocalDate creationDate) {
        this.id = id;
        this.reservationDate = reservationDate;
        this.creationDate = creationDate;
    }
}
