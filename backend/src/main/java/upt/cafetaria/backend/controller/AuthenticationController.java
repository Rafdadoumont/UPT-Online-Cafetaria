package upt.cafetaria.backend.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import upt.cafetaria.backend.model.web.*;
import upt.cafetaria.backend.service.AuthenticationService;

/**
 * Handles authentication-related operations for users.
 * Provides endpoints for user registration, login, token refresh, and token validation.
 *
 * Endpoints:
 * - /register: POST request for user registration.
 * - /login: POST request for user login authentication.
 * - /refresh-token: POST request to refresh user access tokens.
 * - /validate: GET request to validate user access tokens.
 * @author Rúben Santos
 */
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(
            @Valid @RequestBody RegisterRequest request
    ) {
        return ResponseEntity.ok(authenticationService.register(request));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @Valid @RequestBody LoginRequest request
    ) {
        AuthenticationResponse response = authenticationService.authenticate(request);

        return ResponseEntity.ok(response);
    }

    /**
     * Refreshes user access tokens.
     * Happens when the access token expires.
     * @param refreshTokenRequest the refresh token request.
     * @return the authentication response.
     * @author Rainier Bastiaans
     */
    @PostMapping("/refresh-token")
    public ResponseEntity<AuthenticationResponse> refreshTokens(@Valid @RequestBody RefreshTokenRequest refreshTokenRequest) {
        return ResponseEntity.ok(authenticationService.refreshToken(refreshTokenRequest));
    }

    @GetMapping("/validate")
    public ResponseEntity<AuthoritiesResponse> validate(@RequestHeader("Authorization") String token) {
        return ResponseEntity.ok(authenticationService.validateAccessToken(token));
    }
}