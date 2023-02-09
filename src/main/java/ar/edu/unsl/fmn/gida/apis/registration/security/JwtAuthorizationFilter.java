package ar.edu.unsl.fmn.gida.apis.registration.security;

import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import ar.edu.unsl.fmn.gida.apis.registration.security.token.CustomTokenAuthenticator;

@Component
public class JwtAuthorizationFilter extends OncePerRequestFilter {

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
			FilterChain filterChain) throws ServletException, IOException {
		// System.out.println("JwtAuthorizationFilter - doFilterInternal");
		// System.out.println();

		String bearerToken = request.getHeader("Authorization");

		if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
			// System.out.println("bearerToken is OK");
			// System.out.println();

			String token = bearerToken.replace("Bearer ", "");
			SecurityContextHolder.getContext()
					.setAuthentication(new CustomTokenAuthenticator().authenticate(token));
		}

		filterChain.doFilter(request, response);
	}
}
