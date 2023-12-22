package upt.cafetaria.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import upt.cafetaria.backend.model.domain.Soup;
import java.util.List;

public interface SoupRepository extends JpaRepository<Soup, Long> {
    @Query("SELECT s FROM Soup s WHERE s.isActive = :isActive")
    List<Soup> findAllByActive(boolean isActive);
}
