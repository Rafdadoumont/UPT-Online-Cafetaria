package upt.cafetaria.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import upt.cafetaria.backend.model.domain.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
}