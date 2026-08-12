package framework;
import framework.constants.SeleniumActions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import java.util.Optional;
import org.openqa.selenium.WindowType;

public class SeleniumUtils {
    
    private WebDriver driver;
    private ElementUtils elementUtils;

    public SeleniumUtils(WebDriver driver, ElementUtils elementUtils) {
        this.driver = driver;
        this.elementUtils = elementUtils;
    }

    public SeleniumUtils launchApplication(String url) {

        //Optional.ofNullable(url).orElseThrow(() -> new GenericExceptions("URL is null"));

        if(url == null)
        {
            throw new GenericExceptions("URL is null");
        }

        if(!url.startsWith("http"))
        {
            throw new GenericExceptions("Invalid URL: " + url);
        }

        driver.get(url);
        driver.manage().window().maximize();
        return this; //this keyword is used to return the current object
    }

    public SeleniumUtils switchToNewTab(String url)
    {
        driver.switchTo().newWindow(WindowType.TAB);
        launchApplication(url);
        return this;
    }

    public String getRequiredInformationFromDriver(String attribute)
    {
        return switch (attribute.toUpperCase()) {
            case "URL" -> driver.getCurrentUrl();
            case "TITLE" -> driver.getTitle();
            case "PAGE SOURCE" -> driver.getPageSource();
            case "WINDOW HANDLE" -> driver.getWindowHandle();
            default -> null;
        };
    }

    public SeleniumUtils performAction(SeleniumActions actions, By by, String... data)
    {
        switch (actions) {
            case CLICK -> click(by);
            case ENTER_DATA -> enterData(by,data[0]);
        }
        return this;
    }

    private SeleniumUtils click(By by)
    {
        elementUtils.findElement(by).click();
        return this;
    }

    private SeleniumUtils enterData(By by, String data)
    {
        elementUtils.findElement(by).sendKeys(data);
        return this;
    }




}
