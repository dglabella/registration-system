package ar.edu.unsl.fmn.gida.apis.registration.messengers;

import ar.edu.unsl.fmn.gida.apis.registration.messengers.authentication.AuthenticationMessenger;
import ar.edu.unsl.fmn.gida.apis.registration.messengers.controllers.AccessControllerMessenger;
import ar.edu.unsl.fmn.gida.apis.registration.messengers.controllers.CredentialControllerMessenger;
import ar.edu.unsl.fmn.gida.apis.registration.messengers.controllers.DependencyControllerMessenger;
import ar.edu.unsl.fmn.gida.apis.registration.messengers.controllers.PersonControllerMessenger;
import ar.edu.unsl.fmn.gida.apis.registration.messengers.controllers.RegisterControllerMessenger;
import ar.edu.unsl.fmn.gida.apis.registration.messengers.controllers.UserControllerMessenger;
import ar.edu.unsl.fmn.gida.apis.registration.messengers.controllers.WeeklyControllerMessenger;

import ar.edu.unsl.fmn.gida.apis.registration.messengers.logic.AccessServiceMessenger;
import ar.edu.unsl.fmn.gida.apis.registration.messengers.logic.CredentialServiceMessenger;
import ar.edu.unsl.fmn.gida.apis.registration.messengers.logic.DependencyServiceMessenger;
import ar.edu.unsl.fmn.gida.apis.registration.messengers.logic.PersonServiceMessenger;
import ar.edu.unsl.fmn.gida.apis.registration.messengers.logic.RegisterServiceMessenger;
import ar.edu.unsl.fmn.gida.apis.registration.messengers.logic.UserServiceMessenger;
import ar.edu.unsl.fmn.gida.apis.registration.messengers.logic.WeeklyServiceMessenger;

import ar.edu.unsl.fmn.gida.apis.registration.messengers.validation.AccessValidationMessenger;
import ar.edu.unsl.fmn.gida.apis.registration.messengers.validation.CredentialValidationMessenger;
import ar.edu.unsl.fmn.gida.apis.registration.messengers.validation.DependencyValidationMessenger;
import ar.edu.unsl.fmn.gida.apis.registration.messengers.validation.PersonValidationMessenger;
import ar.edu.unsl.fmn.gida.apis.registration.messengers.validation.RegisterValidationMessenger;
import ar.edu.unsl.fmn.gida.apis.registration.messengers.validation.UserValidationMessenger;
import ar.edu.unsl.fmn.gida.apis.registration.messengers.validation.WeeklyValidationMessenger;

public class Messenger {

	private String lang;

	private AuthenticationMessenger authenticationMessenger;

	private AccessControllerMessenger accessControllerMessenger;
	private CredentialControllerMessenger credentialControllerMessenger;
	private DependencyControllerMessenger dependencyControllerMessenger;
	private PersonControllerMessenger personControllerMessenger;
	private RegisterControllerMessenger registerControllerMessenger;
	private UserControllerMessenger userControllerMessenger;
	private WeeklyControllerMessenger weeklyControllerMessenger;

	private AccessServiceMessenger accessBusinessLogicMessenger;
	private CredentialServiceMessenger credentialBusinessLogicMessenger;
	private DependencyServiceMessenger dependencyBusinessLogicMessenger;
	private PersonServiceMessenger personBusinessLogicMessenger;
	private RegisterServiceMessenger registerBusinessLogicMessenger;
	private UserServiceMessenger userBusinessLogicMessenger;
	private WeeklyServiceMessenger weeklyBusinessLogicMessenger;

	private AccessValidationMessenger accessValidationMessenger;
	private CredentialValidationMessenger credentialValidationMessenger;
	private DependencyValidationMessenger dependencyValidationMessenger;
	private PersonValidationMessenger personValidationMessenger;
	private RegisterValidationMessenger registerValidationMessenger;
	private UserValidationMessenger userValidationMessenger;
	private WeeklyValidationMessenger weeklyValidationMessenger;

