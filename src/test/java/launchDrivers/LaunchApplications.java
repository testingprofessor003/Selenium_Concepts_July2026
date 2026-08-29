package launchDrivers;

import framework.BrowserUtils;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;
import framework.constants.BrowserTypes;
import framework.SeleniumUtils;

public class LaunchApplications {

    @Test(description = "Launching Applications",priority = 1)
    public void launchApplications() {

        //From selenium 4.12 onwards, there is inbuilt manager to manage the browsers that is selenium manager
        //WebDriver driver = new ChromeDriver();
        WebDriver driver = BrowserUtils.getDriver(BrowserTypes.CHROME);

        // Maximizing the window
        // driver.manage().window().maximize();

        // Launching Google Application
        // driver.get("https://www.google.com");

        SeleniumUtils seleniumUtils = new SeleniumUtils(driver,null,null);
        seleniumUtils.launchApplication("https://www.google.com");
    }
}
