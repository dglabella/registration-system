package ar.edu.unsl.fmn.gida.apis.registration.services.messages;

import ar.edu.unsl.fmn.gida.apis.registration.services.messages.logic.AccessBusinessLogicMessages;
import ar.edu.unsl.fmn.gida.apis.registration.services.messages.logic.CredentialBusinessLogicMessages;
import ar.edu.unsl.fmn.gida.apis.registration.services.messages.logic.DependencyBusinessLogicMessages;
import ar.edu.unsl.fmn.gida.apis.registration.services.messages.logic.PersonBusinessLogicMessages;
import ar.edu.unsl.fmn.gida.apis.registration.services.messages.logic.RegisterBusinessLogicMessages;
import ar.edu.unsl.fmn.gida.apis.registration.services.messages.logic.UserBusinessLogicMessages;
import ar.edu.unsl.fmn.gida.apis.registration.services.messages.logic.WeeklyBusinessLogicMessages;
import ar.edu.unsl.fmn.gida.apis.registration.services.messages.validation.AccessValidationMessages;
import ar.edu.unsl.fmn.gida.apis.registration.services.messages.validation.CredentialValidationMessages;
import ar.edu.unsl.fmn.gida.apis.registration.services.messages.validation.DependencyValidationMessages;
import ar.edu.unsl.fmn.gida.apis.registration.services.messages.validation.PersonValidationMessages;
import ar.edu.unsl.fmn.gida.apis.registration.services.messages.validation.RegisterValidationMessages;
import ar.edu.unsl.fmn.gida.apis.registration.services.messages.validation.UserValidationMessages;
import ar.edu.unsl.fmn.gida.apis.registration.services.messages.validation.WeeklyValidationMessages;

public class Messages {

    private String lang;

    private AccessBusinessLogicMessages accessBusinessLogicMessages;
    private CredentialBusinessLogicMessages credentialBusinessLogicMessages;
    private DependencyBusinessLogicMessages dependencyBusinessLogicMessages;
    private PersonBusinessLogicMessages personBusinessLogicMessages;
    private RegisterBusinessLogicMessages registerBusinessLogicMessages;
    private UserBusinessLogicMessages userBusinessLogicMessages;
    private WeeklyBusinessLogicMessages weeklyBusinessLogicMessages;

    private AccessValidationMessages accessValidationMessages;
    private CredentialValidationMessages credentialValidationMessages;
    private DependencyValidationMessages dependencyValidationMessages;
    private PersonValidationMessages personValidationMessages;
    private RegisterValidationMessages registerValidationMessages;
    private UserValidationMessages userValidationMessages;
    private WeeklyValidationMessages weeklyValidationMessages;

    public Messages(String lang) {
        this.lang = lang;

        this.accessBusinessLogicMessages = new AccessBusinessLogicMessages(this);
        this.credentialBusinessLogicMessages = new CredentialBusinessLogicMessages(this);
        this.dependencyBusinessLogicMessages = new DependencyBusinessLogicMessages(this);
        this.personBusinessLogicMessages = new PersonBusinessLogicMessages(this);
        this.registerBusinessLogicMessages = new RegisterBusinessLogicMessages(this);
        this.userBusinessLogicMessages = new UserBusinessLogicMessages(this);
        this.weeklyBusinessLogicMessages = new WeeklyBusinessLogicMessages(this);

        this.accessValidationMessages = new AccessValidationMessages(this);
        this.credentialValidationMessages = new CredentialValidationMessages(this);
        this.dependencyValidationMessages = new DependencyValidationMessages(this);
        this.personValidationMessages = new PersonValidationMessages(this);
        this.registerValidationMessages = new RegisterValidationMessages(this);
        this.userValidationMessages = new UserValidationMessages(this);
        this.weeklyValidationMessages = new WeeklyValidationMessages(this);
    }

    public String getLang() {
        return this.lang;
    }

    public AccessBusinessLogicMessages getAccessBusinessLogicMessages() {
        return this.accessBusinessLogicMessages;
    }

