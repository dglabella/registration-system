package ar.edu.unsl.fmn.gida.apis.registration.security.token;

public interface TokenGenerator {

    /**
     * generates a token.
     * 
     * @param userAccountName the user account name used for the token generation process
     * @param userEmail the user email used for the token generation process
     * @return the token
     */
    String generate(String userAccountName, String userEmail);

}
