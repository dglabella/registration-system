package ar.edu.unsl.fmn.gida.apis.registration.messengers.logic;

import ar.edu.unsl.fmn.gida.apis.registration.messengers.Messenger;

public class WeeklyServiceMessenger extends ServiceMessenger {

	public WeeklyServiceMessenger(Messenger messenger) {
		super(messenger);
	}

	public String wrongWeeklyStartDate() {
		String ret = null;

		switch (this.getMessenger().getLang()) {
			case "EN":
				ret = "cannot insert a new weekly with start date before today, or before or equal to the current weekly";
				break;
			case "ES":
				ret = "No es posible insertar un semanario nuevo con fecha de inicio anterior a la fecha de hoy, o anterior o igual a la fecha de inicio del semanario vigente";
				break;
			default:
				ret = "cannot insert a new weekly with start date before today, or before or equal to the current weekly";
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
