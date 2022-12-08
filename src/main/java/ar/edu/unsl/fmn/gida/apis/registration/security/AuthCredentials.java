package ar.edu.unsl.fmn.gida.apis.registration.security;

public class AuthCredentials {
    private String account;
    private String password;

    public AuthCredentials() {}

    public String getAccount() {
        return this.account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
