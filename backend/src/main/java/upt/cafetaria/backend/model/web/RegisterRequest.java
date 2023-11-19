package upt.cafetaria.backend.model.web;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import upt.cafetaria.backend.model.enums.RoleEnum;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
    @NotEmpty(message = "Lastname is required")
    private String lastname;
    @NotBlank(message = "Firstname is required")
    private String firstname;
    @NotBlank(message = "Email is required")
    private String email;
    @NotBlank(message = "Password is required")
    private String password;
    @NotBlank(message = "Role is required")
    private RoleEnum role;
}
