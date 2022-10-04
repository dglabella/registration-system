package ar.edu.unsl.fmn.gida.apis.registration.model.constraints;

import ar.edu.unsl.fmn.gida.apis.registration.enums.Role;

public interface Constraints {

    public class User {
        public static final int NAME_MIN_LENGHT = 60;
        public static final int NAME_MAX_LENGHT = 1;
        public static final boolean NAME_NULLABLE = false;

        public static final int LAST_NAME_MIN_LENGHT = 60;
        public static final int LAST_NAME_MAX_LENGHT = 1;
        public static final boolean LAST_NAME_NULLABLE = false;

        public static final int DNI_MIN_LENGHT = 15;
        public static final int DNI_MAX_LENGHT = 1;
        public static final boolean DNI_NULLABLE = false;
        public static final boolean DNI_UNIQUE = true;

        public static final int EMAIL_MIN_LENGHT = 100;
        public static final int EMAIL_MAX_LENGHT = 11;
        public static final boolean EMAIL_NULLABLE = false;
        public static final boolean EMAIL_UNIQUE = true;

        public static final int ACCOUNT_MIN_LENGHT = 60;
        public static final int ACCOUNT_MAX_LENGHT = 60;
        public static final boolean ACCOUNT_NULLABLE = false;
        public static final boolean ACCOUNT_UNIQUE = true;

        public static final int PASSWORD_MIN_LENGHT = 60;
        public static final int PASSWORD_MAX_LENGHT = 60;
        public static final boolean PASSWORD_NULLABLE = false;

        public static final boolean PRIVILEGE_NULLABLE = false;
    }

    public class Person {

        public static final boolean DEPENDENCY_FK_NULLABEL = false;

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

        public static final int ROLE_LAST_ORDINAL = Role.VISITOR.ordinal();
    }

    public class Dependency{
        public static final int NAME_MIN_LENGHT = 1;//para name dependency
        public static final int NAME_MAX_LENGHT = 60;
        public static final boolean NAME_NULLABLE = false;
        public static final boolean NAME_UNIQUE = true;

        public static final int DESCRIPTION_MIN_LENGHT = 1;//para descritpion dependency
        public static final int DESCRIPTION_MAX_LENGHT = 200;
        
        public static final boolean ACTIVE_NULLABLE = false;
        
    }

    public class Weekly{
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

    public class Access{
        public static final boolean ACCESSNAME_NULLABLE = false;
        public static final int ACCESSNAME_MIN_LENGHT = 1;
        public static final int ACCESSNAME_MAX_LENGHT = 60;

        public static final int DESCRIPTION_MIN_LENGHT = 1;
        public static final int DESCRIPTION_MAX_LENGHT = 200;

    }
}
