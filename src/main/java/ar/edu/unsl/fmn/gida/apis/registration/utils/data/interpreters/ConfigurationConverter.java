package ar.edu.unsl.fmn.gida.apis.registration.utils.data.interpreters;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import ar.edu.unsl.fmn.gida.apis.registration.exceptions.ConversionException;

public class ConfigurationConverter implements Converter<Map<String, String>> {

	private final String TAG_BEGIN = "[begin]";
	private final String TAG_SEPARATOR = "[--]";
	private final String TAG_END = "[end]";

	private final String PREFIX_NAME_PROP = "name";
	private final String PREFIX_VALUE_PROP = "value";
	private final String ASSIGN_OP = "::";

	private String rawData = TAG_BEGIN + "\n";

	private final int PREFIX = 0;
	private final int VALUE = 1;

	public ConfigurationConverter() {}

	@Override
	public Map<String, String> objectify(String data) {
		Map<String, String> ret = new HashMap<>();

		String[] nameProp = new String[2];
		String[] valueProp = new String[2];
		try (Scanner scanner = new Scanner(data)) {
			String line = null;

			boolean endTagReached = false;

			// check begin tag
			if (scanner.hasNextLine()) {
				line = scanner.nextLine();
				if (!line.equals(this.TAG_BEGIN)) {
					throw new ConversionException(
							"file \"configs.CONF\": " + this.TAG_BEGIN + " tag is missing");
				}
			}

			// gather configs
			while (scanner.hasNextLine() && !endTagReached) {
				line = scanner.nextLine(); // should be "name" property

				if (!line.equals(this.TAG_END)) {

					nameProp = line.split(this.ASSIGN_OP);
					if (nameProp[this.PREFIX] == null
							|| !nameProp[this.PREFIX].equals(this.PREFIX_NAME_PROP))
						throw new ConversionException("file \"configs.CONF\": \""
								+ this.PREFIX_NAME_PROP
								+ "\" prefix expected. It needs to be explicit. Maybe the assign operator ( "
								+ this.ASSIGN_OP + " ) is missing or in a wrong place");

					line = scanner.nextLine(); // should be "value" property
					valueProp = line.split(this.ASSIGN_OP);
					if (valueProp[this.PREFIX] == null
							|| !valueProp[this.PREFIX].equals(this.PREFIX_VALUE_PROP))
						throw new ConversionException("file \"configs.CONF\": \""
								+ this.PREFIX_VALUE_PROP
								+ "\" prefix expected. It needs to be explicit. Maybe the assign operator ( "
								+ this.ASSIGN_OP + " ) is missing or in a wrong place");

					line = scanner.nextLine(); // should be SEPARATOR_TAG or TAG_END
					if (line == null
							|| (!line.equals(this.TAG_SEPARATOR) && !line.equals(this.TAG_END)))
						throw new ConversionException("file \"configs.CONF\": \""
								+ this.TAG_SEPARATOR + "\" tag is missing (separator tag)");

					// All is ok, then
					ret.put(nameProp[this.VALUE], valueProp[this.VALUE]);
				} else {
					endTagReached = true;
				}
			}
		}
		return ret;
	}

	@Override
	public String stringify(Map<String, String> object) {

		List<String[]> configLines = new ArrayList<String[]>();
		object.forEach((k, v) -> configLines.add(new String[] {k, v}));
		int last = configLines.size() - 1;

		for (int i = 0; i < configLines.size() - 1; i++) {
			this.rawData += this.PREFIX_NAME_PROP + this.ASSIGN_OP + configLines.get(i)[0] + "\n";
			this.rawData += this.PREFIX_VALUE_PROP + this.ASSIGN_OP + configLines.get(i)[1] + "\n";
			this.rawData += this.TAG_SEPARATOR + "\n";
		}

		this.rawData += this.PREFIX_NAME_PROP + this.ASSIGN_OP + configLines.get(last)[0] + "\n";
		this.rawData += this.PREFIX_VALUE_PROP + this.ASSIGN_OP + configLines.get(last)[1] + "\n";
		this.rawData += this.TAG_END + "\n";

		return this.rawData;
	}
}
