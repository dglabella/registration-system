package ar.edu.unsl.fmn.gida.apis.registration.validators;

import org.springframework.http.HttpStatus;
import ar.edu.unsl.fmn.gida.apis.registration.exceptions.ErrorResponse;
import ar.edu.unsl.fmn.gida.apis.registration.model.Person;

public class PersonValidator implements Validator<Person> {
    private DependencyValidator dependencyValidator = new DependencyValidator();
    private WeeklyValidator weeklyValidator = new WeeklyValidator();

    public PersonValidator() {
        this.dependencyValidator = new DependencyValidator();
    }

    @Override
    public void validate(Person entity) {



        this.dependencyValidator.validate(entity.getDependency());
    }
}
