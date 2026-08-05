package framework;

import framework.constants.BrowserTypes;
import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.safari.SafariOptions;

import java.net.URI;
import java.net.URL;

@UtilityClass
public class BrowserUtils {

    private WebDriver getDriver(String browserName)
    {
        return switch (browserName.toUpperCase())
        {
            case "CHROME" -> new ChromeDriver();
            case "FIREFOX" -> new FirefoxDriver();
            case "EDGE" -> new EdgeDriver();
            case "SAFARI" -> new SafariDriver();

            default -> throw new GenericExceptions(browserName+" is not a valid browser name");
        };
    }

    @SneakyThrows
    private WebDriver getRemoteDriver(String browserName)
    {
        return switch (browserName.toUpperCase())
        {
            case "CHROME" -> new RemoteWebDriver(URI.create("https://seleniumgrid.testingprofessor.net").toURL(), new ChromeOptions());
            case "FIREFOX" -> new RemoteWebDriver(URI.create("https://seleniumgrid.testingprofessor.net").toURL(), new FirefoxOptions());
            case "EDGE" -> new RemoteWebDriver(URI.create("https://seleniumgrid.testingprofessor.net").toURL(), new EdgeOptions());
            case "SAFARI" -> new RemoteWebDriver(URI.create("https://seleniumgrid.testingprofessor.net").toURL(), new SafariOptions());

            default -> throw new GenericExceptions(browserName+" is not a valid browser name");
        };
    }

    /**
     *
     * @param browserName --> We are passing the browser like chrome, firefox, safari etc..
     * @param remoteWebDriver --> We determine where do we want to launch the browser (Local or in some third party system)
     * @return --> Returns the WebDriver Object
     */

    public WebDriver fetchDriver(BrowserTypes browserName, boolean... remoteWebDriver)
    {
        return remoteWebDriver.length>0 ?
                remoteWebDriver[0] ? getRemoteDriver(browserName.getBrowserName()) : getDriver(browserName.getBrowserName()) :
                getDriver(browserName.getBrowserName());
    }

}
