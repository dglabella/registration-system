package ar.edu.unsl.fmn.gida.apis.registration.security;

public class AuthCredential {
    private String account;
    private String password;

    public AuthCredential() {}

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
