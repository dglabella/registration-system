package ar.edu.unsl.fmn.gida.apis.registration.utils.configs;

public abstract class Configurable {

    private final int PREFIX = 0;
    private final int VALUE = 1;

    private final String defaultValue;

    private String name;
    private String value;

    private Configuration configuration;

    public Configurable(String name, String defaultValue) {
        this.name = name;
        this.defaultValue = defaultValue;
    }

    public String getName() {
        return this.getName();
    }

    public String getValue() {
        return this.value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getDefaultValue() {
        return this.defaultValue;
    }
}
