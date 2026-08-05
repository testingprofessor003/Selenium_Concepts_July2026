package framework;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.safari.SafariDriver;
import framework.constants.BrowserTypes;

public class BrowserUtils {

    public static WebDriver getDriver(BrowserTypes browserType) {
        return switch (browserType) {
            case CHROME -> new ChromeDriver();
            case FIREFOX -> new FirefoxDriver();
            case EDGE -> new EdgeDriver();
            case SAFARI -> new SafariDriver();
            default -> throw new IllegalArgumentException("Invalid browser type: " + browserType);
        };
    }
    
}
