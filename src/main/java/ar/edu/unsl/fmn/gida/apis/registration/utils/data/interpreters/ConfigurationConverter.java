package ar.edu.unsl.fmn.gida.apis.registration.utils.data.interpreters;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import ar.edu.unsl.fmn.gida.apis.registration.exceptions.ConvertionException;
import ar.edu.unsl.fmn.gida.apis.registration.utils.configs.Configuration;
import ar.edu.unsl.fmn.gida.apis.registration.utils.configs.LangConfig;
import ar.edu.unsl.fmn.gida.apis.registration.utils.configs.LogFileConfig;

public class ConfigurationConverter implements Converter<Configuration> {

    private final String TAG_BEGIN = "[begin]";
    private final String TAG_SEPARATOR = "[--]";
    private final String TAG_END = "[end]";
    private final String SPLITTER = "::";

    private final int PREFIX = 0;
    private final int VALUE = 1;

    public ConfigurationConverter() {}

    private Map<String, String> readConfigurations(String data) {
        Map<String, String> ret = new HashMap<>();

        String[] nameLine = new String[2];
        String[] valueLine = new String[2];
        Scanner scanner = new Scanner(data);
        String line1 = null;
        String line2 = null;
        String line3 = null;

        boolean endTagReached = false;

        // check begin tag
        if (scanner.hasNextLine()) {
            line1 = scanner.nextLine();
            if (!line1.equals(this.TAG_BEGIN)) {
                throw new ConvertionException("file \"configs.CONF\": [BEGIN] tag is missing");
            }
        }

        // gather configs
        while (scanner.hasNextLine() && !endTagReached) {
            line1 = scanner.nextLine();

            if (!line1.equals(this.TAG_END)) {

                nameLine = line1.split(this.SPLITTER);
                if (nameLine[PREFIX] != null && nameLine[PREFIX] != null)

                    line2 = scanner.nextLine();
                valueLine = line2.split(this.SPLITTER);

                if (!nameLine[PREFIX].equals("name") || valueLine[PREFIX].equals("value")) {
                    throw new ConvertionException(
                            "file \"configs.CONF\": configs must have a first a property called \"name\", and then, "
                                    + "a propert called \"value\". Also, the assign operator must be \"::\"");
                } else {
                    // ret.put(nameLine, line);
                }
            } else {
                endTagReached = true;
            }
        }

        return ret;
    }

    @Override
    public Configuration objectify(String data) throws ConvertionException {
        Configuration configuration = new Configuration();
        Map<String, String> rawConfiguration = this.readConfigurations(data);

        configuration
                .add(new LogFileConfig("log-file-dir", System.getProperty("user.dir") + "\\log"));
        configuration.add(new LangConfig("lang", "ES"));

        configuration.getConfig("log-file-dir").setValue(rawConfiguration.get("log-file-dir"));
        configuration.getConfig("lang").setValue(rawConfiguration.get("lang"));

        return configuration;
    }

    @Override
    public String stringify(Configuration object) throws ConvertionException {
        return null;
    }
}
