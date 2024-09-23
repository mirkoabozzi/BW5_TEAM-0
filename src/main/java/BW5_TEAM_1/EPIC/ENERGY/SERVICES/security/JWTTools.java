package BW5_TEAM_1.EPIC.ENERGY.SERVICES.security;

import BW5_TEAM_1.EPIC.ENERGY.SERVICES.entities.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;

import java.util.Date;

public class JWTTools {

    @Value("${jwt_secret}")
    private String secret;

    public String createToke(User user){
        return Jwts.builder().issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24 * 7))
                .subject(String.valueOf(user.getId()))
                .signWith(Keys.hmacShaKeyFor(secret.getBytes()))
                .compact();
    }

    public void verifyToke(String token){
        try {
            Jwts.parser().verifyWith(Keys.hmacShaKeyFor(token.getBytes())).build().parse(token);
        } catch (Exception ex){
            thro
        }
    }

}
