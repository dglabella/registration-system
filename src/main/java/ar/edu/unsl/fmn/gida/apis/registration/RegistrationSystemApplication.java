package ar.edu.unsl.fmn.gida.apis.registration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import ar.edu.unsl.fmn.gida.apis.registration.endpoints.Endpoint;
import ar.edu.unsl.fmn.gida.apis.registration.exceptions.InterpretationException;
import ar.edu.unsl.fmn.gida.apis.registration.utils.datainterpreters.ConfigFileInterpreter;

@SpringBootApplication
public class RegistrationSystemApplication {
	public static void main(String[] args) {
		SpringApplication.run(RegistrationSystemApplication.class, args);

		// ConfigFileInterpreter configFileInterpreter = new ConfigFileInterpreter(
		// System.getProperty("user.dir") + "\\src\\main\\resources\\configs.CONF");
		// try {
		// configFileInterpreter.interpret();
		// } catch (FileInterpreterException e) {
		// e.printStackTrace();
		// }
	}

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {

				registry.addMapping("/" + Endpoint.dependencies)
						.allowedOrigins("http://localhost:3000");
				registry.addMapping("/" + Endpoint.users).allowedOrigins("http://localhost:3000");
				registry.addMapping("/" + Endpoint.persons).allowedOrigins("http://localhost:3000");
				// .allowedMethods("GET", "POST", "PUT", "DELETE")
				// .maxAge(3600);
			}
		};
	}
}
