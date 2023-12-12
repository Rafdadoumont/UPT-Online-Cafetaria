package upt.cafetaria.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import upt.cafetaria.backend.model.domain.Token;
import upt.cafetaria.backend.model.enums.TokenTypeEnum;

import java.util.List;
import java.util.Optional;

public interface TokenRepository extends JpaRepository<Token, Integer> {

    @Query(value = """
        select t from Token t inner join User u
        on t.user.userId = u.userId
        where u.userId = :id and (t.expired = false or t.revoked = false)
        and t.tokenType = :tokenType
        """)
    List<Token> findTokensByUserIdAndType(@Param("id") Long userId, @Param("tokenType") TokenTypeEnum tokenType);
}
