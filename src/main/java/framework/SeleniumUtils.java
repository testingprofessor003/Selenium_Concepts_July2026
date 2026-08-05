package framework;
import org.openqa.selenium.WebDriver;
import java.util.Optional;
import org.openqa.selenium.WindowType;

public class SeleniumUtils {
    
    private WebDriver driver;

    public SeleniumUtils(WebDriver driver) {
        this.driver = driver;
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
}
