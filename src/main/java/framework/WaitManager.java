package framework;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class WaitManager {

    private WebDriver driver;
    public WaitManager(WebDriver driver) {
        this.driver = driver;
    }

    public boolean waitForElementPresence(By by, int sec)
    {
        try
        {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(sec));
            wait.until(ExpectedConditions.presenceOfElementLocated(by));
            return true;
        }

        catch(Exception e)
        {
            return false;
        }
    }
}
