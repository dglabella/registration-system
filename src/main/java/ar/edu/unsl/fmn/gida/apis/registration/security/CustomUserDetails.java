package ar.edu.unsl.fmn.gida.apis.registration.security;

import java.util.Collection;
import java.util.Collections;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import ar.edu.unsl.fmn.gida.apis.registration.model.User;

public class CustomUserDetails implements UserDetails {

    private final User user;

    public CustomUserDetails(User user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // System.out.println("CustomUserDetails - getAuthorities");
        // System.out.println();
        // return new ArrayList<Privilege>(Arrays.asList(this.user.getPrivileges()));
        return Collections.emptyList();
    }

    @Override
    public String getPassword() {
        // System.out.println("CustomUserDetails - getPassword");
        // System.out.println();
        return this.user.getPassword();
    }

    @Override
    public String getUsername() {
        // System.out.println("CustomUserDetails - getUsername");
        // System.out.println();
        return this.user.getAccount();
    }

    @Override
    public boolean isAccountNonExpired() {
        // System.out.println("CustomUserDetails - isAccountNonExpired");
        // System.out.println();
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        // System.out.println("CustomUserDetails - isAccountNonLocked");
        // System.out.println();
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        // System.out.println("CustomUserDetails - isCredentialsNonExpired");
        // System.out.println();
        return true;
    }

    @Override
    public boolean isEnabled() {
        // System.out.println("CustomUserDetails - isEnabled");
        // System.out.println();
        return true;
    }

    public String getEmail() {
        // System.out.println("CustomUserDetails - getEmail");
        // System.out.println();
        return this.user.getEmail();
    }
}
