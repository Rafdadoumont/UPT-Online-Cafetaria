package upt.cafetaria.backend.model.web;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@Getter
@Setter
public class RefreshTokenRequest {
    @NotEmpty(message = "refreshtokenrequest.refreshtoken.empty")
    private String refreshToken;

    @NotEmpty(message = "refreshtokenrequest.email.empy")
    @Email(message = "refreshtokenrequest.email.format")
    private String email;
}
