package upt.cafetaria.backend.model.web;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

/**
 * Defining class RefreshTokenRequest that connect backend and frontend.
 * Automatically creating getters and setters.
 */
@Getter
@Setter
public class RefreshTokenRequest {
    @NotEmpty(message = "refreshtokenrequest.refreshtoken.empty")
    private String refreshToken;
}
