package ar.edu.unsl.fmn.gida.apis.registration.messengers.controllers;

import ar.edu.unsl.fmn.gida.apis.registration.messengers.Messenger;

public abstract class ControllerMessenger {

	private Messenger messenger;

	public ControllerMessenger(Messenger messenger) {
		this.messenger = messenger;
	}

	public Messenger getMessenger() {
		return this.messenger;
	}

	public String operationNotImplementedYet(String operationName, String entityName) {
		String ret = null;

		switch (this.messenger.getLang()) {
			case "EN":
				ret = operationName + " " + entityName + " operation not implemented yet";
				break;
			case "ES":
				ret = operationName + " " + entityName
						+ " es una operación no implementada todavía";
				break;
			default:
				ret = operationName + " " + entityName + " operation not implemented yet";
		}
		return ret;
	}
}
