package upt.cafetaria.backend.service;

import io.jsonwebtoken.MalformedJwtException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import upt.cafetaria.backend.exceptions.ServiceException;
import upt.cafetaria.backend.model.domain.Token;
import upt.cafetaria.backend.model.enums.RoleEnum;
import upt.cafetaria.backend.model.enums.TokenTypeEnum;
import upt.cafetaria.backend.model.web.*;
import upt.cafetaria.backend.repository.TokenRepository;
import upt.cafetaria.backend.repository.UserRepository;
import upt.cafetaria.backend.model.domain.User;

import java.time.Instant;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository repository;
    private final TokenRepository tokenRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse register(RegisterRequest request) {
        try {
            User user = User.builder()
                    .firstName(request.getFirstname())
                    .lastName(request.getLastname())
                    .email(request.getEmail())
                    .password(passwordEncoder.encode(request.getPassword()))
                    .role(request.getRole())
                    .created(Instant.now())
                    .build();
            User savedUser = repository.save(user);
            String jwtToken = jwtService.generateToken(user);
            String refreshToken = jwtService.generateRefreshToken(user);
            saveToken(savedUser, jwtToken, TokenTypeEnum.ACCESS);
            saveToken(savedUser, refreshToken, TokenTypeEnum.REFRESH);
            return AuthenticationResponse.builder()
                    .accessToken(jwtToken)
                    .refreshToken(refreshToken)
                    .build();
        } catch (Exception e) {
            throw new ServiceException("Register", e.getMessage());
        }
    }

    public AuthenticationResponse authenticate(LoginRequest request) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getEmail(),
                            request.getPassword()
                    )
            );
            User user = repository.findByEmail(request.getEmail()).orElseThrow(() -> new ServiceException("Find By Email", "Email could not be retrieved from the request "));
            String jwtToken = jwtService.generateToken(user);
            String refreshToken = jwtService.generateRefreshToken(user);
            revokeAllAccessTokens(user);
            saveToken(user, jwtToken, TokenTypeEnum.ACCESS);
            return AuthenticationResponse.builder()
                    .accessToken(jwtToken)
                    .refreshToken(refreshToken)
                    .build();
        } catch (Exception e) {
            System.err.println(e.getMessage());
            throw new ServiceException("Authenticate", "Invalid credentials");
        }
    }

    private void saveToken(User user, String jwtToken, TokenTypeEnum tokenType) {
        Token token = Token.builder()
                .user(user)
                .token(jwtToken)
                .tokenType(tokenType)
                .expired(false)
                .revoked(false)
                .build();
        tokenRepository.save(token);
    }

    private void revokeAllAccessTokens(User user) {
        List<Token> validAccessTokens = tokenRepository.findTokensByUserIdAndType(user.getUserId(), TokenTypeEnum.ACCESS);
        if (validAccessTokens.isEmpty())
            return;
        validAccessTokens.forEach(token -> {
            token.setExpired(true);
            token.setRevoked(true);
        });
        tokenRepository.saveAll(validAccessTokens);
    }

    public AuthenticationResponse refreshToken(@Valid @RequestBody RefreshTokenRequest request) {
        try {
            final String refreshToken = request.getRefreshToken();
            final String email = jwtService.extractEmail(refreshToken);
            if (email != null) {
                User user = this.repository.findByEmail(email).orElseThrow(() -> new IllegalArgumentException("Invalid Refresh Token"));
                if (jwtService.isTokenValid(refreshToken, user)) {
                    String accessToken = jwtService.generateToken(user);
                    revokeAllAccessTokens(user);
                    saveToken(user, accessToken, TokenTypeEnum.ACCESS);
                    return AuthenticationResponse.builder()
                            .accessToken(accessToken)
                            .refreshToken(refreshToken)
                            .build();
                } else {
                    throw new MalformedJwtException("Invalid Refresh Token");
                }
            } else {
                throw new MalformedJwtException("Invalid Refresh Token");
            }
        } catch (Exception e) {
            throw new ServiceException("Invalid Refresh Token", e.getMessage());
        }
    }

    public AuthoritiesResponse validateAccessToken(String accessToken) {
        try {
            String token = accessToken.substring(7);
            final String email = jwtService.extractEmail(token);
            if (email != null) {
                User user = this.repository.findByEmail(email).orElseThrow(() -> new IllegalArgumentException("Invalid Access Token"));
                if (jwtService.isTokenValid(token, user)) {
                    return AuthoritiesResponse.builder()
                            .role(user.getRole())
                            .build();
                } else {
                    throw new MalformedJwtException("Invalid Access Token");
                }
            } else {
                throw new MalformedJwtException("Invalid Access Token");
            }
        } catch (Exception e) {
            throw new ServiceException("Invalid Access Token", e.getMessage());
        }
    }

    public String getAccessTokenForTesting() {
        User user = this.repository.findAll().get(0);
        return jwtService.generateToken(user);
    }
}