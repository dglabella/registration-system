package ar.edu.unsl.fmn.gida.apis.registration.utils.validators;

import ar.edu.unsl.fmn.gida.apis.registration.model.User;

public class UserValidator implements Validator<User> {

    private String message;

    @Override
    public boolean validate(User entity) {
        // if (entity.getPersonName() != null && entity.getPersonName().is)
        return false;
    }

    @Override
    public String getMessage() {
        // TODO Auto-generated method stub
        return null;
    }

}
