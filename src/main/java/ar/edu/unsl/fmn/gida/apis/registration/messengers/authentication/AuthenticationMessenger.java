package ar.edu.unsl.fmn.gida.apis.registration.messengers.authentication;

import ar.edu.unsl.fmn.gida.apis.registration.messengers.Messenger;

public class AuthenticationMessenger {

	private Messenger messages;

	public AuthenticationMessenger(Messenger messages) {
		this.messages = messages;
	}

	public Messenger getMessages() {
		return this.messages;
	}

	public String invalidToken() {
		String ret = null;

		switch (this.messages.getLang()) {
			case "EN":
				ret = "The tokes is invalid: maybe is expired or is incorrect";
				break;
			case "ES":
				ret = "El token es invalido: quizás ha expirado o es incorrecto";
				break;
			default:
				ret = "The tokes is invalid: maybe is expired or is incorrect";
		}

		return ret;
	}

	public String errorReadingCredentials() {
		String ret = null;

		switch (this.messages.getLang()) {
			case "EN":
				ret = "something wrong with the request. Maybe the json sent is incorrect";
				break;
			case "ES":
				ret = "algo esta mal con la petición. Quizás el json enviado es incorrecto";
				break;
			default:
				ret = "something wrong with the request. Maybe the json sent is incorrect";
		}

		return ret;
	}
}
