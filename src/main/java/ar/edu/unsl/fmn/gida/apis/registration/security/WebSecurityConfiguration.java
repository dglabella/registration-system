package ar.edu.unsl.fmn.gida.apis.registration.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import ar.edu.unsl.fmn.gida.apis.registration.enums.Privilege;
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

		// System.out.println("WebSecurityConfiguration - securityFilterChain");
		// System.out.println();

		JwtAuthenticationFilter jwtAuthenticationFilter = new JwtAuthenticationFilter();
		jwtAuthenticationFilter.setAuthenticationManager(authManager);
		jwtAuthenticationFilter
				.setFilterProcessesUrl("/" + Urls.Privileges.pub + Urls.authentication);

		return httpSecurity.csrf().disable().cors(Customizer.withDefaults()).authorizeRequests()
				.antMatchers("/" + Urls.Privileges.pub + "/**").permitAll()
				.antMatchers("/" + Urls.Privileges.user + "/**")
				.hasAnyAuthority(Privilege.ROLE_USER.name(), Privilege.ROLE_RESPONSIBLE.name(),
						Privilege.ROLE_ADMIN.name())
				.antMatchers("/" + Urls.Privileges.responsible + "/**")
				.hasAnyAuthority(Privilege.ROLE_RESPONSIBLE.name(), Privilege.ROLE_ADMIN.name())
				.antMatchers("/" + Urls.Privileges.admin + "/**")
				.hasAuthority(Privilege.ROLE_ADMIN.name()).anyRequest().authenticated().and()
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
				.addFilter(jwtAuthenticationFilter)
				.addFilterBefore(jwtAuthorizationFilter, UsernamePasswordAuthenticationFilter.class)
				.build();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		// System.out.println("WebSecurityConfiguration - passwordEncoder");
		// System.out.println();
		return new BCryptPasswordEncoder();
	}

	@Bean
	public AuthenticationManager authManager(HttpSecurity httpSecurity) throws Exception {
		// System.out.println("WebSecurityConfiguration - authManager");
		// System.out.println();
		return httpSecurity.getSharedObject(AuthenticationManagerBuilder.class)
				.userDetailsService(this.userDetailsService).passwordEncoder(this.passwordEncoder())
				.and().build();
	}

	@Bean
	public CorsConfigurationSource corsConfigurationSource() {
		final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		CorsConfiguration config = new CorsConfiguration();
		config.addAllowedHeader("*");
		config.addAllowedMethod("*");
		config.addAllowedOrigin("http://localhost:3000");
		source.registerCorsConfiguration("/**", config);
		return source;
	}
}
