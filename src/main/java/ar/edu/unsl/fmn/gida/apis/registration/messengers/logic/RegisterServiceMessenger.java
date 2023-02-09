package ar.edu.unsl.fmn.gida.apis.registration.messengers.logic;

import ar.edu.unsl.fmn.gida.apis.registration.messengers.Messenger;

public class RegisterServiceMessenger extends ServiceMessenger {

	public RegisterServiceMessenger(Messenger lang) {
		super(lang);
	}

	public String dateValueSpecificationErrorMessage() {
		String ret = null;

		switch (this.getMessages().getLang()) {
			case "EN":
				ret = "\"from\" date cannot be a later date than \"to\" date";
				break;
			case "ES":
				ret = "la fecha \"from\" no puede ser posterior a la fecha \"to\"";
				break;
			default:
				ret = "\"from\" date cannot be a later date than \"to\" date";
		}
		return ret;
	}

	public String dateFormatSpecificationErrorMessage() {
		String ret = null;

		switch (this.getMessages().getLang()) {
			case "EN":
				ret = "date format is wrong, make sure that date format follows the right specification";
				break;
			case "ES":
				ret = "el formato de la fecha es erróneo, asegúrese que el formato de las fechas siga las especificaciones correctas";
				break;
			default:
				ret = "date format is wrong, make sure that date format follows the right specification";
		}
		return ret;
	}
}
