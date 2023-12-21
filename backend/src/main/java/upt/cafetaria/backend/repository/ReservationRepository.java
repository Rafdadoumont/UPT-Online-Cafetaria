package upt.cafetaria.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import upt.cafetaria.backend.model.domain.Reservation;

import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    List<Reservation> findByUserUserIdAndFulfilledOrderByReservationDateDescReservationTimeDesc(Long userId, boolean fulfilled);
}
