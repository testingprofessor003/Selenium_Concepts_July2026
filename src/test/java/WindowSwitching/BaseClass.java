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
    protected JavascriptUtils javascriptUtils;
    protected PathUtils pathutils;

    @BeforeClass
    public void setupOfFrameworkObject()
    {
        driver = BrowserUtils.getDriver(BrowserTypes.CHROME);
        elementUtils = new ElementUtils(driver);
        seleniumUtils = new SeleniumUtils(driver,elementUtils);
        waitManager = new WaitManager(driver);
        reports = new Reports(driver);
        javascriptUtils = new JavascriptUtils(driver);

    }

    @BeforeMethod
    public void beforeMethod()
    {
        if(seleniumUtils.getRequiredInformationFromDriver("URL").contains("data"))
        seleniumUtils.launchApplication("https://seleniumsessions.testingprofessor.net/");
    }

    //Xpath for finding the players:
    //h1[text()='playing XI']/following-sibling::div//a/div/div/div[contains(@class,'items-center') and contains(@class,'justify-start')]/span[not(@class)]
}
