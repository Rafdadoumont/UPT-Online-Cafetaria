package upt.cafetaria.backend.model.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

/**
 * Defining this class as an entity to map it to database.
 * @author Jan Wieprow
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
public class Reservation {

    /**
     *Create new field "id" that is used as primary key and automatically assign unique value.
     * @author Jan Wieprow
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long reservationId;

    private LocalDate reservationDate;

    private LocalTime reservationTime;

    private LocalDate creationDate;

    private boolean fulfilled;

    //TO-BE ADDED LATER ON
    //private String paymentMethod;

    /**
     * Creating many-to-one relation to user.
     * @author Jan Wieprow
     */
    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "user_id")
    private User user;

    /**
     * Creating many-to-many relation with products.
     * @author Jan Wieprow
     */
    @ManyToMany
    @JoinTable(
            name = "reservation_product",
            joinColumns = @JoinColumn(name = "reservation_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    private List<Product> products;
}