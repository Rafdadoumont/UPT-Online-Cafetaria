package upt.cafetaria.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import upt.cafetaria.backend.exceptions.ServiceException;
import upt.cafetaria.backend.model.domain.Product;
import upt.cafetaria.backend.model.domain.Reservation;
import upt.cafetaria.backend.model.web.ReservationDto;
import upt.cafetaria.backend.repository.ReservationRepository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class ReservationService {
    @Autowired
    private ReservationRepository reservationRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private ProductService productService;

    public List<Reservation> getReservations() {
        return reservationRepository.findAll();
    }

    public List<Reservation> getUnfulfilledReservationsByUserId(long id) {
        return reservationRepository.findByUserUserIdAndFulfilledOrderByReservationDateDescReservationTimeDesc(id, false);
    }

    public List<Reservation> getFulfilledReservationsByUserId(long id) {
        return reservationRepository.findByUserUserIdAndFulfilledOrderByReservationDateDescReservationTimeDesc(id, true);
    }

    public Reservation getReservation(long id) {
        return reservationRepository.findById(id).orElseThrow(() -> new ServiceException("GET", "reservation.not.found"));
    }

    public Reservation addReservation(ReservationDto dto)  {
        Reservation reservation = new Reservation();
        try {
            reservation.setReservationDate(dto.getReservationDate());
            reservation.setReservationTime(dto.getReservationTime());
            reservation.setCreationDate(LocalDate.now());
            reservation.setUser(userService.getUser(dto.getUserId()));
            reservation.setProducts(productService.getProductsById(dto.getProductIds()));
            reservation.setFulfilled(false);
            return reservationRepository.save(reservation);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return null;
    }

    public void fulfilReservation(long id) {
        Reservation reservation = getReservation(id);
        reservation.setFulfilled(true);
    }

    public List<Product> getProductsByReservationId(long id) {
        Reservation reservation = getReservation(id);
        return reservation.getProducts();
    }

    public List<String> getAvailableTimes() {
        List<String> availableTimes = new ArrayList<>();

        LocalTime startTime = LocalTime.of(12,0);

        while (startTime.isBefore(LocalTime.of(15, 0))) {
            availableTimes.add(startTime.toString());
            startTime = startTime.plusMinutes(5);
        }

        System.out.println(availableTimes);
        return availableTimes;
    }

    public Reservation toggleFulfill(Long id) {
        Reservation reservation = getReservation(id);

        reservation.setFulfilled(!reservation.isFulfilled());
        return reservationRepository.save(reservation);
    }
}
