package ar.edu.unsl.fmn.gida.apis.registration.messengers.logic;

import ar.edu.unsl.fmn.gida.apis.registration.messengers.Messenger;

public class WeeklyServiceMessenger extends ServiceMessenger {

	public WeeklyServiceMessenger(Messenger messenger) {
		super(messenger);
	}

	public String crossDates() {
		String ret = null;

		switch (this.getMessenger().getLang()) {
			case "EN":
				ret = "cannot insert a new weekly with cross dates";
				break;
			case "ES":
				ret = "No es posible insertar un semanario con fechas cruzadas";
				break;
			default:
				ret = "cannot insert a new weekly with cross dates";
		}

		return ret;
	}

	public String wrongWeeklyStartDates() {
		String ret = null;

		switch (this.getMessenger().getLang()) {
			case "EN":
				ret = "cannot insert a new weekly with start date before or equal to the current weekly start date";
				break;
			case "ES":
				ret = "No es posible insertar un semanario nuevo con fecha de inicio anterior o igual a la fecha de inicio del semanario vigente";
				break;
			default:
				ret = "cannot insert a new weekly with start date before or equal to the current weekly start date";
		}

		return ret;
	}

	public String startDateNotqualToCurrentWeeklyEndDate() {
		String ret = null;

		switch (this.getMessenger().getLang()) {
			case "EN":
				ret = "cannot insert a new weekly with start date different to the current weekly end date";
				break;
			case "ES":
				ret = "No es posible insertar un semanario nuevo con fecha de inicio distina a la fecha de fin del semanario vigente";
				break;
			default:
				ret = "cannot insert a new weekly with start date different to the current weekly end date";
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
