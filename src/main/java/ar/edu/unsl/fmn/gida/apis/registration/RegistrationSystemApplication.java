package ar.edu.unsl.fmn.gida.apis.registration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class RegistrationSystemApplication {

	public class Endpoints {
		public final static String persons = "persons";
		public final static String registers = "registers";
		public final static String accesses = "accesses";
		public final static String dependencies = "dependencies";
		public final static String weeklies = "weeklies";
		public final static String users = "users";
	}

	public static void main(String[] args) {
		SpringApplication.run(RegistrationSystemApplication.class, args);
	}
	
	@Bean
	public WebMvcConfigurer corsConfigurer() {
	        return new WebMvcConfigurer() {
	                @Override
	                public void addCorsMappings(CorsRegistry registry) {
	                        registry.addMapping("/users").allowedOrigins("http://localhost:3000");
	                                //.allowedMethods("GET", "POST", "PUT", "DELETE")
	                                //.maxAge(3600);
	                }

	        };
	}	
}