    public void setAccessBusinessLogicMessages(
            AccessBusinessLogicMessages accessBusinessLogicMessages) {
        this.accessBusinessLogicMessages = accessBusinessLogicMessages;
    }

    public CredentialBusinessLogicMessages getCredentialBusinessLogicMessages() {
        return this.credentialBusinessLogicMessages;
    }

    public void setCredentialBusinessLogicMessages(
            CredentialBusinessLogicMessages credentialBusinessLogicMessages) {
        this.credentialBusinessLogicMessages = credentialBusinessLogicMessages;
    }

    public DependencyBusinessLogicMessages getDependencyBusinessLogicMessages() {
        return this.dependencyBusinessLogicMessages;
    }

    public void setDependencyBusinessLogicMessages(
            DependencyBusinessLogicMessages dependencyBusinessLogicMessages) {
        this.dependencyBusinessLogicMessages = dependencyBusinessLogicMessages;
    }

    public PersonBusinessLogicMessages getPersonBusinessLogicMessages() {
        return this.personBusinessLogicMessages;
    }

    public void setPersonBusinessLogicMessages(
            PersonBusinessLogicMessages personBusinessLogicMessages) {
        this.personBusinessLogicMessages = personBusinessLogicMessages;
    }

    public RegisterBusinessLogicMessages getRegisterBusinessLogicMessages() {
        return this.registerBusinessLogicMessages;
    }

    public void setRegisterBusinessLogicMessages(
            RegisterBusinessLogicMessages registerBusinessLogicMessages) {
        this.registerBusinessLogicMessages = registerBusinessLogicMessages;
    }

    public UserBusinessLogicMessages getUserBusinessLogicMessages() {
        return this.userBusinessLogicMessages;
    }

    public void setUserBusinessLogicMessages(UserBusinessLogicMessages userBusinessLogicMessages) {
        this.userBusinessLogicMessages = userBusinessLogicMessages;
    }

    public WeeklyBusinessLogicMessages getWeeklyBusinessLogicMessages() {
        return this.weeklyBusinessLogicMessages;
    }

    public void setWeeklyBusinessLogicMessages(
            WeeklyBusinessLogicMessages weeklyBusinessLogicMessages) {
        this.weeklyBusinessLogicMessages = weeklyBusinessLogicMessages;
    }

    public AccessValidationMessages getAccessValidationMessages() {
        return this.accessValidationMessages;
    }

    public void setAccessValidationMessages(AccessValidationMessages accessValidationMessages) {
        this.accessValidationMessages = accessValidationMessages;
    }

    public CredentialValidationMessages getCredentialValidationMessages() {
        return this.credentialValidationMessages;
    }

    public void setCredentialValidationMessages(
            CredentialValidationMessages credentialValidationMessages) {
        this.credentialValidationMessages = credentialValidationMessages;
    }

    public DependencyValidationMessages getDependencyValidationMessages() {
        return this.dependencyValidationMessages;
    }

    public void setDependencyValidationMessages(
            DependencyValidationMessages dependencyValidationMessages) {
        this.dependencyValidationMessages = dependencyValidationMessages;
    }

    public PersonValidationMessages getPersonValidationMessages() {
        return this.personValidationMessages;
    }

    public void setPersonValidationMessages(PersonValidationMessages personValidationMessages) {
        this.personValidationMessages = personValidationMessages;
    }

    public RegisterValidationMessages getRegisterValidationMessages() {
        return this.registerValidationMessages;
    }

    public void setRegisterValidationMessages(
            RegisterValidationMessages registerValidationMessages) {
        this.registerValidationMessages = registerValidationMessages;
    }

    public UserValidationMessages getUserValidationMessages() {
        return this.userValidationMessages;
    }

    public void setUserValidationMessages(UserValidationMessages userValidationMessages) {
        this.userValidationMessages = userValidationMessages;
    }

    public WeeklyValidationMessages getWeeklyValidationMessages() {
        return this.weeklyValidationMessages;
    }

    public void setWeeklyValidationMessages(WeeklyValidationMessages weeklyValidationMessages) {
        this.weeklyValidationMessages = weeklyValidationMessages;
    }
}
