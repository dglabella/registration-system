package ar.edu.unsl.fmn.gida.apis.registration.validators;

public interface ExpressionValidator {

    boolean isPresent(String string);

    boolean onlyNumbers(String string);

    boolean moneyValue(String string, int leftDigits, int rightDigits);

    boolean moneyValueWithNegative(String string, int leftDigits, int rightDigits);

    boolean onlyChars(String string);

    boolean onlyCharsWithWhiteSpace(String string);

    boolean isEmail(String string);

    boolean composedName(String string);

    /**
     * Checks if a string follows the next specificatios: 1- Begin with a letter (uppercase or
     * lowercase) or a digit. 2- Ends with a letter (uppercase or lowercase) or a digit. 3- can
     * contain letters (uppercase or lowercase) or digits. 4- can contain: .(dot), -(hyphen),
     * _(underscore). 5- cannot contain .(dot), -(hyphen), _(underscore) one after the other.
     * 
     * @param string The string to check.
     * @return true if match the specifictions.
     */
    boolean isAccountValid(String string);

    /**
     * Checks if a string follows the next specifications: The string must contain only alphanumeric
     * characters.
     * 
     * @param string The string to check.
     * @return true if match the specifictions.
     */
    boolean isPasswordValid(String string);

    /**
     * Checks if a string follows a datetime pattern. Example: 2022-10-14T17:27:55.6850
     * (yyyy-MM-dd'T'HH:mm:ss.SSSX format)
     *
     * @param string The string to check.
     * @return true if match the specifications.
     */
    boolean isDatetime(String string);
}
