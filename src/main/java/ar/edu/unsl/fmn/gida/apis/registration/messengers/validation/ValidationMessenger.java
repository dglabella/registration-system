package ar.edu.unsl.fmn.gida.apis.registration.messengers.validation;

import ar.edu.unsl.fmn.gida.apis.registration.messengers.Messenger;

public abstract class ValidationMessenger {

	private Messenger messenger;

	public ValidationMessenger(Messenger messenger) {
		this.messenger = messenger;
	}

	public Messenger getMessenger() {
		return this.messenger;
	}

	public String idNotRequired() {
		String ret = null;

		switch (this.messenger.getLang()) {
			case "EN":
				ret = "id cannot be specified in entity";
				break;
			case "ES":
				ret = "no puede especificarse el id en la entidad";
				break;
			default:
				ret = "id cannot be specified in entity";
		}

		return ret;
	}

	public String attributeRequired(String entityName, String attributeName) {
		String ret = null;

		switch (this.messenger.getLang()) {
			case "EN":
				ret = "'" + entityName + "' " + attributeName + " is required";
				break;
			case "ES":
				ret = "'" + entityName + "' " + attributeName + " es requerido";
				break;
			default:
				ret = "'" + entityName + "' " + attributeName + " is required";
		}

		return ret;
	}

	public String invalidAttributeSize(String entityName, String attributeName, int minLenght,
			int maxLenght) {
		String ret = null;

		switch (this.messenger.getLang()) {
			case "EN":
				ret = "invalid " + entityName + " " + attributeName + ": must contain between "
						+ minLenght + " and " + maxLenght + " characters";
				break;
			case "ES":
				ret = entityName + " " + attributeName + " inválido: debe contener entre"
						+ minLenght + " y " + maxLenght + " caracteres";
				break;
			default:
				ret = "invalid " + entityName + " " + attributeName + ": must contain between "
						+ minLenght + " and " + maxLenght + " characters";
		}

		return ret;
	}

	public String invalidChar(String entityName, String attributeName) {
		String ret = null;

		switch (this.messenger.getLang()) {
			case "EN":
				ret = "invalid " + entityName + " " + attributeName
						+ ": must have only valids characters.";
				break;
			case "ES":
				ret = entityName + " " + attributeName
						+ " inválido: debe contener solo caracteres válidos.";
				break;
			default:
				ret = "invalid " + entityName + " " + attributeName
						+ ": must have only valids characters.";
		}

		return ret;
	}

	public String onlyNumberAllowed(String entityName, String attributeName) {
		String ret = null;

		switch (this.messenger.getLang()) {
			case "EN":
				ret = "invalid " + entityName + " " + attributeName + ": only digits are allowed";
				break;
			case "ES":
				ret = entityName + " " + attributeName + " inválido: solo dígitos estan permitidos";
				break;
			default:
				ret = "invalid " + entityName + " " + attributeName + ": only digits are allowed";
		}

		return ret;
	}

	public String notAnEmail(String entityName, String attributeName) {
		String ret = null;

		switch (this.messenger.getLang()) {
			case "EN":
				ret = "invalid " + entityName + " " + attributeName
						+ ": must be like anything@example.com";
				break;
			case "ES":
				ret = entityName + " " + attributeName
						+ " inválido: debe ser como cualquiercosa@ejemplo.com";
				break;
			default:
				ret = "invalid " + entityName + " " + attributeName
						+ ": must be like anything@example.com";
		}

		return ret;
	}
}
