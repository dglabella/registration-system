package ar.edu.unsl.fmn.gida.apis.registration.utils.lang;

public class Language {

    private String lang;

    private AccessMessages accessMessages;
    private CredentialMessages credentialMessages;
    private DependencyMessages dependencyMessages;
    private PersonMessages personMessages;
    private RegisterMessages registerMessages;
    private UserMessages userMessages;
    private WeeklyMessages weeklyMessages;

    public Language(String lang) {
        this.lang = lang;

        this.accessMessages = new AccessMessages(this);
        this.credentialMessages = new CredentialMessages(this);
        this.dependencyMessages = new DependencyMessages(this);
        this.personMessages = new PersonMessages(this);
        this.registerMessages = new RegisterMessages(this);
        this.userMessages = new UserMessages(this);
        this.weeklyMessages = new WeeklyMessages(this);
    }

    public String getLang() {
        return this.lang;
    }

    public AccessMessages getAccessMessages() {
        return this.accessMessages;
    }

    public void setAccessMessages(AccessMessages accessMessages) {
        this.accessMessages = accessMessages;
    }

    public CredentialMessages getCredentialMessages() {
        return this.credentialMessages;
    }

    public void setCredentialMessages(CredentialMessages credentialMessages) {
        this.credentialMessages = credentialMessages;
    }

    public DependencyMessages getDependencyMessages() {
        return this.dependencyMessages;
    }

    public void setDependencyMessages(DependencyMessages dependencyMessages) {
        this.dependencyMessages = dependencyMessages;
    }

    public PersonMessages getPersonMessages() {
        return this.personMessages;
    }

    public void setPersonMessages(PersonMessages personMessages) {
        this.personMessages = personMessages;
    }

    public RegisterMessages getRegisterMessages() {
        return this.registerMessages;
    }

    public void setRegisterMessages(RegisterMessages registerMessages) {
        this.registerMessages = registerMessages;
    }

    public UserMessages getUserMessages() {
        return this.userMessages;
    }

    public void setUserMessages(UserMessages userMessages) {
        this.userMessages = userMessages;
    }

    public WeeklyMessages getWeeklyMessages() {
        return this.weeklyMessages;
    }

    public void setWeeklyMessages(WeeklyMessages weeklyMessages) {
        this.weeklyMessages = weeklyMessages;
    }
}
