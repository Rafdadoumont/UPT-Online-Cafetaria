package upt.cafetaria.backend.model.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.NoArgsConstructor;

//Defining this class as an entity to map it to database
@Entity
@NoArgsConstructor
public class Dessert extends Product {}