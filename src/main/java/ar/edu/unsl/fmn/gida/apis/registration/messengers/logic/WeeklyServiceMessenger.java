package ar.edu.unsl.fmn.gida.apis.registration.messengers.logic;

import ar.edu.unsl.fmn.gida.apis.registration.messengers.Messenger;

public class WeeklyServiceMessenger extends ServiceMessenger {

	public WeeklyServiceMessenger(Messenger messenger) {
		super(messenger);
	}

	public String currentWeeklyNotFound(int personId) {
		String ret = null;

		switch (this.getMessenger().getLang()) {
			case "EN":
				ret = "person " + personId
						+ " has not a current weekly registered: there should be in the database but is not";
				break;
			case "ES":
				ret = "la persona " + personId
						+ " no tiene semanario actual registrado: debería existir en la base de datos, pero no existe";
				break;
			default:
				ret = "person " + personId
						+ " has not a current weekly registered: there should be in the database but is not";
		}

		return ret;
	}

	public String wrongWeeklyDatetime() {
		String ret = null;

		switch (this.getMessenger().getLang()) {
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
