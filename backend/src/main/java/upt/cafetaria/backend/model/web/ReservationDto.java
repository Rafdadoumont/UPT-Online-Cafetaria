package upt.cafetaria.backend.model.web;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;

public class ReservationDto {
    private long id;

    @NotNull(message = "reservationdate.null")
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @FutureOrPresent(message = "reservationdate.future")
    private LocalDate reservationDate;

    @NotNull(message = "creationdate.null")
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate creationDate;

    private List<ProductDto> products;

    private UserDto user;
}
