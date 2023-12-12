package upt.cafetaria.backend.model.web;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Getter
@Setter
public class ReservationDto {
    private long id;

    private List<Long> productIds;

    private long userId;

    @NotNull(message = "reservation.date.null")
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @FutureOrPresent(message = "reservation.date.future")
    private LocalDate reservationDate;

    private LocalTime reservationTime;
}