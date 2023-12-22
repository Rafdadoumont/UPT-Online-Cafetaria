package upt.cafetaria.backend.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import upt.cafetaria.backend.model.domain.User;
import upt.cafetaria.backend.model.enums.RoleEnum;
import upt.cafetaria.backend.repository.UserRepository;
import upt.cafetaria.backend.security.JwtAuthenticationFilter;
import upt.cafetaria.backend.service.JwtService;
import java.time.Instant;
import java.util.List;
import static org.springframework.security.web.util.matcher.AntPathRequestMatcher.antMatcher;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private final UserRepository userRepository;
    private final JwtService jwtService;

    /**
     * This bean is used by Spring Security to load user-specific data.
     * @exception AccessDeniedException if user does not exist.
     * @return UserDetailsService
     * @author Rainier Bastiaans
     */
    @Bean
    public UserDetailsService userDetailsService() {
        return email -> userRepository.findByEmail(email)
                .orElseThrow(() -> new AccessDeniedException("User with email " + email + " does not exist." ));
    }

    /**
     * This bean is used by Spring Security to set an encoder for passwords.
     * BCrypt is a password hashing function
     * @return PasswordEncoder
     * @author Rainier Bastiaans
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

    /**
     * This bean is used by Spring Security to allow access to the H2 database console.
     * @return WebSecurityCustomizer
     * @author Rainier Bastiaans
     */
    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring()
                .requestMatchers(antMatcher("/h2/**"));
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
                .cors(Customizer.withDefaults())
                .addFilterBefore(new JwtAuthenticationFilter(jwtService, userDetailsService()), UsernamePasswordAuthenticationFilter.class)
                .authenticationProvider(authenticationProvider())
                .authorizeHttpRequests((auth) -> auth
                        .requestMatchers(antMatcher("/api/auth/**")).permitAll()
                        .requestMatchers(antMatcher("/swagger-ui/**")).permitAll()
                        .requestMatchers(antMatcher("/v3/api-docs/**")).permitAll()
                        .requestMatchers(antMatcher("/swagger-resources")).permitAll()
                        .anyRequest().authenticated());
        return http.build();
    }

    /**
     * This bean is used to disable CORS.
     * @return CorsConfigurationSource
     * @author Rainier Bastiaans
     */
    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(List.of("*"));
        configuration.setAllowedMethods(List.of("*"));
        configuration.setAllowedHeaders(List.of("*"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    /**
     * This bean is used to create default users.
     * @author Rainier Bastiaans
     */
    @Bean
    public void defaultUsers() {
        User user = User.builder()
                .firstName("user")
                .lastName("user")
                .email("user@mail.com")
                .password(passwordEncoder().encode("password"))
                .role(RoleEnum.USER)
                .created(Instant.now())
                .build();
        if (userRepository.findByEmail(user.getEmail()).isEmpty()) {
            userRepository.save(user);
        }

        User employee = User.builder()
                .firstName("employee")
                .lastName("employee")
                .email("employee@mail.com")
                .password(passwordEncoder().encode("password"))
                .role(RoleEnum.EMPLOYEE)
                .created(Instant.now())
                .build();
        if (userRepository.findByEmail(employee.getEmail()).isEmpty()) {
            userRepository.save(employee);
        }

        User admin = User.builder()
                .firstName("admin")
                .lastName("admin")
                .email("admin@mail.com")
                .password(passwordEncoder().encode("password"))
                .role(RoleEnum.ADMIN)
                .created(Instant.now())
                .build();
        if (userRepository.findByEmail(admin.getEmail()).isEmpty()) {
            userRepository.save(admin);
        }
    }
}