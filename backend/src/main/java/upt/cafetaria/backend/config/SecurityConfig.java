package upt.cafetaria.backend.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@Configuration
public class SecurityConfig {

    @Bean
    public InMemoryUserDetailsManager inMemoryUserDetailsManager() {
        UserDetails user = User.withDefaultPasswordEncoder()
                .username("user")
                .password("t")
                .roles("USER")
                .build();
        UserDetails admin = User.withDefaultPasswordEncoder()
                .username("admin")
                .password("t")
                .roles("USER", "ADMIN")
                .build();
        return new InMemoryUserDetailsManager(user, admin);
    }

/*    @Autowired
    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {
        return http
                .csrf().disable()
                .authorizeRequests()
                .requestMatchers("/home").permitAll()
                .requestMatchers("/login").permitAll()
                .requestMatchers("/team/overview/**").hasRole("USER")
                .requestMatchers("/regatta/overview/**").hasRole("USER")
                .requestMatchers("/storage/overview/**").hasRole("USER")
                .requestMatchers("/boat/overview/**").hasRole("USER")
                .requestMatchers("/team/add/**").hasRole("ADMIN")
                .requestMatchers("/regatta/add/**").hasRole("ADMIN")
                .requestMatchers("/storage/add/**").hasRole("ADMIN")
                .requestMatchers("/boat/add/**").hasRole("ADMIN")
                .requestMatchers("/team/delete/**").hasRole("ADMIN")
                .requestMatchers("/regatta/delete/**").hasRole("ADMIN")
                .requestMatchers("/storage/delete/**").hasRole("ADMIN")
                .requestMatchers("/boat/delete/**").hasRole("ADMIN")
                .requestMatchers("/team/update/**").hasRole("ADMIN")
                .requestMatchers("/regatta/update/**").hasRole("ADMIN")
                .requestMatchers("/storage/update/**").hasRole("USER")
                .requestMatchers("/boat/update/**").hasRole("ADMIN")
                .requestMatchers("/api/**").permitAll()
                // ADDED FOR H2 ACCESS VIA BROWSER
                .requestMatchers("/h2/**").permitAll()
                .and()
                .headers().frameOptions().disable()
                .and()
                .formLogin()
                .loginPage("/login")
                .permitAll()
                .and()
                .logout()
                .permitAll()
                .deleteCookies("JSESSIONID")
                .and()
                .build();
    }*/

/*    @Bean
    public SpringSecurityDialect springSecurityDialect() {
        return new SpringSecurityDialect();
    }*/
}
