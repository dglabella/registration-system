package ar.edu.unsl.fmn.gida.apis.registration.messengers.validation;

import ar.edu.unsl.fmn.gida.apis.registration.messengers.Messenger;

public class UserValidationMessenger extends ValidationMessenger {

	public UserValidationMessenger(Messenger messenger) {
		super(messenger);
	}

	public String invalidNamePattern() {
		String ret = null;

		switch (this.getMessenger().getLang()) {
			case "EN":
				ret = "invalid user name: cannot contain numbers or special characters";
				break;
			case "ES":
				ret = "nombre del usuario inválido: no puede contener números o caracteres especiales";
				break;
			default:
				ret = "invalid user name: cannot contain numbers or special characters";
		}

		return ret;
	}

	public String invalidLastnamePattern() {
		String ret = null;

		switch (this.getMessenger().getLang()) {
			case "EN":
				ret = "invalid user name: cannot contain numbers or special characters";
				break;
			case "ES":
				ret = "nombre del usuario inválido: no puede contener números o caracteres especiales";
				break;
			default:
				ret = "invalid user name: cannot contain numbers or special characters";
		}

		return ret;
	}

	public String invalidAccount() {
		String ret = null;

		switch (this.getMessenger().getLang()) {
			case "EN":
				ret = "invalid user account: must follow the next specification: "
						+ "1- Begin with a letter (uppercase or lowercase) or a digit. "
						+ "2- Ends with a letter (uppercase or lowercase) or a digit. "
						+ "3- can contain letters (uppercase or lowercase) or digits. "
						+ "4- can contain: .(dot), -(hyphen), _(underscore). "
						+ "5- cannot contain .(dot), -(hyphen), _(underscore) one after the other";
				break;
			case "ES":
				ret = "nombre de cuenta de usuario invalida: debe seguir la siguiente especificación: "
						+ "1- Comenzar con una letra (mayúscula o minúscula) o un dígito. "
						+ "2- Terminar con una letra (mayúscula o minúscula) o un dígito. "
						+ "3- Puede contener letras (mayúsculas o minúsculas) o un dígito. "
						+ "4- Puede contener: . (dot), - (hyphen), _ (underscore). "
						+ "5- No puede contener: . (dot), - (hyphen), _ (underscore) uno seguido de otro";
				break;
			default:
				ret = "invalid user account: must follow the next specification: "
						+ "1- Begin with a letter (uppercase or lowercase) or a digit. "
						+ "2- Ends with a letter (uppercase or lowercase) or a digit. "
						+ "3- Can contain letters (uppercase or lowercase) or digits. "
						+ "4- Can contain: .(dot), -(hyphen), _(underscore). "
						+ "5- Cannot contain .(dot), -(hyphen), _(underscore) one after the other";
		}

		return ret;
	}

	public String invalidPassword() {
		String ret = null;

		switch (this.getMessenger().getLang()) {
			case "EN":
				ret = "invalid user passowrd: must follow the next specification: "
						+ "must contain only alphanumeric characters";
				break;
			case "ES":
				ret = "password de usuario inválido: debe seguir la siguiente especificación: "
						+ "debe contener solamente caracteres alfanuméricos";
				break;
			default:
				ret = "invalid user passowrd: must follow the next specification: "
						+ "must contain only alphanumeric characters";
		}

		return ret;
	}
}
