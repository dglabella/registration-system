package ar.edu.unsl.fmn.gida.apis.registration.model.constraints;

public interface Constraints {

	interface User {
		int NAME_MIN_LENGHT = 1;
		int NAME_MAX_LENGHT = 60;
		boolean NAME_NULLABLE = false;

		int LAST_NAME_MIN_LENGHT = 1;
		int LAST_NAME_MAX_LENGHT = 60;
		boolean LAST_NAME_NULLABLE = false;

		int DNI_MIN_LENGHT = 1;
		int DNI_MAX_LENGHT = 15;
		boolean DNI_NULLABLE = false;
		boolean DNI_UNIQUE = true;

		int EMAIL_MIN_LENGHT = 11;
		int EMAIL_MAX_LENGHT = 100;
		boolean EMAIL_NULLABLE = false;
		boolean EMAIL_UNIQUE = true;

		int ACCOUNT_MIN_LENGHT = 8;
		int ACCOUNT_MAX_LENGHT = 60;
		boolean ACCOUNT_NULLABLE = false;
		boolean ACCOUNT_UNIQUE = true;

		int PASSWORD_MIN_LENGHT = 8;
		int PASSWORD_MAX_LENGHT = 60;
		boolean PASSWORD_NULLABLE = false;

		boolean PRIVILEGE_NULLABLE = false;
	}

	interface Person {
		boolean DEPENDENCY_FK_NULLABLE = false;

		int NAME_MIN_LENGHT = 1;
		int NAME_MAX_LENGHT = 60;
		boolean NAME_NULLABLE = false;

		int LAST_NAME_MIN_LENGHT = 1;
		int LAST_NAME_MAX_LENGHT = 60;
		boolean LAST_NAME_NULLABLE = false;

		int DNI_MIN_LENGHT = 1;
		int DNI_MAX_LENGHT = 15;
		boolean DNI_NULLABLE = false;
		boolean DNI_UNIQUE = true;
	}

	interface Dependency {
		int NAME_MIN_LENGHT = 1;
		int NAME_MAX_LENGHT = 60;
		boolean NAME_NULLABLE = false;
		boolean NAME_UNIQUE = true;

		int DESCRIPTION_MIN_LENGHT = 1;
		int DESCRIPTION_MAX_LENGHT = 200;
	}

	interface Weekly {
		boolean PERSONFK_NULLABLE = false;

		boolean MONDAY_NULLABLE = false;
		boolean TUESDAY_NULLABLE = false;
		boolean WEDNESDAY_NULLABLE = false;
		boolean THURDAY_NULLABLE = false;
		boolean FRIDAY_NULLABLE = false;
		boolean SATURDAY_NULLABLE = false;
		boolean SUNDAY_NULLABLE = false;

		boolean START_NULLABLE = false;
		boolean END_NULLABLE = true;

	}

	interface Access {
		boolean NAME_NULLABLE = false;
		int NAME_MIN_LENGHT = 1;
		int NAME_MAX_LENGHT = 60;

		boolean DESCRIPTION_NULLABLE = true;
		int DESCRIPTION_MIN_LENGHT = 1;
		int DESCRIPTION_MAX_LENGHT = 200;

	}

	interface Credential {
		boolean PERSON_FK_NULLABLE = false;
		boolean DATA_NULLABLE = false;
		int DATA_MIN_LENGHT = 1;
		int DATA_MAX_LENGHT = 255;

		boolean IMG_NULLABLE = false;
	}

	interface Register {
		boolean PERSON_FK_NULLABLE = false;
		boolean ACCESS_FK_NULLABLE = false;

		boolean CHECK_IN_NULLABLE = false;
		boolean CHECK_OUT_NULLABLE = true;

		boolean ENCRYPTED_DATA_NULLABLE = true;
	}
}
