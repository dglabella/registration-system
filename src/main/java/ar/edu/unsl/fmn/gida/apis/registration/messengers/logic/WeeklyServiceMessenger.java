package ar.edu.unsl.fmn.gida.apis.registration.messengers.logic;

import ar.edu.unsl.fmn.gida.apis.registration.messengers.Messenger;

public class WeeklyServiceMessenger extends ServiceMessenger {

	public WeeklyServiceMessenger(Messenger messenger) {
		super(messenger);
	}

	public String notFoundByPersonIdAndContainingDate(int personId, String date) {
		String ret = null;

		switch (this.getMessenger().getLang()) {
			case "EN":
				ret = "there is no weekly from person " + personId + " that contains the date "
						+ date;
				break;
			case "ES":
				ret = "no existe semanario de la persona " + personId + " que contenga a la fecha "
						+ date;
				break;
			default:
				ret = "there is no weekly from person " + personId + " that contains the date "
						+ date;
		}

		return ret;
	}

	public String crossDates() {
		String ret = null;

		switch (this.getMessenger().getLang()) {
			case "EN":
				ret = "cannot insert a new weekly with cross dates";
				break;
			case "ES":
				ret = "no es posible insertar un semanario con fechas cruzadas";
				break;
			default:
				ret = "cannot insert a new weekly with cross dates";
		}

		return ret;
	}

	public String overlappedDates() {
		String ret = null;

		switch (this.getMessenger().getLang()) {
			case "EN":
				ret = "cannot insert a new weekly with overlapped dates with other weeklies";
				break;
			case "ES":
				ret = "No es posible insertar un semanario nuevo que tenga fechas superpuestas con otros semanarios";
				break;
			default:
				ret = "cannot insert a new weekly with overlapped dates with other weeklies";
		}

		return ret;
	}

	public String stringToDateParseError() {
		String ret = null;

		switch (this.getMessenger().getLang()) {
			case "EN":
				ret = "cannot parse the input date";
				break;
			case "ES":
				ret = "No se puede traducir la fecha recibida";
				break;
			default:
				ret = "cannot parse the input date";
		}

		return ret;
	}
}
