package upt.cafetaria.backend.model.web;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class ReservationDto {
    private long id;

    private List<ProductDto> products;

    private UserDto user;

    @NotNull(message = "reservation.date.null")
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @FutureOrPresent(message = "reservation.date.future")
    private LocalDate reservationDate;

    @NotNull(message = "creation.date.null")
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate creationDate;
}