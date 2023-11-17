package upt.cafetaria.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import upt.cafetaria.backend.model.domain.Soup;

public interface SoupRepository extends JpaRepository<Soup, Long> {
}
