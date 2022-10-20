package ar.edu.unsl.fmn.gida.apis.registration.utils.datainterpreters;

import ar.edu.unsl.fmn.gida.apis.registration.exceptions.InterpretationException;

@FunctionalInterface
public interface Interpreter<T> {
    /**
     * Interpret some data using the underlying implementation.
     * 
     * @param data the data to be interpreted.
     * @return the object obtained from the intepretation process.
     * @throws InterpretationException
     */
    T interpret(String data) throws InterpretationException;
}
