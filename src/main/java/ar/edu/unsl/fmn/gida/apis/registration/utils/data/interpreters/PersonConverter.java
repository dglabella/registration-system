package ar.edu.unsl.fmn.gida.apis.registration.utils.data.interpreters;

import ar.edu.unsl.fmn.gida.apis.registration.exceptions.ConvertionException;
import ar.edu.unsl.fmn.gida.apis.registration.model.Person;

@Deprecated
public class PersonConverter implements Converter<Person> {
	private final String SEPARATOR = "-";

	private final int ID = 0;
	private final int DNI = 1;
	private final int LAST_NAME = 2;
	private final int NAME = 3;
	// private final int DEPENDENCY = 4;


	@Override
	public Person objectify(String data) throws ConvertionException {
		Person person = new Person();
		String[] splitedData = data.split(SEPARATOR);

		person.setId(Integer.parseInt(splitedData[ID]));
		person.setDni(splitedData[DNI]);
		person.setPersonLastName(splitedData[LAST_NAME]);
		person.setPersonName(splitedData[NAME]);

		return person;
	}


	@Override
	public String stringify(Person object) throws ConvertionException {
		return object.getId() + SEPARATOR + object.getDni() + SEPARATOR + object.getPersonLastName()
				+ SEPARATOR + object.getPersonName();
	}
}
