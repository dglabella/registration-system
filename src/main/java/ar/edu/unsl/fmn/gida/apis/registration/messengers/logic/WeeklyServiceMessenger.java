package ar.edu.unsl.fmn.gida.apis.registration.messengers.logic;

import ar.edu.unsl.fmn.gida.apis.registration.messengers.Messenger;

public class WeeklyServiceMessenger extends ServiceMessenger {

	public WeeklyServiceMessenger(Messenger lang) {
		super(lang);
	}

	public String getCurrentWeeklyError(int personId) {
		String ret = null;

		switch (this.getMessages().getLang()) {
			case "EN":
				ret = "FATAL ERROR: database integrity may be corrupted; person " + personId
						+ " has not a current weekly";
				break;
			case "ES":
				ret = "ERROR FATAL: la integridad de la base de datos puede estar corrupta; la persona "
						+ personId + " no tiene semanario actual";
				break;
			default:
				ret = "FATAL ERROR: database integrity may be corrupted; person " + personId
						+ " has not a current weekly";
		}
		return ret;
	}

	public String wrongWeeklyDatetime() {
		String ret = null;

		switch (this.getMessages().getLang()) {
			case "EN":
				ret = "cannot insert/update a new weekly with start datetime before the current clock";
				break;
			case "ES":
				ret = "No es posible insertar/actualizar un semanario con fecha de inicio anterior al día de hoy inclusive";
				break;
			default:
				ret = "cannot insert/update a new weekly with start datetime before the current clock";
		}
		return ret;
	}
}
