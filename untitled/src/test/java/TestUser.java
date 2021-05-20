import aquality.selenium.browser.AqualityServices;
import aquality.selenium.elements.interfaces.*;
import aquality.selenium.template.forms.pages.SecondPage;
import aquality.selenium.template.forms.pages.WelcomePage;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Properties;


public class TestUser extends BaseTest {


    private static final String EXPECTED_URL_SECOND_PAGE = "https://userinyerface.com/game.html";
    private Properties properties = new Properties();
    private WelcomePage welcomePage;
    private IElementFactory elementFactory = AqualityServices.getElementFactory();



    @BeforeTest
    private void setUp() throws IOException {
        welcomePage = new WelcomePage();
        try (Reader reader = new FileReader("src/main/resources/config.properties")) {
            properties.load(reader);
        }
    }

    @Test
    public void testTreeCardsShouldBeOpened() throws InterruptedException {
        SoftAssert softAssert = new SoftAssert();
        SecondPage secondPage = welcomePage.createSecondPageByClickHere();
        softAssert.assertEquals(secondPage.getCardName().getText(), properties.getProperty("EXPECTED_NAME_FIRST_CARD"));
        secondPage.setEmail("Adrew");
        secondPage.setPassword("Aqua111111");
        secondPage.setDomain("gmail");
        secondPage.insertOrgCode();
        secondPage.getTerms().click();
        secondPage.getNextButton().click();
        softAssert.assertEquals(secondPage.getCardName().getText(), properties.getProperty("EXPECTED_NAME_SECOND_CARD"));
        secondPage.insertInterestChecks(3);
        secondPage.testUpload(properties.getProperty("imageName"));
        softAssert.assertEquals(secondPage.getCardName().getText(), properties.getProperty("EXPECTED_NAME_THIRD_CARD"));
    }

    @Test
    public void testHelpFormIsHidden() throws IOException{
        SoftAssert softAssert = new SoftAssert();
        SecondPage secondPage = welcomePage.createSecondPageByClickHere();
        softAssert.assertEquals(AqualityServices.getBrowser().getCurrentUrl(), EXPECTED_URL_SECOND_PAGE);
        secondPage.getHelpFormButton().click();
        secondPage.getHelpFormButton().state().waitForNotDisplayed();
        softAssert.assertFalse(secondPage.getHelpFormButton().state().isDisplayed());

    }

    @Test
    public void testAcceptedCookies() throws InterruptedException {
        SoftAssert softAssert = new SoftAssert();
        SecondPage secondPage = welcomePage.createSecondPageByClickHere();
        softAssert.assertEquals(AqualityServices.getBrowser().getCurrentUrl(), EXPECTED_URL_SECOND_PAGE);
        secondPage.getAcceptedCookies().state().waitForDisplayed();
        secondPage.getAcceptedCookies().click();
        softAssert.assertFalse(secondPage.getAcceptedCookies().state().isClickable());
    }

    @Test
    public void testTimerStartFromZero(){
        SoftAssert softAssert = new SoftAssert();
        SecondPage secondPage = welcomePage.createSecondPageByClickHere();
        softAssert.assertEquals(secondPage.getTimer().getText(), properties.getProperty("EXPECTED_TIMER_VALUE"));

    }
}