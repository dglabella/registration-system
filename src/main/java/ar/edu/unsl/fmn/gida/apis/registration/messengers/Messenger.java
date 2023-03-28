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
import ar.edu.unsl.fmn.gida.apis.registration.messengers.logic.ResponsibilityServiceMessenger;
import ar.edu.unsl.fmn.gida.apis.registration.messengers.logic.UserServiceMessenger;
import ar.edu.unsl.fmn.gida.apis.registration.messengers.logic.WeeklyServiceMessenger;
import ar.edu.unsl.fmn.gida.apis.registration.messengers.logic.WorkAttendanceServiceMessenger;
import ar.edu.unsl.fmn.gida.apis.registration.messengers.validation.AccessValidationMessenger;
import ar.edu.unsl.fmn.gida.apis.registration.messengers.validation.CredentialValidationMessenger;
import ar.edu.unsl.fmn.gida.apis.registration.messengers.validation.DependencyValidationMessenger;
import ar.edu.unsl.fmn.gida.apis.registration.messengers.validation.PersonValidationMessenger;
import ar.edu.unsl.fmn.gida.apis.registration.messengers.validation.RegisterValidationMessenger;
import ar.edu.unsl.fmn.gida.apis.registration.messengers.validation.ResponsibilityValidationMessenger;
import ar.edu.unsl.fmn.gida.apis.registration.messengers.validation.UserValidationMessenger;
import ar.edu.unsl.fmn.gida.apis.registration.messengers.validation.WeeklyValidationMessenger;
import ar.edu.unsl.fmn.gida.apis.registration.messengers.validation.WorkAttendanceValidationMessenger;

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

	private AccessServiceMessenger accessServiceMessenger;
	private CredentialServiceMessenger credentialServiceMessenger;
	private DependencyServiceMessenger dependencyServiceMessenger;
	private PersonServiceMessenger personServiceMessenger;
	private RegisterServiceMessenger registerServiceMessenger;
	private ResponsibilityServiceMessenger responsibilityServiceMessenger;
	private UserServiceMessenger userServiceMessenger;
	private WeeklyServiceMessenger weeklyServiceMessenger;
	private WorkAttendanceServiceMessenger workAttendanceServiceMessenger;

	private AccessValidationMessenger accessValidationMessenger;
	private CredentialValidationMessenger credentialValidationMessenger;
	private DependencyValidationMessenger dependencyValidationMessenger;
	private PersonValidationMessenger personValidationMessenger;
	private RegisterValidationMessenger registerValidationMessenger;
	private ResponsibilityValidationMessenger responsibilityValidationMessenger;
	private UserValidationMessenger userValidationMessenger;
	private WeeklyValidationMessenger weeklyValidationMessenger;
	private WorkAttendanceValidationMessenger workAttendanceValidationMessenger;

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

		this.accessServiceMessenger = new AccessServiceMessenger(this);
		this.credentialServiceMessenger = new CredentialServiceMessenger(this);
		this.dependencyServiceMessenger = new DependencyServiceMessenger(this);
		this.personServiceMessenger = new PersonServiceMessenger(this);
		this.registerServiceMessenger = new RegisterServiceMessenger(this);
		this.responsibilityServiceMessenger = new ResponsibilityServiceMessenger(this);
		this.userServiceMessenger = new UserServiceMessenger(this);
		this.weeklyServiceMessenger = new WeeklyServiceMessenger(this);
		this.workAttendanceServiceMessenger = new WorkAttendanceServiceMessenger(this);

		this.accessValidationMessenger = new AccessValidationMessenger(this);
		this.credentialValidationMessenger = new CredentialValidationMessenger(this);
		this.dependencyValidationMessenger = new DependencyValidationMessenger(this);
		this.personValidationMessenger = new PersonValidationMessenger(this);
		this.registerValidationMessenger = new RegisterValidationMessenger(this);
		this.responsibilityValidationMessenger = new ResponsibilityValidationMessenger(this);
		this.userValidationMessenger = new UserValidationMessenger(this);
		this.weeklyValidationMessenger = new WeeklyValidationMessenger(this);
		this.workAttendanceValidationMessenger = new WorkAttendanceValidationMessenger(this);
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

	public AccessServiceMessenger getAccessServiceMessenger() {
		return this.accessServiceMessenger;
	}

	public CredentialServiceMessenger getCredentialServiceMessenger() {
		return this.credentialServiceMessenger;
	}

	public DependencyServiceMessenger getDependencyServiceMessenger() {
		return this.dependencyServiceMessenger;
	}

	public PersonServiceMessenger getPersonServiceMessenger() {
		return this.personServiceMessenger;
	}

	public RegisterServiceMessenger getRegisterServiceMessenger() {
		return this.registerServiceMessenger;
	}

	public ResponsibilityServiceMessenger getResponsibilityServiceMessenger() {
		return this.responsibilityServiceMessenger;
	}

	public UserServiceMessenger getUserServiceMessenger() {
		return this.userServiceMessenger;
	}

	public WeeklyServiceMessenger getWeeklyServiceMessenger() {
		return this.weeklyServiceMessenger;
	}

	public WorkAttendanceServiceMessenger getWorkAttendanceServiceMessenger() {
		return this.workAttendanceServiceMessenger;
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

	public ResponsibilityValidationMessenger getResponsibilityValidationMessenger() {
		return this.responsibilityValidationMessenger;
	}

	public UserValidationMessenger getUserValidationMessenger() {
		return this.userValidationMessenger;
	}

	public WeeklyValidationMessenger getWeeklyValidationMessenger() {
		return this.weeklyValidationMessenger;
	}

	public WorkAttendanceValidationMessenger getWorkAttendanceValidationMessenger() {
		return this.workAttendanceValidationMessenger;
	}
}
