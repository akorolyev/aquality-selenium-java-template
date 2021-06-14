package aquality.selenium.template.test;

import aquality.selenium.browser.AqualityServices;

import aquality.selenium.template.configurations.Configuration;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public abstract class BaseTest {


    private int stepNumber;

    @BeforeClass(alwaysRun = true)
    public void beforeMethod() {
        stepNumber = 1;
        AqualityServices.getBrowser().maximize();
        AqualityServices.getBrowser().goTo(Configuration.getStartUrl());
        AqualityServices.getBrowser().waitForPageToLoad();

    }

    @AfterClass(alwaysRun = true)
    public void afterTest() {
        if (AqualityServices.isBrowserStarted()) {
            AqualityServices.getBrowser().quit();
        }
    }


    protected void logStep(String message) {
        String format = "Step %d: %s";
        AqualityServices.getLogger().info(String.format(format, stepNumber, message));
        stepNumber++;
    }

}