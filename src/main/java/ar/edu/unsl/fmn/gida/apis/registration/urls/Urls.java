package ar.edu.unsl.fmn.gida.apis.registration.urls;

public interface Urls {

	String persons = "/persons";
	String registers = "/registers";
	String accesses = "/accesses";
	String dependencies = "/dependencies";
	String weeklies = "/weeklies";
	String users = "/users";
	String credentials = "/credentials";

	String authentication = "/login";
	String signin = "/signin";

	interface Privileges {
		String pub = "pub";
		String user = "user";
		String responsible = "responsible";
		String admin = "admin";
	}
}
