package upt.cafetaria.backend.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import upt.cafetaria.backend.model.domain.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
}