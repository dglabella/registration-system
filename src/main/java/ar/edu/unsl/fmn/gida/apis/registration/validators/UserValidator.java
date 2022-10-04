package ar.edu.unsl.fmn.gida.apis.registration.validators;

import org.springframework.http.HttpStatus;
import ar.edu.unsl.fmn.gida.apis.registration.exceptions.ErrorResponse;
import ar.edu.unsl.fmn.gida.apis.registration.model.User;
import ar.edu.unsl.fmn.gida.apis.registration.model.constraints.Constraints;

public class UserValidator implements Validator<User> {

        private CustomExpressionValidator expresionValidator;

        public UserValidator(CustomExpressionValidator expresionValidator) {
                this.expresionValidator = expresionValidator;
        }

        private void validateClassFields(User user) {
                /**
                 * check nullability
                 */

                if (!(this.expresionValidator.isPresent(user.getUserName())
                                || Constraints.User.NAME_NULLABLE))
                        throw new ErrorResponse("user name is required", HttpStatus.BAD_REQUEST);

                if (!(this.expresionValidator.isPresent(user.getUserLastName())
                                || Constraints.User.LAST_NAME_NULLABLE))
                        throw new ErrorResponse("user last name is required",
                                        HttpStatus.BAD_REQUEST);

                if (!(this.expresionValidator.isPresent(user.getDni())
                                || Constraints.User.DNI_NULLABLE))
                        throw new ErrorResponse("user dni is required", HttpStatus.BAD_REQUEST);

                if (!(this.expresionValidator.isPresent(user.getEmail())
                                || Constraints.User.EMAIL_NULLABLE))
                        throw new ErrorResponse("user email is required", HttpStatus.BAD_REQUEST);

                if (!(this.expresionValidator.isPresent(user.getAccount())
                                || Constraints.User.ACCOUNT_NULLABLE))
                        throw new ErrorResponse("user account is required", HttpStatus.BAD_REQUEST);

                if (!(this.expresionValidator.isPresent(user.getPassword())
                                || Constraints.User.PASSWORD_NULLABLE))
                        throw new ErrorResponse("user password is required",
                                        HttpStatus.BAD_REQUEST);

                /**
                 * check size
                 */
                if (!(Constraints.User.NAME_MIN_LENGHT < user.getUserName().length()
                                && user.getUserName().length() < Constraints.User.NAME_MAX_LENGHT))
                        throw new ErrorResponse("invalid user name: must contain between "
                                        + Constraints.User.NAME_MIN_LENGHT + " and "
                                        + Constraints.User.NAME_MAX_LENGHT + " characters",
                                        HttpStatus.BAD_REQUEST);

                if (!(Constraints.User.LAST_NAME_MIN_LENGHT < user.getUserLastName().length()
                                && user.getUserLastName()
                                                .length() < Constraints.User.LAST_NAME_MAX_LENGHT))
                        throw new ErrorResponse("invalid user last name: must contain between "
                                        + Constraints.User.LAST_NAME_MIN_LENGHT + " and "
                                        + Constraints.User.LAST_NAME_MAX_LENGHT + " characters",
                                        HttpStatus.BAD_REQUEST);

                if (!(Constraints.User.DNI_MIN_LENGHT < user.getDni().length()
                                && user.getDni().length() < Constraints.User.DNI_MAX_LENGHT))
                        throw new ErrorResponse("invalid user dni: must contain between "
                                        + Constraints.User.DNI_MIN_LENGHT + " and "
                                        + Constraints.User.DNI_MAX_LENGHT + " characters",
                                        HttpStatus.BAD_REQUEST);

                if (!(Constraints.User.EMAIL_MIN_LENGHT < user.getEmail().length()
                                && user.getEmail().length() < Constraints.User.EMAIL_MAX_LENGHT))
                        throw new ErrorResponse("invalid user email: must contain between "
                                        + Constraints.User.EMAIL_MIN_LENGHT + " and "
                                        + Constraints.User.EMAIL_MAX_LENGHT + " characters",
                                        HttpStatus.BAD_REQUEST);


                if (!(Constraints.User.ACCOUNT_MIN_LENGHT < user.getAccount().length() && user
                                .getAccount().length() < Constraints.User.ACCOUNT_MAX_LENGHT))
                        throw new ErrorResponse("invalid user account: must contain between "
                                        + Constraints.User.ACCOUNT_MIN_LENGHT + " and "
                                        + Constraints.User.ACCOUNT_MAX_LENGHT + " characters",
                                        HttpStatus.BAD_REQUEST);

                if (!(Constraints.User.PASSWORD_MIN_LENGHT < user.getPassword().length() && user
                                .getPassword().length() < Constraints.User.PASSWORD_MAX_LENGHT))
                        throw new ErrorResponse("invalid user password: must contain between "
                                        + Constraints.User.PASSWORD_MIN_LENGHT + " and "
                                        + Constraints.User.PASSWORD_MAX_LENGHT + " characters",
                                        HttpStatus.BAD_REQUEST);

                /**
                 * check pattern
                 */
                if (!this.expresionValidator.composedName(user.getUserName()))
                        throw new ErrorResponse(
                                        "invalid user name: must have only valids chararcters",
                                        HttpStatus.BAD_REQUEST);

                if (!this.expresionValidator.composedName(user.getUserLastName()))
                        throw new ErrorResponse(
                                        "invalid user last name: must have only valids chararcters",
                                        HttpStatus.BAD_REQUEST);

                if (!this.expresionValidator.onlyNumbers(user.getDni()))
                        throw new ErrorResponse("invalid user dni: only numbers allowed",
                                        HttpStatus.BAD_REQUEST);

                if (!this.expresionValidator.isEmail(user.getEmail()))
                        throw new ErrorResponse(
                                        "invalid user email: must be a valid email (anything@example.com)",
                                        HttpStatus.BAD_REQUEST);

                if (!this.expresionValidator.isAccountValid(user.getAccount()))
                        throw new ErrorResponse(
                                        "invalid user account: must follow the next specificatios: "
                                                        + "1- Begin with a letter (uppercase or lowercase) or a digit. "
                                                        + "2- Ends with a letter (uppercase or lowercase) or a digit. "
                                                        + "3- can contain letters (uppercase or lowercase) or digits. "
                                                        + "4- can contain: .(dot), -(hyphen), _(underscore). "
                                                        + "5- cannot contain .(dot), -(hyphen), _(underscore) one after the other.",
                                        HttpStatus.BAD_REQUEST);

                if (!this.expresionValidator.isPasswordValid(user.getPassword()))
                        throw new ErrorResponse(
                                        "invalid user passowrd: must follow the next specificatios: "
                                                        + "the string must contain only alphanumeric characters.",
                                        HttpStatus.BAD_REQUEST);
        }

        @Override
        public void validate(User entity) {
                this.validateClassFields(entity);
        }
}
