package ar.edu.unsl.fmn.gida.apis.registration;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import ar.edu.unsl.fmn.gida.apis.registration.external.configs.Configuration;
import ar.edu.unsl.fmn.gida.apis.registration.messengers.Messenger;
import ar.edu.unsl.fmn.gida.apis.registration.model.Register;
import ar.edu.unsl.fmn.gida.apis.registration.model.Responsibility;
import ar.edu.unsl.fmn.gida.apis.registration.model.Weekly;
import ar.edu.unsl.fmn.gida.apis.registration.services.WeeklyService;
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
		File configFile =
				new File(System.getProperty("user.dir") + "\\src\\main\\resources\\configs.CONF");
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

	private static void workAttendanceTest() {
		List<Responsibility> dateResponsibilities = new ArrayList<>();
		List<Register> dateRegisters = new ArrayList<>();

		Responsibility r1 = new Responsibility();
		r1.setId(1);
		r1.setDay(DayOfWeek.FRIDAY);
		r1.setEntranceTime(LocalTime.of(8, 0));
		r1.setDepartureTime(LocalTime.of(10, 0));
		dateResponsibilities.add(r1);

		Responsibility r2 = new Responsibility();
		r2.setId(45);
		r2.setDay(DayOfWeek.FRIDAY);
		r2.setEntranceTime(LocalTime.of(11, 0));
		r2.setDepartureTime(LocalTime.of(13, 0));
		dateResponsibilities.add(r2);

		Responsibility r3 = new Responsibility();
		r3.setId(23);
		r3.setDay(DayOfWeek.FRIDAY);
		r3.setEntranceTime(LocalTime.of(16, 0));
		r3.setDepartureTime(LocalTime.of(18, 0));
		dateResponsibilities.add(r3);


		Register reg1 = new Register();
		reg1.setId(5456);
		reg1.setTime(LocalDateTime.of(2023, 4, 14, 8, 0, 45));
		dateRegisters.add(reg1);

		Register reg2 = new Register();
		reg2.setId(5563);
		reg2.setTime(LocalDateTime.of(2023, 4, 14, 10, 20, 0));
		dateRegisters.add(reg2);

		Register reg5 = new Register();
		reg5.setId(6998);
		reg5.setTime(LocalDateTime.of(2023, 4, 14, 10, 48, 45));
		dateRegisters.add(reg5);

		Register reg6 = new Register();
		reg6.setId(6682);
		reg6.setTime(LocalDateTime.of(2023, 4, 14, 12, 59, 0));
		dateRegisters.add(reg6);

		Register reg3 = new Register();
		reg3.setId(5595);
		reg3.setTime(LocalDateTime.of(2023, 4, 14, 16, 16, 26));
		dateRegisters.add(reg3);

		Register reg4 = new Register();
		reg4.setId(5785);
		reg4.setTime(LocalDateTime.of(2023, 4, 14, 18, 16, 26));
		dateRegisters.add(reg4);

		// System.out.println("hay al menos una responsabilidad finalizada? "
		// + WeeklyService.isFulfilledAtLeastOneResponsibilityCRIS(dateResponsibilities,
		// dateRegisters, 1800));
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
