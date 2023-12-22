package upt.cafetaria.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import upt.cafetaria.backend.model.domain.Dessert;
import upt.cafetaria.backend.model.domain.Product;

import java.util.List;

public interface DessertRepository extends JpaRepository<Dessert, Long> {
    @Query("SELECT d FROM Dessert d WHERE d.isActive = :isActive")
    List<Dessert> findAllByActive(boolean isActive);
}
