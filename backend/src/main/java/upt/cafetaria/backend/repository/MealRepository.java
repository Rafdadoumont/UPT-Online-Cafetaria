package upt.cafetaria.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import upt.cafetaria.backend.model.domain.Meal;
import java.util.List;

public interface MealRepository extends JpaRepository<Meal, Long> {
    @Query("SELECT m FROM Meal m WHERE m.isActive = :isActive")
    List<Meal> findAllByActive(boolean isActive);
}
