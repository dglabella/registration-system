package ar.edu.unsl.fmn.gida.apis.registration.validators;

import java.util.regex.Pattern;

public class CustomExpressionValidator {

    private Pattern pattern;

    public CustomExpressionValidator() {}

    public boolean isPresent(String string) {
        return string != null && !string.isBlank();
    }

    public boolean onlyNumbers(String string) {
        pattern = Pattern.compile("[\\d]*");
        return pattern.matcher(string).matches();
    }

    public boolean moneyValue(String string, int leftDigits, int rightDigits) {
        pattern = Pattern.compile("\\d{0," + leftDigits + "}(\\.\\d{1," + rightDigits + "})?");
        return pattern.matcher(string).matches();
    }

    public boolean moneyValueWithNegative(String string, int leftDigits, int rightDigits) {
        pattern = Pattern.compile("(-)?\\d{0," + leftDigits + "}(\\.\\d{1," + rightDigits + "})?");
        return pattern.matcher(string).matches();
    }

    public boolean onlyChars(String string) {
        pattern = Pattern.compile("[a-zA-Z]*");
        return pattern.matcher(string).matches();
    }

    public boolean onlyCharsWithWhiteSpace(String string) {
        pattern = Pattern.compile("[a-zA-Z]*[\\s[a-zA-Z]]*");
        return pattern.matcher(string).matches();
    }

    public boolean isEmail(String string) {
        pattern = Pattern.compile(
                "(^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$|^$)?");

        return pattern.matcher(string).matches();
    }

    public boolean composedName(String string) {
        pattern = Pattern.compile(
                "^[a-zA-ZÀ-ÿ\\u00f1\\u00d1]+(\\s*[a-zA-ZÀ-ÿ\\u00f1\\u00d1]*)*[a-zA-ZÀ-ÿ\\u00f1\\u00d1]+$");

        return pattern.matcher(string).matches();
    }

    /**
     * Checks if a string follows the next specificatios: 1- Begin with a letter (uppercase or
     * lowercase) or a digit. 2- Ends with a letter (uppercase or lowercase) or a digit. 3- can
     * contain letters (uppercase or lowercase) or digits. 4- can contain: .(dot), -(hyphen),
     * _(underscore). 5- cannot contain .(dot), -(hyphen), _(underscore) one after the other.
     * 
     * @param string The string to check.
     * @return true if match the specifictions.
     */
    public boolean isAccountValid(String string) {
        pattern = Pattern.compile("^([a-zA-Z0-9]+[_.-]?)*[a-zA-Z0-9]+$");

        return pattern.matcher(string).matches();
    }

    /**
     * Checks if a string follows the next specifications: The string must contain only alphanumeric
     * characters.
     * 
     * @param string The string to check.
     * @return true if match the specifictions.
     */
    public boolean isPasswordValid(String string) {
        pattern = Pattern.compile("^[a-zA-Z0-9]+$");

        return pattern.matcher(string).matches();
    }
}
