package ar.edu.unsl.fmn.gida.apis.registration.utils.data.interpreters;

import ar.edu.unsl.fmn.gida.apis.registration.exceptions.ConvertionException;

public interface Converter<T> {
	/**
	 * converts string data to an object representation using the underlying implementation.
	 * 
	 * @param data the data to be converted.
	 * @return the object obtained from the converting process.
	 * @throws ConvertionException
	 */
	T objectify(String data) throws ConvertionException;

	/**
	 * converts an object to a string using the underlying implementation.
	 * 
	 * @param object the object to be converted
	 * @return the string obtained from the converting process.
	 * @throws ConvertionException
	 */
	String stringify(T object) throws ConvertionException;
}
