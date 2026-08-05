package WindowSwitching;

import org.testng.annotations.BeforeClass;
import org.openqa.selenium.WebDriver;
import framework.SeleniumUtils;
import framework.BrowserUtils;
import framework.constants.BrowserTypes;

public class BaseClass {
    
    protected WebDriver driver;
    protected SeleniumUtils seleniumUtils;

    @BeforeClass
    public void setupOfFrameworkObject()
    {
        driver = BrowserUtils.getDriver(BrowserTypes.CHROME);
        seleniumUtils = new SeleniumUtils(driver);
    }
}
