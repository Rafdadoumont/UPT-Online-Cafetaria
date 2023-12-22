package upt.cafetaria.backend.model.domain;

import jakarta.persistence.Entity;
import lombok.NoArgsConstructor;

/**
 * Defining this class as an entity to map it to database.
 * It extends Product which make it inherit its fields.
 * @author Jan Wieprow
 */
@Entity
@NoArgsConstructor
public class Dessert extends Product {}