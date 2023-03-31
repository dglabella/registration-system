package ar.edu.unsl.fmn.gida.apis.registration.urls;

public interface Urls {

	String persons = "/persons";
	String registers = "/registers";
	String accesses = "/accesses";
	String dependencies = "/dependencies";
	String weeklies = "/weeklies";
	String workAttendances = "/workattendances";
	String users = "/users";
	String credentials = "/credentials";

	String authentication = "/login";
	String signup = "/signup";

	interface Privileges {
		String pub = "pub";
		String user = "user";
		String responsible = "responsible";
		String admin = "admin";
	}
}
