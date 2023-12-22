package upt.cafetaria.backend.model.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import upt.cafetaria.backend.model.enums.TokenTypeEnum;

/**
 * Automatically builds getters, setters, toString, hashCode, equals.
 * Automatically generates builder pattern.
 * Automatically generate parameterless constructor.
 * Automatically generate constructor with no arguments.
 * Defining this class as an entity to map it to database.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Token {
    /**
     * Create field "tokenId" that is used as a primary key and automatically assign unique value.
     * @author Jan Wieprow
     */
    @Id
    @GeneratedValue
    public Integer tokenId;

    /**
     * Create field "token" informing JPA that token in database should have unique value.
     * @author Jan Wieprow
     */
    @Column(unique = true)
    public String token;

    /**
     * Create field "tokenType" and specify that TokenTypeEnum is store as string.
     * @author Jan Wieprow
     */
    @Enumerated(EnumType.STRING)
    public TokenTypeEnum tokenType;

    public boolean revoked;

    public boolean expired;

    /**
     * Creating many-to-one relation with user.
     * @author Jan Wieprow
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId")
    public User user;
}
