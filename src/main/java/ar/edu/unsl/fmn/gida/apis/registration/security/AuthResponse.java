package ar.edu.unsl.fmn.gida.apis.registration.security;

public class AuthResponse {

	private String id;
	private String token;

	public AuthResponse() {}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getToken() {
		return this.token;
	}

	public void setToken(String token) {
		this.token = token;
	}
}
