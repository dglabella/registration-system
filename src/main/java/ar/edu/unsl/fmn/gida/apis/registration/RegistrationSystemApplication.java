package ar.edu.unsl.fmn.gida.apis.registration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RegistrationSystemApplication {

	public class Endpoints {
		public final static String persons = "persons";
		public final static String accesses = "accesses";
	}

	public static void main(String[] args) {
		SpringApplication.run(RegistrationSystemApplication.class, args);
	}
}
