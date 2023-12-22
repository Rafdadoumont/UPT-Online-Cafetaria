package upt.cafetaria.backend.model.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import upt.cafetaria.backend.model.enums.RoleEnum;
import java.time.Instant;
import java.util.Collection;
import java.util.List;
import upt.cafetaria.backend.model.Cafetaria;

/**
 * Defining this class as an entity to map it to database.
 * Automatically builds getters, setters, toString, hashCode, equals.
 * Automatically generate parameterless constructor.
 * Automatically generate constructor with no arguments.
 * Automatically generate constructor with all arguments.
 * Automatically generates builder pattern.
 * Specifies name of the table in the database.
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "`USER`")
public class User implements UserDetails {

    /**
     *Create new field "userId" that is used as primary key and automatically assign unique value.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    private String lastName;

    private String firstName;

    private String email;

    private String password;

    private Instant created;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "cafetaria_id")
    private Cafetaria cafetaria;

    /**
     * Create field "role" and specify that RoleEnum is stored as string.
     */
    @Enumerated(EnumType.STRING)
    private RoleEnum role;

    /**
     * Implements method to retrieve roles of users.
     * @return list of roles
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.toString()));
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
