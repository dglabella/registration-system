package ar.edu.unsl.fmn.gida.apis.registration.security.token;

import java.util.Collections;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;

public class CustomTokenAuthenticator implements TokenAuthenticator {

    @Override
    public UsernamePasswordAuthenticationToken authenticate(String token) {
        UsernamePasswordAuthenticationToken ret = null;
        try {
            Claims claims = Jwts.parserBuilder().setSigningKey(TokenProperties.KEY.getBytes())
                    .build().parseClaimsJws(token).getBody();

            String userAccountName = claims.getSubject();
            ret = new UsernamePasswordAuthenticationToken(userAccountName, null,
                    Collections.emptyList());
        } catch (JwtException exception) {
            exception.printStackTrace();
        }
        return ret;
    }
}
