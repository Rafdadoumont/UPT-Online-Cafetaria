package upt.cafetaria.backend.model.web;

import jakarta.validation.constraints.NotEmpty;

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

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
