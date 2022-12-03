package ar.edu.unsl.fmn.gida.apis.registration.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class WebSecurityConfiguration {

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity,
			AuthenticationManager authenticationManager) throws Exception {

		return httpSecurity.csrf().disable().authorizeRequests().anyRequest().authenticated().and()
				.httpBasic().and().sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().build();

		// http.authorizeHttpRequests((requests) -> requests.antMatchers("/", "/home").permitAll()
		// .anyRequest().authenticated())
		// .formLogin((form) -> form.loginPage("/login").permitAll())
		// .logout((logout) -> logout.permitAll());
	}

	@Bean
	public UserDetailsService userDetailsService() {
		InMemoryUserDetailsManager userDetailsManager = new InMemoryUserDetailsManager();
		userDetailsManager.createUser(User.withUsername("commonUser")
				.password(this.passwordEncoder().encode("123456789")).roles().build());// roles:
		// "USER"
		return userDetailsManager;
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public AuthenticationManager authenticationManager(HttpSecurity httpSecurity) throws Exception {
		return httpSecurity.getSharedObject(AuthenticationManagerBuilder.class)
				.userDetailsService(this.userDetailsService())
				.passwordEncoder(this.passwordEncoder()).and().build();
	}
}
