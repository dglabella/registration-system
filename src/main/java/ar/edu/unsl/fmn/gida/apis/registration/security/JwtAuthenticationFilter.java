package ar.edu.unsl.fmn.gida.apis.registration.security;

import java.io.IOException;
import java.util.Collections;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import com.fasterxml.jackson.databind.ObjectMapper;
import ar.edu.unsl.fmn.gida.apis.registration.exceptions.ErrorResponse;
import ar.edu.unsl.fmn.gida.apis.registration.security.token.CustomTokenGenerator;

public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
            HttpServletResponse response) throws AuthenticationException {
        System.out.println("JwtAuthenticationFilter - attemptAuthentication");
        System.out.println();

        AuthCredential authCredentials = new AuthCredential();

        try {
            authCredentials =
                    new ObjectMapper().readValue(request.getReader(), AuthCredential.class);
        } catch (IOException e) {
            e.printStackTrace();
            throw new ErrorResponse("something wrong with the request. Maybe the json sent is bad",
                    HttpStatus.BAD_REQUEST);
        }

        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                new UsernamePasswordAuthenticationToken(authCredentials.getAccount(),
                        authCredentials.getPassword(), Collections.emptyList());

        return getAuthenticationManager().authenticate(usernamePasswordAuthenticationToken);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request,
            HttpServletResponse response, FilterChain chain, Authentication authResult)
            throws IOException, ServletException {
        System.out.println("JwtAuthenticationFilter - successfulAuthentication");
        System.out.println();

        CustomUserDetails userDetails = (CustomUserDetails) authResult.getPrincipal();
        String token = new CustomTokenGenerator().generate(userDetails.getUsername(),
                userDetails.getEmail());

        response.addHeader("Authorization", "Bearer " + token);
        response.getWriter().flush();

        super.successfulAuthentication(request, response, chain, authResult);
    }
}
