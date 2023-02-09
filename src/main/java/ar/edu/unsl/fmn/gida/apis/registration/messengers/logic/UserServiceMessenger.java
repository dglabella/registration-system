package ar.edu.unsl.fmn.gida.apis.registration.messengers.logic;

import ar.edu.unsl.fmn.gida.apis.registration.messengers.Messenger;

public class UserServiceMessenger extends ServiceMessenger {

	public UserServiceMessenger(Messenger lang) {
		super(lang);
	}

	public String notFoundByAccount(String account) {
		String ret = null;

		switch (this.getMessages().getLang()) {
			case "EN":
				ret = "there is no user with account name '" + account + "'";
				break;
			case "ES":
				ret = "no existe usuario con el nombre de cuenta '" + account + "'";
				break;
			default:
				ret = "there is no user with account name '" + account + "'";
		}

		return ret;
	}

	public String updateAccessViolation(String account) {
		String ret = null;

		switch (this.getMessages().getLang()) {
			case "EN":
				ret = "the user " + account + " cannot update data from other user";
				break;
			case "ES":
				ret = "el usuario " + account + " no puede cambiar datos de otro usuario";
				break;
			default:
				ret = "the user " + account + " cannot update data from other user";
		}

		return ret;
	}

	public String accesssViolation(String account) {
		String ret = null;

		switch (this.getMessages().getLang()) {
			case "EN":
				ret = "the user " + account + " is not registered. Access Violation";
				break;
			case "ES":
				ret = "el usuario " + account + " no esta registrado. Violación de acceso";
				break;
			default:
				ret = "the user " + account + " is not registered. Access Violation";
		}

		return ret;
	}
}
