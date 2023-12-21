package upt.cafetaria.backend.model.domain;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;

/**
 *Defining this class as an entity to map it to database.
 * It extends Product which make it inherit its fields.
 */
@Entity
@NoArgsConstructor
public class Soup extends Product {}
