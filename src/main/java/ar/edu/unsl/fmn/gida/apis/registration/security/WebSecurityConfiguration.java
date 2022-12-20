package ar.edu.unsl.fmn.gida.apis.registration.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import ar.edu.unsl.fmn.gida.apis.registration.urls.Urls;

@Configuration
public class WebSecurityConfiguration {

	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	private JwtAuthorizationFilter jwtAuthorizationFilter;

	// @Autowired
	// private PasswordEncoder passwordEncoder;

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity,
			AuthenticationManager authManager) throws Exception {

		System.out.println("WebSecurityConfiguration - securityFilterChain");
		System.out.println();

		JwtAuthenticationFilter jwtAuthenticationFilter = new JwtAuthenticationFilter();
		jwtAuthenticationFilter.setAuthenticationManager(authManager);
		jwtAuthenticationFilter
				.setFilterProcessesUrl("/" + Urls.Privileges.pub + Urls.authentication);

		// return httpSecurity.csrf().disable().authorizeRequests()
		// .antMatchers("/" + Urls.Privileges.pub + "/**").permitAll().and()
		// .authorizeRequests().antMatchers("/" + Urls.Privileges.user + "/**")
		// .hasRole(Privilege.USER.name()).and().authorizeRequests()
		// .antMatchers("/" + Urls.Privileges.responsible + "/**")
		// .hasRole(Privilege.RESPONSIBLE.name()).and().authorizeRequests()
		// .antMatchers("/" + Urls.Privileges.admin + "/**").hasRole(Privilege.ADMIN.name())
		// .and().authorizeRequests().anyRequest().authenticated().and().sessionManagement()
		// .sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
		// .addFilter(jwtAuthenticationFilter)
		// .addFilterBefore(jwtAuthorizationFilter, UsernamePasswordAuthenticationFilter.class)
		// .build();
		return httpSecurity.csrf().disable().authorizeRequests().anyRequest().authenticated().and()
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
				.addFilter(jwtAuthenticationFilter)
				.addFilterBefore(jwtAuthorizationFilter, UsernamePasswordAuthenticationFilter.class)
				.build();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		System.out.println("WebSecurityConfiguration - passwordEncoder");
		System.out.println();
		return new BCryptPasswordEncoder();
	}

	@Bean
	public AuthenticationManager authManager(HttpSecurity httpSecurity) throws Exception {
		System.out.println("WebSecurityConfiguration - authManager");
		System.out.println();
		return httpSecurity.getSharedObject(AuthenticationManagerBuilder.class)
				.userDetailsService(this.userDetailsService).passwordEncoder(this.passwordEncoder())
				.and().build();
	}
}
