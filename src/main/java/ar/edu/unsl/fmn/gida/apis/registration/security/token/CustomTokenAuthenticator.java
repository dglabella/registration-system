package ar.edu.unsl.fmn.gida.apis.registration.security.token;

import java.util.Collections;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import ar.edu.unsl.fmn.gida.apis.registration.exceptions.ErrorResponse;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;

public class CustomTokenAuthenticator implements TokenAuthenticator {

    @Override
    public UsernamePasswordAuthenticationToken authenticate(String token) {
        System.out.println("CustomTokenAuthenticator - authenticate");
        System.out.println();
        UsernamePasswordAuthenticationToken ret = null;
        try {
            Claims claims = Jwts.parserBuilder().setSigningKey(TokenProperties.KEY.getBytes())
                    .build().parseClaimsJws(token).getBody();

            String userAccountName = claims.getSubject();

            ret = new UsernamePasswordAuthenticationToken(userAccountName, null,
                    Collections.emptyList());
        } catch (JwtException exception) {
            exception.printStackTrace();
            throw new ErrorResponse("The tokes is invalid: maybe is expired or is wrong",
                    HttpStatus.FORBIDDEN);
        }
        return ret;
    }
}
