package ar.edu.unsl.fmn.gida.apis.registration;

import java.io.File;
import java.io.FileOutputStream;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import ar.edu.unsl.fmn.gida.apis.registration.endpoints.Endpoint;
import ar.edu.unsl.fmn.gida.apis.registration.exceptions.ConvertionException;
import ar.edu.unsl.fmn.gida.apis.registration.utils.cypher.CustomCypher;
import ar.edu.unsl.fmn.gida.apis.registration.utils.cypher.Cypher;
import ar.edu.unsl.fmn.gida.apis.registration.utils.data.interpreters.ConfigFileConverter;
import ar.edu.unsl.fmn.gida.apis.registration.utils.qr.CustomQRGenerator;

@SpringBootApplication
public class RegistrationSystemApplication {

	private void createQr() {
		Cypher cypher = new CustomCypher();
		CustomQRGenerator qrGenerator = new CustomQRGenerator();
		File file = new File(System.getProperty("user.dir") + "/qrs/" + "matthewQr" + ".png");
		try {
			FileOutputStream fileOutputStream = new FileOutputStream(file);
			fileOutputStream.write(qrGenerator
					.generate(cypher.encrypt("7243-32775983-BRINGHAMM-MATTHEW"), 350, 350));
			fileOutputStream.flush();
			fileOutputStream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

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

	/*@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {

				registry.addMapping("/" + Endpoint.dependencies).allowedOrigins("http://localhost:3000");
				registry.addMapping("/" + Endpoint.users).allowedOrigins("http://localhost:3000");
				registry.addMapping("/" + Endpoint.persons).allowedOrigins("http://localhost:3000");
				registry.addMapping("/" + Endpoint.persons + "/{*}").allowedOrigins("http://localhost:3000");
<<<<<<< Updated upstream
				registry.addMapping("/" + Endpoint.persons + "/paged").allowedOrigins("http://localhost:3000");
=======
>>>>>>> Stashed changes
				// .allowedMethods("GET", "POST", "PUT", "DELETE")
				// .maxAge(3600);
			}
		};
	}*/
}
