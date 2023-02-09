package ar.edu.unsl.fmn.gida.apis.registration.security.token;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public interface TokenAuthenticator {

	/**
	 * authenticates a token
	 * 
	 * @param token the token to be authenticated
	 * @return the object with authenticated data. returns null if the token is expired or invalid
	 */
	UsernamePasswordAuthenticationToken authenticate(String token);
}
