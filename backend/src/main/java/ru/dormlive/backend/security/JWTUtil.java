package ru.dormlive.backend.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ru.dormlive.backend.model.User;

import java.time.ZonedDateTime;
import java.util.Date;

@Component
public class JWTUtil {

    @Value("${jwt_secret}")
    private String secret;
    @Value("${token_issuer}")
    private String issuer;
    @Value("${jwt_days_until_expiration}")
    private int expirationDays;

    public String generateToken(User user){
        Date expirationDate = Date.from(ZonedDateTime.now().plusDays(expirationDays).toInstant());

        return JWT.create()
                .withSubject("User details")
                .withClaim("id", user.getId())
                .withClaim("nickname", user.getNickname())
                .withClaim("fio", user.getFio())
                .withClaim("group", user.getGroup())
                .withClaim("dorm", user.getDorm())
                .withIssuer(issuer)
                .withExpiresAt(expirationDate)
                .sign(Algorithm.HMAC256(secret));
    }

    public String validateTokenAndRetrieveClaim(String token) throws JWTVerificationException {
        JWTVerifier verifier = JWT.require(Algorithm.HMAC256(secret))
                .withSubject("User details")
                .withIssuer(issuer)
                .build();
        DecodedJWT jwt = verifier.verify(token);

        return jwt.getClaim("nickname").asString();
    }
}
