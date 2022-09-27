package ar.edu.unsl.fmn.gida.apis.registration.model.constraints;

public interface Constraints {

    public class User {
        public static final int NAME_MAX_LENGHT = 60;
        public static final int NAME_MIN_LENGHT = 1;
        public static final boolean NAME_NULLABLE = false;
        public static final boolean NAME_UNIQUE = false;

        public static final int LAST_NAME_MAX_LENGHT = 60;
        public static final int LAST_NAME_MIN_LENGHT = 1;
        public static final boolean LAST_NAME_NULLABLE = false;
        public static final boolean LAST_NAME_UNIQUE = false;

        public static final int DNI_MAX_LENGHT = 15;
        public static final int DNI_MIN_LENGHT = 1;
        public static final boolean DNI_NULLABLE = false;
        public static final boolean DNI_UNIQUE = true;

        public static final int EMAIL_MAX_LENGHT = 100;
        public static final int EMAIL_MIN_LENGHT = 11;
        public static final boolean EMAIL_NULLABLE = false;
        public static final boolean EMAIL_UNIQUE = true;

        public static final int ACCOUNT_MAX_LENGHT = 60;
        public static final int ACCOUNT_MIN_LENGHT = 60;
        public static final boolean ACCOUNT_NULLABLE = false;
        public static final boolean ACCOUNT_UNIQUE = true;

        public static final int PASSWORD_MAX_LENGHT = 60;
        public static final int PASSWORD_MIN_LENGHT = 60;
        public static final boolean PASSWORD_NULLABLE = false;
        public static final boolean PASSWORD_UNIQUE = false;

        public static final boolean PRIVILEGE_NULLABLE = false;
    }

    public class Person {
        // public static final int
    }
}
