package ar.edu.unsl.fmn.gida.apis.registration.messengers.logic;

import ar.edu.unsl.fmn.gida.apis.registration.messengers.Messenger;

public class PersonServiceMessenger extends ServiceMessenger {

	public PersonServiceMessenger(Messenger messenger) {
		super(messenger);
	}

	public String notFoundByDni(String dni) {
		String ret = null;

		switch (this.getMessenger().getLang()) {
			case "EN":
				ret = "there is no person with dni: " + dni;
				break;
			case "ES":
				ret = "no existe una persona con dni: " + dni;
				break;
			default:
				ret = "there is no person with dni: " + dni;
		}

		return ret;
	}

	public String checkedQrOutOfDates(String personName, String personLastname, String dni) {
		String ret = null;

		switch (this.getMessenger().getLang()) {
			case "EN":
				ret = "QR from " + personLastname + " " + personName + " with dni " + dni
						+ " has been checked outside of dates of its assigned weeklies";
				break;
			case "ES":
				ret = "El QR de " + personLastname + " " + personName + " con dni " + dni
						+ " ha sido leido fuera de fechas de sus semanarios asignados";
				break;
			default:
				ret = "QR from " + personLastname + " " + personName + " with dni " + dni
						+ " has been checked outside of dates of its assigned weeklies";
		}

		return ret;
	}

	public String extraordinaryAssistance(String personName, String personLastname, String dni) {
		String ret = null;

		switch (this.getMessenger().getLang()) {
			case "EN":
				ret = "extraordinary assistance from " + personLastname + " " + personName
						+ " with dni " + dni;
				break;
			case "ES":
				ret = "asistencia extraordinaria de " + personLastname + " " + personName
						+ " con dni " + dni;
				break;
			default:
				ret = "extraordinary assistance from " + personLastname + " " + personName
						+ " with dni " + dni;
		}

		return ret;
	}

	public String qrDecodeFailed() {
		String ret = null;

		switch (this.getMessenger().getLang()) {
			case "EN":
				ret = "an error occurred while trying to decode the QR";
				break;
			case "ES":
				ret = "un error ha ocurrido al intentar decodificar el código QR";
				break;
			default:
				ret = "an error occurred while trying to decode the QR";
		}

		return ret;
	}

	public String qrGenerationFailed() {
		String ret = null;

		switch (this.getMessenger().getLang()) {
			case "EN":
				ret = "an error occurred while trying to generate the QR";
				break;
			case "ES":
				ret = "un error ha ocurrido al intentar generar el código QR";
				break;
			default:
				ret = "an error occurred while trying to generate the QR";
		}

		return ret;
	}
}
