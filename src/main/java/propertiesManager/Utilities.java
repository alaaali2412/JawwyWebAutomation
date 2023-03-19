package propertiesManager;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Utilities {
    public String getValuesFromPropertiesFile(String propertiesFileName, String value) {
        Properties properties = new Properties();
        FileInputStream file;
        try {
            file = new FileInputStream(System.getProperty("user.dir") + "/PropertiesFiles/" + propertiesFileName + ".properties");
            properties.load(file);
        } catch (IOException e) {
            throw new RuntimeException("Could not read properties from file: [" + propertiesFileName + "].", e);
        }
        return properties.getProperty(value);
    }
}
