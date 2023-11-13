package upt.cafetaria.backend.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import upt.cafetaria.backend.model.domain.User;

public interface UserRepository extends JpaRepository<User, Long> {
}