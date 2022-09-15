package ar.edu.unsl.fmn.gida.apis.registration.utils.validators;

public interface Validator<T> {

    /**
     * this method will validate the entity.
     * 
     * @param entity the entity to be validated.
     * @return false if some of the fields are incorrect.
     */
    boolean validate(T entity);

    /**
     * gets the validation message.
     * 
     * @return the message containing the specific information about validation.
     */
    String getMessage();
}
