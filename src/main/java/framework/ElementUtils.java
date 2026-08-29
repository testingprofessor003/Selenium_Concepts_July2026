package framework;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class ElementUtils {

    private WebDriver driver;
    private WaitManager waitManager;

    public ElementUtils(WebDriver driver, WaitManager waitManager)
    {
        this.driver = driver;
        this.waitManager = waitManager;
    }

    public WebElement findElement(By by)
    {
        return driver.findElement(by);
    }

    public WebElement findElement(By by, int sec)
    {
        if(waitManager.waitForElementPresence(by, sec))
        return driver.findElement(by);

        else
        {
            throw new GenericExceptions("Element not found for: "+by);
        }

    }

    public List<WebElement> findElements(By by)
    {
        return driver.findElements(by);
    }
}
