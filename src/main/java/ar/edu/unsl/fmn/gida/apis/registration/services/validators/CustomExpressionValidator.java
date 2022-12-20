package ar.edu.unsl.fmn.gida.apis.registration.services.validators;

import java.util.regex.Pattern;

public class CustomExpressionValidator implements ExpressionValidator {

    private Pattern pattern;

    public CustomExpressionValidator() {}

    public boolean isPresent(String string) {
        return string != null && !string.trim().isEmpty();
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

    public boolean isAccountValid(String string) {
        pattern = Pattern.compile("^([a-zA-Z0-9]+[_.-]?)*[a-zA-Z0-9]+$");
        return pattern.matcher(string).matches();
    }

    public boolean isPasswordValid(String string) {
        pattern = Pattern.compile("^[a-zA-Z0-9]+$");
        return pattern.matcher(string).matches();
    }

    @Override
    public boolean isDatetime(String string) {
        pattern = Pattern.compile("^\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}:\\d{2}\\.\\d{4}$");
        return pattern.matcher(string).matches();
    }
}
