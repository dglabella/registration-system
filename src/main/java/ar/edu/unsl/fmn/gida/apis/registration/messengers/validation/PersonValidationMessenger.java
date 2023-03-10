package ar.edu.unsl.fmn.gida.apis.registration.messengers.validation;

import ar.edu.unsl.fmn.gida.apis.registration.messengers.Messenger;

public class PersonValidationMessenger extends ValidationMessenger {

	public PersonValidationMessenger(Messenger messenger) {
		super(messenger);
	}

	public String invalidNamePattern() {
		String ret = null;

		switch (this.getMessenger().getLang()) {
			case "EN":
				ret = "invalid person name: cannot contain numbers or special characters";
				break;
			case "ES":
				ret = "nombre de persona inválido: no puede contener números o caracteres especiales";
				break;
			default:
				ret = "invalid person name: cannot contain numbers or special characters";
		}

		return ret;
	}

	public String invalidLastnamePattern() {
		String ret = null;

		switch (this.getMessenger().getLang()) {
			case "EN":
				ret = "invalid person last name: cannot contain numbers or special characters";
				break;
			case "ES":
				ret = "apellido de persona inválido: no puede contener números o caracteres especiales";
				break;
			default:
				ret = "invalid person last name: cannot contain numbers or special characters";
		}

		return ret;
	}
}
