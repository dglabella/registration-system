package ar.edu.unsl.fmn.gida.apis.registration.model.constraints;

public interface Constraints {

    public class User {
        public static final int NAME_MIN_LENGHT = 1;
        public static final int NAME_MAX_LENGHT = 60;
        public static final boolean NAME_NULLABLE = false;

        public static final int LAST_NAME_MIN_LENGHT = 1;
        public static final int LAST_NAME_MAX_LENGHT = 60;
        public static final boolean LAST_NAME_NULLABLE = false;

        public static final int DNI_MIN_LENGHT = 1;
        public static final int DNI_MAX_LENGHT = 15;
        public static final boolean DNI_NULLABLE = false;
        public static final boolean DNI_UNIQUE = true;

        public static final int EMAIL_MIN_LENGHT = 11;
        public static final int EMAIL_MAX_LENGHT = 100;
        public static final boolean EMAIL_NULLABLE = false;
        public static final boolean EMAIL_UNIQUE = true;

        public static final int ACCOUNT_MIN_LENGHT = 8;
        public static final int ACCOUNT_MAX_LENGHT = 60;
        public static final boolean ACCOUNT_NULLABLE = false;
        public static final boolean ACCOUNT_UNIQUE = true;

        public static final int PASSWORD_MIN_LENGHT = 8;
        public static final int PASSWORD_MAX_LENGHT = 60;
        public static final boolean PASSWORD_NULLABLE = false;

        public static final boolean PRIVILEGE_NULLABLE = false;
    }

    public class Person {
        public static final boolean DEPENDENCY_FK_NULLABLE = false;

        public static final int NAME_MIN_LENGHT = 1;
        public static final int NAME_MAX_LENGHT = 60;
        public static final boolean NAME_NULLABLE = false;

        public static final int LAST_NAME_MIN_LENGHT = 1;
        public static final int LAST_NAME_MAX_LENGHT = 60;
        public static final boolean LAST_NAME_NULLABLE = false;

        public static final int DNI_MIN_LENGHT = 1;
        public static final int DNI_MAX_LENGHT = 15;
        public static final boolean DNI_NULLABLE = false;
        public static final boolean DNI_UNIQUE = true;
    }

    public class Dependency {
        public static final int NAME_MIN_LENGHT = 1;
        public static final int NAME_MAX_LENGHT = 60;
        public static final boolean NAME_NULLABLE = false;
        public static final boolean NAME_UNIQUE = true;

        public static final int DESCRIPTION_MIN_LENGHT = 1;
        public static final int DESCRIPTION_MAX_LENGHT = 200;
    }

    public class Weekly {
        public static final boolean PERSONFK_NULLABLE = false;

        public static final boolean MONDAY_NULLABLE = false;
        public static final boolean TUESDAY_NULLABLE = false;
        public static final boolean WEDNESDAY_NULLABLE = false;
        public static final boolean THURDAY_NULLABLE = false;
        public static final boolean FRIDAY_NULLABLE = false;
        public static final boolean SATURDAY_NULLABLE = false;
        public static final boolean SUNDAY_NULLABLE = false;

        public static final boolean START_NULLABLE = false;
        public static final boolean END_NULLABLE = true;

    }

    public class Access {
        public static final boolean NAME_NULLABLE = false;
        public static final int NAME_MIN_LENGHT = 1;
        public static final int NAME_MAX_LENGHT = 60;

        public static final boolean DESCRIPTION_NULLABLE = true;
        public static final int DESCRIPTION_MIN_LENGHT = 1;
        public static final int DESCRIPTION_MAX_LENGHT = 200;

    }

    public class Credential {
        public static final boolean PERSON_FK_NULLABLE = false;
        public static final boolean DATA_NULLABLE = false;
        public static final int DATA_MIN_LENGHT = 1;
        public static final int DATA_MAX_LENGHT = 255;

        public static final boolean IMG_NULLABLE = false;
    }

    public class Register {
        public static final boolean PERSON_FK_NULLABLE = false;
        public static final boolean ACCESS_FK_NULLABLE = false;

        public static final boolean CHECK_IN_NULLABLE = false;
        public static final boolean CHECK_OUT_NULLABLE = true;

        public static final boolean ENCRYPTED_DATA_NULLABLE = true;
    }
}
