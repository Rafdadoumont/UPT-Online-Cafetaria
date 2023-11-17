package upt.cafetaria.backend.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import upt.cafetaria.backend.exceptions.ServiceException;
import upt.cafetaria.backend.model.domain.User;
import upt.cafetaria.backend.model.web.UserDto;
import upt.cafetaria.backend.service.UserService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/user")
public class UserController {
    @Autowired
    private final UserService userService;

    public UserController(UserService userService) { this.userService = userService; }

    @GetMapping("/all")
    List<User> allUsers() { return userService.getUsers(); }

    @PostMapping("/add")
    User addUser(@Valid @RequestBody UserDto userDto) {
        return userService.addUser(userDto);
    }

    @PutMapping("/update/{id}")
    User updateUser(@Valid @RequestBody UserDto newUser, @PathVariable Long id) {
        User updatedUser = userService.getUser(id);
        userService.updateUser(id, newUser);
        return updatedUser;
    }

    @DeleteMapping("/delete/{id}")
    public User deleteUser(@PathVariable Long id) {
        User deletedUser = userService.getUser(id);
        userService.deleteUser(id);
        return deletedUser;
    }
}