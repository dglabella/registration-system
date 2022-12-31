package ar.edu.unsl.fmn.gida.apis.registration.services.validators;

import ar.edu.unsl.fmn.gida.apis.registration.model.User;
import ar.edu.unsl.fmn.gida.apis.registration.model.constraints.Constraints;

public class UserValidator extends Validator<User> {

    public UserValidator(ExpressionValidator expressionValidator) {
        super(expressionValidator);
    }

    @Override
    public void validate(User entity) {
        /**
         * check nullability
         */
        if (entity.getId() != null) {
            this.sendError("cannot specify id");
        }

        if (!(this.getExpressionValidator().isPresent(entity.getUserName())
                || Constraints.User.NAME_NULLABLE))
            this.sendError("user name is required");


        if (!(this.getExpressionValidator().isPresent(entity.getUserLastName())
                || Constraints.User.LAST_NAME_NULLABLE))
            this.sendError("user last name is required");

        if (!(this.getExpressionValidator().isPresent(entity.getDni())
                || Constraints.User.DNI_NULLABLE))
            this.sendError("user dni is required");

        if (!(this.getExpressionValidator().isPresent(entity.getEmail())
                || Constraints.User.EMAIL_NULLABLE))
            this.sendError("user email is required");

        if (!(this.getExpressionValidator().isPresent(entity.getAccount())
                || Constraints.User.ACCOUNT_NULLABLE))
            this.sendError("user account is required");

        if (!(this.getExpressionValidator().isPresent(entity.getPassword())
                || Constraints.User.PASSWORD_NULLABLE))
            this.sendError("user password is required");

        /**
         * check size
         */
        if (!(Constraints.User.NAME_MIN_LENGHT <= entity.getUserName().length()
                && entity.getUserName().length() <= Constraints.User.NAME_MAX_LENGHT))
            this.sendError(
                    "invalid user name: must contain between " + Constraints.User.NAME_MIN_LENGHT
                            + " and " + Constraints.User.NAME_MAX_LENGHT + " characters");


        if (!(Constraints.User.LAST_NAME_MIN_LENGHT <= entity.getUserLastName().length()
                && entity.getUserLastName().length() <= Constraints.User.LAST_NAME_MAX_LENGHT))
            this.sendError("invalid user last name: must contain between "
                    + Constraints.User.LAST_NAME_MIN_LENGHT + " and "
                    + Constraints.User.LAST_NAME_MAX_LENGHT + " characters");

        if (!(Constraints.User.DNI_MIN_LENGHT <= entity.getDni().length()
                && entity.getDni().length() <= Constraints.User.DNI_MAX_LENGHT))
            this.sendError(
                    "invalid user dni: must contain between " + Constraints.User.DNI_MIN_LENGHT
                            + " and " + Constraints.User.DNI_MAX_LENGHT + " characters");

        if (!(Constraints.User.EMAIL_MIN_LENGHT <= entity.getEmail().length()
                && entity.getEmail().length() <= Constraints.User.EMAIL_MAX_LENGHT))
            this.sendError(
                    "invalid user email: must contain between " + Constraints.User.EMAIL_MIN_LENGHT
                            + " and " + Constraints.User.EMAIL_MAX_LENGHT + " characters");


        if (!(Constraints.User.ACCOUNT_MIN_LENGHT <= entity.getAccount().length()
                && entity.getAccount().length() <= Constraints.User.ACCOUNT_MAX_LENGHT))
            this.sendError("invalid user account: must contain between "
                    + Constraints.User.ACCOUNT_MIN_LENGHT + " and "
                    + Constraints.User.ACCOUNT_MAX_LENGHT + " characters");

        if (!(Constraints.User.PASSWORD_MIN_LENGHT <= entity.getPassword().length()
                && entity.getPassword().length() <= Constraints.User.PASSWORD_MAX_LENGHT))
            this.sendError("invalid user password: must contain between "
                    + Constraints.User.PASSWORD_MIN_LENGHT + " and "
                    + Constraints.User.PASSWORD_MAX_LENGHT + " characters");

        /**
         * check pattern
         */
        if (!this.getExpressionValidator().composedName(entity.getUserName()))
            this.sendError("invalid user name: must have only valids chararcters");

        if (!this.getExpressionValidator().composedName(entity.getUserLastName()))
            this.sendError("invalid user last name: must have only valids chararcters");

        if (!this.getExpressionValidator().onlyNumbers(entity.getDni()))
            this.sendError("invalid user dni: only numbers allowed");

        if (!this.getExpressionValidator().isEmail(entity.getEmail()))
            this.sendError("invalid user email: must be a valid email (anything@example.com)");

        if (!this.getExpressionValidator().isAccountValid(entity.getAccount()))
            this.sendError("invalid user account: must follow the next specificatios: "
                    + "1- Begin with a letter (uppercase or lowercase) or a digit. "
                    + "2- Ends with a letter (uppercase or lowercase) or a digit. "
                    + "3- can contain letters (uppercase or lowercase) or digits. "
                    + "4- can contain: .(dot), -(hyphen), _(underscore). "
                    + "5- cannot contain .(dot), -(hyphen), _(underscore) one after the other.");

        if (!this.getExpressionValidator().isPasswordValid(entity.getPassword()))
            this.sendError("invalid user passowrd: must follow the next specificatios: "
                    + "the string must contain only alphanumeric characters.");
    }


}
