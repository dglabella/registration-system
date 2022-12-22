package ar.edu.unsl.fmn.gida.apis.registration.security;

import java.io.IOException;
import java.util.Random;
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
import ar.edu.unsl.fmn.gida.apis.registration.model.User;
import ar.edu.unsl.fmn.gida.apis.registration.security.token.CustomTokenGenerator;

public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final int IDENT_PREFIX_QUANT = 5;
    private final int IDENT_POSTFIX_QUANT = 7;

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
            HttpServletResponse response) throws AuthenticationException {
        // System.out.println("JwtAuthenticationFilter - attemptAuthentication");
        // System.out.println();

        User user = new User();

        try {
            user = new ObjectMapper().readValue(request.getReader(), User.class);
        } catch (IOException e) {
            // e.printStackTrace();
            throw new ErrorResponse("something wrong with the request. Maybe the json sent is bad",
                    HttpStatus.BAD_REQUEST);
        }

        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                new UsernamePasswordAuthenticationToken(user.getAccount(), user.getPassword());

        return this.getAuthenticationManager().authenticate(usernamePasswordAuthenticationToken);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request,
            HttpServletResponse response, FilterChain chain, Authentication authResult)
            throws IOException, ServletException {
        // System.out.println("JwtAuthenticationFilter - successfulAuthentication");
        // System.out.println();

        User user = (User) authResult.getPrincipal();
        // System.out.println(
        // "Check privileges - successfulAuthentication " + authResult.getAuthorities());

        Random random = new Random();
        String prefix = "";
        for (int i = 0; i < this.IDENT_PREFIX_QUANT; i++)
            prefix += (random.nextInt(9) + 1); // excluding cero in prefix to avoid string to long
                                               // convertion problem

        String postfix = "";
        for (int i = 0; i < this.IDENT_POSTFIX_QUANT; i++)
            postfix += random.nextInt(10);

        String encodedId = prefix + user.getId() + postfix;

        String token = new CustomTokenGenerator().generate(Long.parseLong(encodedId),
                user.getUsername(), user.getEmail(), user.getAuthorities());

        response.addHeader("Authorization", "Bearer " + token);
        response.getWriter().write(encodedId);
        response.getWriter().flush();

        super.successfulAuthentication(request, response, chain, authResult);
    }
}
