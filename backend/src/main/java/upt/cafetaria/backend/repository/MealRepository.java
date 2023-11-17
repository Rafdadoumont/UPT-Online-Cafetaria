package upt.cafetaria.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import upt.cafetaria.backend.model.domain.Meal;

public interface MealRepository extends JpaRepository<Meal, Long> {
}
