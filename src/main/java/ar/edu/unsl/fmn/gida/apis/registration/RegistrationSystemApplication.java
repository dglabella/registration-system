package ar.edu.unsl.fmn.gida.apis.registration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class RegistrationSystemApplication {
	public static void main(String[] args) {
		SpringApplication.run(RegistrationSystemApplication.class, args);
	}

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
					registry.addMapping("/dependencies").allowedOrigins("http://localhost:3000");
					registry.addMapping("/users").allowedOrigins("http://localhost:3000");
					registry.addMapping("/persons").allowedOrigins("http://localhost:3000");
							//.allowedMethods("GET", "POST", "PUT", "DELETE")
							//.maxAge(3600);
			}

	};
	}
}
