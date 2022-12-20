package ar.edu.unsl.fmn.gida.apis.registration.security.token;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

public class CustomTokenGenerator implements TokenGenerator {

    @Override
    public String generate(String userAccountName, String userEmail) {

        long expirationTimeMillis = TokenProperties.EXPIRATION_TIME_SECONDS * 1000;
        Date expirationDate = new Date(System.currentTimeMillis() + expirationTimeMillis);

        Map<String, Object> extraData = new HashMap<>();
        extraData.put(TokenProperties.EMAIL_CLAIM_KEY, userEmail);

        return Jwts.builder().setSubject(userAccountName).setExpiration(expirationDate)
                .addClaims(extraData).signWith(Keys.hmacShaKeyFor(TokenProperties.KEY.getBytes()))
                .compact();
    }
}