	public Messenger(String lang) {
		this.lang = lang;

		this.authenticationMessenger = new AuthenticationMessenger(this);

		this.accessControllerMessenger = new AccessControllerMessenger(this);
		this.credentialControllerMessenger = new CredentialControllerMessenger(this);
		this.dependencyControllerMessenger = new DependencyControllerMessenger(this);
		this.personControllerMessenger = new PersonControllerMessenger(this);
		this.registerControllerMessenger = new RegisterControllerMessenger(this);
		this.userControllerMessenger = new UserControllerMessenger(this);
		this.weeklyControllerMessenger = new WeeklyControllerMessenger(this);

		this.accessBusinessLogicMessenger = new AccessServiceMessenger(this);
		this.credentialBusinessLogicMessenger = new CredentialServiceMessenger(this);
		this.dependencyBusinessLogicMessenger = new DependencyServiceMessenger(this);
		this.personBusinessLogicMessenger = new PersonServiceMessenger(this);
		this.registerBusinessLogicMessenger = new RegisterServiceMessenger(this);
		this.userBusinessLogicMessenger = new UserServiceMessenger(this);
		this.weeklyBusinessLogicMessenger = new WeeklyServiceMessenger(this);

		this.accessValidationMessenger = new AccessValidationMessenger(this);
		this.credentialValidationMessenger = new CredentialValidationMessenger(this);
		this.dependencyValidationMessenger = new DependencyValidationMessenger(this);
		this.personValidationMessenger = new PersonValidationMessenger(this);
		this.registerValidationMessenger = new RegisterValidationMessenger(this);
		this.userValidationMessenger = new UserValidationMessenger(this);
		this.weeklyValidationMessenger = new WeeklyValidationMessenger(this);
	}

	public String getLang() {
		return this.lang;
	}

	public AuthenticationMessenger getAuthenticationMessenger() {
		return this.authenticationMessenger;
	}

	public AccessControllerMessenger getAccessControllerMessenger() {
		return this.accessControllerMessenger;
	}

	public CredentialControllerMessenger getCredentialControllerMessenger() {
		return this.credentialControllerMessenger;
	}

	public DependencyControllerMessenger getDependencyControllerMessenger() {
		return this.dependencyControllerMessenger;
	}

	public PersonControllerMessenger getPersonControllerMessenger() {
		return this.personControllerMessenger;
	}

	public RegisterControllerMessenger getRegisterControllerMessenger() {
		return this.registerControllerMessenger;
	}

	public UserControllerMessenger getUserControllerMessenger() {
		return this.userControllerMessenger;
	}

	public WeeklyControllerMessenger getWeeklyControllerMessenger() {
		return this.weeklyControllerMessenger;
	}

	public AccessServiceMessenger getAccessBusinessLogicMessenger() {
		return this.accessBusinessLogicMessenger;
	}

	public CredentialServiceMessenger getCredentialBusinessLogicMessenger() {
		return this.credentialBusinessLogicMessenger;
	}

	public DependencyServiceMessenger getDependencyBusinessLogicMessenger() {
		return this.dependencyBusinessLogicMessenger;
	}

	public PersonServiceMessenger getPersonBusinessLogicMessenger() {
		return this.personBusinessLogicMessenger;
	}

	public RegisterServiceMessenger getRegisterBusinessLogicMessenger() {
		return this.registerBusinessLogicMessenger;
	}

	public UserServiceMessenger getUserBusinessLogicMessenger() {
		return this.userBusinessLogicMessenger;
	}

	public WeeklyServiceMessenger getWeeklyBusinessLogicMessenger() {
		return this.weeklyBusinessLogicMessenger;
	}

	public AccessValidationMessenger getAccessValidationMessenger() {
		return this.accessValidationMessenger;
	}

	public CredentialValidationMessenger getCredentialValidationMessenger() {
		return this.credentialValidationMessenger;
	}

	public DependencyValidationMessenger getDependencyValidationMessenger() {
		return this.dependencyValidationMessenger;
	}

	public PersonValidationMessenger getPersonValidationMessenger() {
		return this.personValidationMessenger;
	}

	public RegisterValidationMessenger getRegisterValidationMessenger() {
		return this.registerValidationMessenger;
	}

	public UserValidationMessenger getUserValidationMessenger() {
		return this.userValidationMessenger;
	}

	public WeeklyValidationMessenger getWeeklyValidationMessenger() {
		return this.weeklyValidationMessenger;
	}
}
