package ar.edu.unsl.fmn.gida.apis.registration.utils.files;

import ar.edu.unsl.fmn.gida.apis.registration.exceptions.FileInterpreterException;

public interface FileInterpreter {
    /**
     * Interpret a file with configurations.
     * 
     * @throws FileInterpreterException
     * @throws FileNotFoundException
     */
    void interpret() throws FileInterpreterException;
}
