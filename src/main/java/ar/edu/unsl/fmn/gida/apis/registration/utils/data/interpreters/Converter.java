package ar.edu.unsl.fmn.gida.apis.registration.utils.data.interpreters;

public interface Converter<T> {
	/**
	 * converts string data to an object representation using the underlying implementation.
	 * 
	 * @param data the data to be converted.
	 * @return the object obtained from the converting process.
	 */
	T objectify(String data);

	/**
	 * converts an object to a string using the underlying implementation.
	 * 
	 * @param object the object to be converted
	 * @return the string obtained from the converting process.
	 */
	String stringify(T object);
}
