package upt.cafetaria.backend.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import upt.cafetaria.backend.model.domain.Reservation;
import upt.cafetaria.backend.model.web.ReservationDto;
import upt.cafetaria.backend.service.ReservationService;

import java.time.LocalDate;
import java.util.List;

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

    @GetMapping("/available-times")
    List<String> getAvailableTimes() {
        return reservationService.getAvailableTimes();
    }

    @PostMapping("/add")
    Reservation addReservation(@Valid @RequestBody ReservationDto reservationDto) {
        return reservationService.addReservation(reservationDto);
    }
}
