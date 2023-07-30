package ar.edu.unsl.fmn.gida.apis.registration;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import ar.edu.unsl.fmn.gida.apis.registration.external.configs.Configuration;
import ar.edu.unsl.fmn.gida.apis.registration.messengers.Messenger;
import ar.edu.unsl.fmn.gida.apis.registration.utils.cypher.QrCypher;
import ar.edu.unsl.fmn.gida.apis.registration.utils.cypher.Cypher;
import ar.edu.unsl.fmn.gida.apis.registration.utils.data.interpreters.ConfigurationConverter;
import ar.edu.unsl.fmn.gida.apis.registration.utils.qr.CustomQRGenerator;

@SpringBootApplication
public class RegistrationSystemApplication {

	public static Map<String, String> CONFIGURATION;
	public static Messenger MESSENGER;

	private void createQr() {
		Cypher cypher = new QrCypher();
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

	private static void setUpConfigFile() {
		ConfigurationConverter configurationConverter = new ConfigurationConverter();
		File configFile = new File(System.getProperty("user.dir") + "\\classes\\configs.CONF");
		try {
			if (!configFile.exists()) {
				System.out.println("no config file found, restoring with default values...");
				configFile.createNewFile();
				configFile.setWritable(true);

				// Creating config object
				Map<String, String> configuration = new HashMap<>();
				// add config 1
				configuration.put(Configuration.NAME_LOG_FILE_DIR,
						Configuration.DEFAULT_VALUE_LOG_FILE_DIR);
				// add config 2
				configuration.put(Configuration.NAME_LANG, Configuration.DEFAULT_VALUE_LANG);
				// add future default configs here
				// ...

				// write into file
				OutputStream outputStream = new FileOutputStream(configFile);
				outputStream.write(configurationConverter.stringify(configuration).getBytes());
				outputStream.close();
			}

			DataInputStream dataInputStream = new DataInputStream(new FileInputStream(configFile));
			int nBytesToRead = dataInputStream.available();
			if (nBytesToRead > 0) {
				byte[] bytes = new byte[nBytesToRead];
				dataInputStream.read(bytes);
				CONFIGURATION = configurationConverter.objectify(new String(bytes));
			} else {
				System.out.println(
						"something is wrong reading the config file, default in memory option will be used");

				// Creating config object
				CONFIGURATION = new HashMap<>();

				// add config 1
				CONFIGURATION.put(Configuration.NAME_LOG_FILE_DIR,
						Configuration.DEFAULT_VALUE_LOG_FILE_DIR);

				// add config 2
				CONFIGURATION.put(Configuration.NAME_LANG, Configuration.DEFAULT_VALUE_LANG);

				// add future default in memory configs here
				// ...
			}
			dataInputStream.close();
		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}

	private static void setUpMessagesLang() {
		MESSENGER = new Messenger(CONFIGURATION.get(Configuration.NAME_LANG));
	}

	public static void main(String[] args) {
		// System.out.println("a ver el pass: " + new BCryptPasswordEncoder().encode("789789789"));
		setUpConfigFile();
		setUpMessagesLang();
		SpringApplication.run(RegistrationSystemApplication.class, args);
	}

	/*
	 * @Bean public WebMvcConfigurer corsConfigurer() { return new WebMvcConfigurer() {
	 * 
	 * @Override public void addCorsMappings(CorsRegistry registry) {
	 * 
	 * registry.addMapping("/" + Endpoint.dependencies).allowedOrigins("http://localhost:3000");
	 * registry.addMapping("/" + Endpoint.users).allowedOrigins("http://localhost:3000");
	 * registry.addMapping("/" + Endpoint.persons).allowedOrigins("http://localhost:3000");
	 * registry.addMapping("/" + Endpoint.persons + "/{*}").allowedOrigins("http://localhost:3000");
	 * // .allowedMethods("GET", "POST", "PUT", "DELETE") // .maxAge(3600); } }; }
	 */
}
