package ar.edu.unsl.fmn.gida.apis.registration.utils.configs;

import java.util.HashMap;
import java.util.Map;

public class Configuration {

    private Map<String, Configurable> configs;

    public Configuration() {
        this.configs = new HashMap<>();
    }

    public Configurable getConfig(String name) {
        return this.configs.get(name);
    }

    public void add(Configurable config) {
        this.configs.put(config.getName(), config);
    }
}
