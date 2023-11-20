package upt.cafetaria.backend.model.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import upt.cafetaria.backend.model.enums.TokenTypeEnum;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Token {
    @Id
    @GeneratedValue
    public Integer tokenId;

    @Column(unique = true)
    public String token;

    @Enumerated(EnumType.STRING)
    public TokenTypeEnum tokenType;

    public boolean revoked;

    public boolean expired;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId")
    public User user;
}
