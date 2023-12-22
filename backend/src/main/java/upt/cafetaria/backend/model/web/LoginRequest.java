package upt.cafetaria.backend.model.web;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

/**
 * Defining class LoginRequest that connect backend and frontend.
 * Automatically creating getters and setters.
 * @author Jan Wieprow
 */
@Getter
@Setter
public class LoginRequest {
    @NotBlank(message = "Email is required.")
    private String email;

    @NotBlank(message = "Password is required")
    private String password;
}
