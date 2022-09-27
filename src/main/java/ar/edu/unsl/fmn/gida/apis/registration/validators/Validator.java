package ar.edu.unsl.fmn.gida.apis.registration.validators;

@FunctionalInterface
public interface Validator<T> {
    /**
     * this method will validate the entity.
     * 
     * @param entity the entity to be validated.
     */
    void validate(T entity);
}
