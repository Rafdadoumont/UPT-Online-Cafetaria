package upt.cafetaria.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import upt.cafetaria.backend.model.domain.Dessert;

public interface DessertRepository extends JpaRepository<Dessert, Long> {
}
