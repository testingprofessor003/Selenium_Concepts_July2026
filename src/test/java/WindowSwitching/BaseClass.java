package WindowSwitching;

import framework.ElementUtils;
import framework.WaitManager;
import org.testng.annotations.BeforeClass;
import org.openqa.selenium.WebDriver;
import framework.SeleniumUtils;
import framework.BrowserUtils;
import framework.constants.BrowserTypes;
import org.testng.annotations.BeforeMethod;

public class BaseClass {
    
    protected WebDriver driver;
    protected SeleniumUtils seleniumUtils;
    protected ElementUtils elementUtils;
    protected WaitManager waitManager;

    @BeforeClass
    public void setupOfFrameworkObject()
    {
        driver = BrowserUtils.getDriver(BrowserTypes.CHROME);
        elementUtils = new ElementUtils(driver);
        seleniumUtils = new SeleniumUtils(driver,elementUtils);
        waitManager = new WaitManager(driver);
    }

    @BeforeMethod
    public void beforeMethod()
    {
        if(seleniumUtils.getRequiredInformationFromDriver("URL").contains("data"))
        seleniumUtils.launchApplication("https://seleniumsessions.testingprofessor.net/");

    }
}
