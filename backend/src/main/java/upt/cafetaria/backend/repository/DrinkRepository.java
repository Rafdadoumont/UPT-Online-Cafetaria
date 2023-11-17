package upt.cafetaria.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import upt.cafetaria.backend.model.domain.Drink;

public interface DrinkRepository extends JpaRepository<Drink, Long> {
}
