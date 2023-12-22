package upt.cafetaria.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import upt.cafetaria.backend.model.domain.Product;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    @Query("SELECT p FROM Product p WHERE p.isActive = :isActive")
    List<Product> findAllByActive(boolean isActive);
}