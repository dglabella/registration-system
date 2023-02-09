package ar.edu.unsl.fmn.gida.apis.registration.messengers.controllers;

import ar.edu.unsl.fmn.gida.apis.registration.messengers.Messenger;

public abstract class ControllerMessenger {

	private Messenger messages;

	public ControllerMessenger(Messenger messages) {
		this.messages = messages;
	}

	public Messenger getMessages() {
		return this.messages;
	}

	public String operationNotImplementedYet(String operationName, String entityName) {
		String ret = null;

		switch (this.messages.getLang()) {
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
