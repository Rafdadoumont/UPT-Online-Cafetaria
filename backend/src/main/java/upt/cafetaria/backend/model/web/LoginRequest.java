package upt.cafetaria.backend.model.web;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
public class LoginRequest {
    @NotBlank(message = "Email is required.")
    private String email;

    @NotBlank(message = "Password is required")
    private String password;
}
