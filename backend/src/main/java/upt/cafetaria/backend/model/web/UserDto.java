package upt.cafetaria.backend.model.web;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

/**
 * Defining class UserDto that connect backend and frontend.
 * Automatically creating getters and setters.
 * @author Jan Wieprow
 */
@Getter
@Setter
public class UserDto {
    private long id;

    @NotEmpty(message = "user.lastname.empty")
    private String lastName;

    @NotEmpty(message = "user.firstname.empty")
    private String firstName;

    @NotEmpty(message = "user.email.empty")
    private String email;

    @NotEmpty(message = "user.password.empty")
    private String password;
}
