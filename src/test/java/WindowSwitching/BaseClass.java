package WindowSwitching;

import framework.*;
import org.testng.annotations.BeforeClass;
import org.openqa.selenium.WebDriver;
import framework.constants.BrowserTypes;
import org.testng.annotations.BeforeMethod;

public class BaseClass {
    
    protected WebDriver driver;
    protected SeleniumUtils seleniumUtils;
    protected ElementUtils elementUtils;
    protected WaitManager waitManager;
    protected Reports reports;

    @BeforeClass
    public void setupOfFrameworkObject()
    {
        driver = BrowserUtils.getDriver(BrowserTypes.CHROME);
        elementUtils = new ElementUtils(driver);
        seleniumUtils = new SeleniumUtils(driver,elementUtils);
        waitManager = new WaitManager(driver);
        reports = new Reports(driver);
    }

    @BeforeMethod
    public void beforeMethod()
    {
        if(seleniumUtils.getRequiredInformationFromDriver("URL").contains("data"))
        seleniumUtils.launchApplication("https://www.cricbuzz.com/");

    }
}
