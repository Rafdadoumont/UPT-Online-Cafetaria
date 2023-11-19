package upt.cafetaria.backend.model.domain;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Table(name = "soup")
public class Soup extends Product {}
