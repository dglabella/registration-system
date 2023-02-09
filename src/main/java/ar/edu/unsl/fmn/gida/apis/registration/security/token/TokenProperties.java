package ar.edu.unsl.fmn.gida.apis.registration.security.token;

public interface TokenProperties {
	String KEY = "(H+KbPeShVmYq3t6w9z$C&F)J@NcQfTj";

	// long EXPIRATION_TIME_SECONDS = 86400; //24 hs
	long EXPIRATION_TIME_SECONDS = 28800; // 8 hs

	String IDENTIFIER_KEY = "id";
	String EMAIL_CLAIM_KEY = "email";
	String PRIVILEGES_CLAIM_KEY = "authorities";
	String PRIVILEGE_CLAIM_KEY = "authority";
}
