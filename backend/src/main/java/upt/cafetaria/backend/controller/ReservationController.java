package upt.cafetaria.backend.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import upt.cafetaria.backend.model.domain.Reservation;
import upt.cafetaria.backend.model.web.ReservationDto;
import upt.cafetaria.backend.service.ReservationService;

import java.time.LocalDate;
import java.util.List;

/**
 * Manages reservations for the cafeteria.
 * Provides endpoints to retrieve all reservations, unfulfilled and fulfilled reservations by user ID,
 * available times for reservations, and add new reservations.
 *
 * Endpoints:
 * - /all: GET request to retrieve all reservations.
 * - /user/{id}/unfulfilled: GET request to retrieve unfulfilled reservations by user ID.
 * - /user/{id}/fulfilled: GET request to retrieve fulfilled reservations by user ID.
 * - /available-times: GET request to retrieve available times for reservations.
 * - /add: POST request to add a new reservation.
 */
@RestController
@RequestMapping("api/reservation")
public class ReservationController {
    @Autowired
    private final ReservationService reservationService;

    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @GetMapping("/all")
    List<Reservation> allReservation() {
        return reservationService.getReservations();
    }

    @GetMapping("/user/{id}/unfulfilled")
    List<Reservation> getUnfulfilledReservationsByUserId(@PathVariable long id) {
        return reservationService.getUnfulfilledReservationsByUserId(id);
    }

    @GetMapping("/user/{id}/fulfilled")
    List<Reservation> getFulfilledReservationsByUserId(@PathVariable long id) {
        return reservationService.getFulfilledReservationsByUserId(id);
    }

    /**
     * Toggles the fulfillment status of a reservation.
     * When fulfillment is set to true, this method sets it to false and vice versa.
     * @param id ID of the reservation
     * @return Reservation object with fulfillment status toggled
     */
    @PutMapping("/toggle-fulfill/{id}")
    Reservation toggleFulfill(@PathVariable Long id) {
        return reservationService.toggleFulfill(id);
    }

    @GetMapping("/available-times")
    List<String> getAvailableTimes() {
        return reservationService.getAvailableTimes();
    }

    @PostMapping("/add")
    Reservation addReservation(@Valid @RequestBody ReservationDto reservationDto) {
        return reservationService.addReservation(reservationDto);
    }
}
