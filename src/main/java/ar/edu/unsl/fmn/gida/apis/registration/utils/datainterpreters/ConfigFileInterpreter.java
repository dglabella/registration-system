package ar.edu.unsl.fmn.gida.apis.registration.utils.datainterpreters;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;
import ar.edu.unsl.fmn.gida.apis.registration.exceptions.InterpretationException;
import ar.edu.unsl.fmn.gida.apis.registration.utils.Configuration;

public class ConfigFileInterpreter implements Interpreter<Configuration> {

    private static final String SEPARATOR = "::";
    private static final int PREFIX_VALUE = 0;
    private static final int CONFIG_VALUE = 1;

    private static final String PREFIX_LOG_FILE_DIR = "log_file_dir";

    private static final String defaultContent = PREFIX_LOG_FILE_DIR + SEPARATOR
            + "C:\\Users\\dglab\\OneDrive\\Escritorio\\jacksonTest\\log-file.txt\\n";


    private File file;

    public ConfigFileInterpreter(String fileLocation) {
        this.file = new File(fileLocation);

        try {
            if (this.file.createNewFile()) {
                System.out.println(
                        "config file not found. A new one will be created with default content...");

                // if (!this.file.canRead())
                // file.setReadable(true);

                // write the file with default content
                FileOutputStream outputStream = new FileOutputStream(this.file);
                byte[] defaultContentToBytes = defaultContent.getBytes();
                outputStream.write(defaultContentToBytes);
                outputStream.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void setUpByPrefix(String[] configFileLine) {
        switch (configFileLine[PREFIX_VALUE]) {
            case PREFIX_LOG_FILE_DIR:
                this.readLogFile(configFileLine[CONFIG_VALUE]);
                break;
            default:
                System.out.println("prefix no defined.");
                break;
        }
    }

    private void readLogFile(String fileDir) {
        File logFile = new File(fileDir);
        try {
            if (logFile.createNewFile()) {
                System.out.println(
                        "log file was not found. A new one will be created with default content...");

                // if (!this.file.canRead())
                // file.setReadable(true);

                // write the file with default content
                FileOutputStream outputStream = new FileOutputStream(logFile, true);
                byte[] defaultContentToBytes = defaultContent.getBytes();
                outputStream.write(defaultContentToBytes);
                outputStream.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Configuration interpret(String data) throws InterpretationException {
        Scanner scanner;
        try {
            scanner = new Scanner(file);
            // scanner.useDelimiter(SEPARATOR);
            while (scanner.hasNext()) {
                this.setUpByPrefix(scanner.next().split(scanner.next()));
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return null;
    }
}
