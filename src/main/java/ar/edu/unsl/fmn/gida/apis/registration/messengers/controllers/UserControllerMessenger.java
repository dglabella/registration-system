package ar.edu.unsl.fmn.gida.apis.registration.messengers.controllers;

import ar.edu.unsl.fmn.gida.apis.registration.messengers.Messenger;

public class UserControllerMessenger extends ControllerMessenger {

	public UserControllerMessenger(Messenger messenger) {
		super(messenger);
	}

	public String userPrivilegeIntegrityCorruption(String account) {
		String ret = null;

		switch (this.getMessenger().getLang()) {
			case "EN":
				ret = "the user " + account
						+ " has no privilege assigned. Data integrity corrupted or exist a failure loading privileges";
				break;
			case "ES":
				ret = "el usuario " + account
						+ " no tiene privilegio asignado. La integridad de los datos esta corrupta o existe una falla en la caraga de los privilegios";
				break;
			default:
				ret = "the user " + account
						+ " has no privilege assigned. Data integrity corrupted or exist a failure loading privileges";
		}

		return ret;
	}
}
