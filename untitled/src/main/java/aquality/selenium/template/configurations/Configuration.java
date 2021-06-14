
package aquality.selenium.template.configurations;

public class Configuration {

    private Configuration() {
    }

    public static String getStartUrl() {
        return Environment.getCurrentEnvironment().getValue("/startUrl").toString();
    }
}