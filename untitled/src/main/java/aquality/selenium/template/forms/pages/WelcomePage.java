package aquality.selenium.template.forms.pages;
import aquality.selenium.browser.AqualityServices;
import aquality.selenium.elements.interfaces.*;
import aquality.selenium.template.forms.BaseAppForm;

import org.openqa.selenium.By;


public class WelcomePage extends BaseAppForm {

    private IElementFactory elementFactory = AqualityServices.getElementFactory();
    private ILink linkHere = elementFactory.getLink(By.linkText("HERE"), "Here");

    public WelcomePage() {
        super(By.xpath("//div[@class='start view view--center']"), "Welcome page");
    }

    public SecondPage createSecondPageByClickHere(){
        this.linkHere.click();
        return new SecondPage();
    }
}
