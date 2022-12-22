package ar.edu.unsl.fmn.gida.apis.registration.security.token;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import ar.edu.unsl.fmn.gida.apis.registration.exceptions.ErrorResponse;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;

public class CustomTokenAuthenticator implements TokenAuthenticator {

        @Override
        public UsernamePasswordAuthenticationToken authenticate(String token) {
                // System.out.println("CustomTokenAuthenticator - authenticate");
                // System.out.println();

                UsernamePasswordAuthenticationToken ret;

                Claims claims;
                try {
                        claims = Jwts.parserBuilder().setSigningKey(TokenProperties.KEY.getBytes())
                                        .build().parseClaimsJws(token).getBody();

                        String userAccountName = claims.getSubject();
                        String userEmail = (String) claims.get(TokenProperties.EMAIL_CLAIM_KEY);

                        ArrayList<LinkedHashMap<String, String>> authorities = (ArrayList) claims
                                        .get(TokenProperties.PRIVILEGES_CLAIM_KEY);
                        LinkedHashMap<String, String> authoritiesMap = authorities.get(0);
                        String authority = authoritiesMap.get(TokenProperties.PRIVILEGE_CLAIM_KEY);

                        // System.out.println("CLASS = " + authorities.get(0).getClass());
                        // System.out.println("VER = " + authorities.get(0));

                        // System.out.println("CLASS = "
                        // + authoritiesMap.get(TokenProperties.PRIVILEGE_CLAIM_KEY).getClass());
                        // System.out.println("VER = " +
                        // authoritiesMap.get(TokenProperties.PRIVILEGE_CLAIM_KEY));

                        ret = new UsernamePasswordAuthenticationToken(userAccountName, null,
                                        Arrays.asList(new SimpleGrantedAuthority(authority)));

                } catch (JwtException exception) {
                        // exception.printStackTrace();
                        throw new ErrorResponse(
                                        "The tokes is invalid: maybe is expired or is wrong",
                                        HttpStatus.FORBIDDEN);
                }

                return ret;
        }
}
