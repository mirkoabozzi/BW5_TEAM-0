package BW5_TEAM_1.EPIC.ENERGY.SERVICES.security;

import BW5_TEAM_1.EPIC.ENERGY.SERVICES.entities.User;
import BW5_TEAM_1.EPIC.ENERGY.SERVICES.exceptions.UnauthorizedException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JWTTools {

    @Value("${jwt.secret}")
    private String secret;

    public String createToken(User user) {
        return Jwts.builder().issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24 * 7))
                .subject(String.valueOf(user.getId()))
                .signWith(Keys.hmacShaKeyFor(secret.getBytes()))
                .compact();
    }

    public void verifyToke(String token) {
        try {
            Jwts.parser().verifyWith(Keys.hmacShaKeyFor(token.getBytes())).build().parse(token);
        } catch (Exception ex) {
            throw new UnauthorizedException("PROBLEMS WITH TOKEN, TRY TO LOGIN");
        }
    }

    public String extractIdFromToken(String accessToken) {
        return Jwts.parser().verifyWith(Keys.hmacShaKeyFor(accessToken.getBytes())).build().parseSignedClaims(accessToken).getPayload().getSubject();
    }

}
