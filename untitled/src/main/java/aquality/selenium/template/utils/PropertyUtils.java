package aquality.selenium.template.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

public class PropertyUtils {
    private static final Properties prop = new Properties();
    private static final String config = "src/main/resources/config.properties";
    private static final String data = "src/main/resources/data.properties";
    public static String readFromFrameworkConfig(String key) {
        try {
            InputStream input = new FileInputStream(config);
            prop.load(new InputStreamReader(input, StandardCharsets.UTF_8));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return prop.getProperty(key);
    }

    public static String readFromDataConfig(String key) {
        try {
            InputStream input = new FileInputStream(data);
            prop.load(new InputStreamReader(input, StandardCharsets.UTF_8));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return prop.getProperty(key);
    }
}
