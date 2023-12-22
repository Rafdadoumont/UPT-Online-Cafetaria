package upt.cafetaria.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import upt.cafetaria.backend.model.domain.Drink;
import upt.cafetaria.backend.model.domain.Product;

import java.util.List;

public interface DrinkRepository extends JpaRepository<Drink, Long> {
    @Query("SELECT d FROM Drink d WHERE d.isActive = :isActive")
    List<Drink> findAllByActive(boolean isActive);
}
