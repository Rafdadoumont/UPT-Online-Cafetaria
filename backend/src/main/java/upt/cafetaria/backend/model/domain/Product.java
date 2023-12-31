package upt.cafetaria.backend.model.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;
import static jakarta.persistence.InheritanceType.JOINED;

/**
 *Defining this class as an entity to map it to database.
 * @author Jan Wieprow
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@Inheritance(strategy=JOINED)
public class Product {

    /**
     *Create new field "id" that is used as primary key and automatically assign unique value.
     * @author Jan Wieprow
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long productId;

    private String name;

    private double price;

    private String description;

    private boolean isActive;

    /**
     * Define field that have many-to-many relation.
     * @author Jan Wieprow
     */
    @JsonIgnore
    @ManyToMany(mappedBy = "products")
    private List<Reservation> reservations;
}
