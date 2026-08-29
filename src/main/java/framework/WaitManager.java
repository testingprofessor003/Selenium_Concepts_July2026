package framework;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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

    public boolean waitForPresenceOfElements(By by, int sec)
    {
        try
        {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(sec));
            wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(by));
            return true;
        }

        catch(Exception e)
        {
            return false;
        }
    }

    public boolean waitForElementClickable(By by, int sec)
    {
        try
        {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(sec));
            wait.until(ExpectedConditions.elementToBeClickable(by));
            return true;
        }

        catch(Exception e)
        {
            return false;
        }
    }


    public boolean waitForElementClickable(WebElement element, int sec)
    {
        try
        {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(sec));
            wait.until(ExpectedConditions.elementToBeClickable(element));
            return true;
        }

        catch(Exception e)
        {
            return false;
        }
    }

    public boolean waitForTextContains(String text, int sec)
    {
        try
        {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(sec));
            wait.until(ExpectedConditions.textToBePresentInElementLocated(By.tagName("body"),text));
            return true;
        }

        catch(Exception e)
        {
            return false;
        }
    }

    public boolean waitForTextIsPresentOrNot(String text, By by, int sec)
    {
        try
        {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(sec));
            wait.until(ExpectedConditions.textToBe(by, text));
            return true;
        }

        catch(Exception e)
        {
            return false;
        }
    }

    public boolean waitForInvisibleElement(By by, int sec)
    {
        try
        {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(sec));
            wait.until(ExpectedConditions.invisibilityOfElementLocated(by));
            return true;
        }

        catch(Exception e)
        {
            return false;
        }
    }

    public boolean waitForPresenceOfAlert(int sec)
    {
        try
        {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(sec));
            wait.until(ExpectedConditions.alertIsPresent());
            return true;
        }

        catch(Exception e)
        {
            return false;
        }
    }

    public boolean checkWhetherPageIsLoaded(int sec)
    {
//        try
//        {
//            ExpectedConditions
//            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(sec));
//            wait.until()
//        }

        return false;
    }

    public boolean checkForPresenceForFrame(String frameName, int sec)
    {
        try
        {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(sec));
            wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameName));
            return true;
        }

        catch(Exception e)
        {
            throw new GenericExceptions("Frame " + frameName + " not found");
        }
    }

    public boolean checkForPresenceForFrame(WebElement frames, int sec)
    {
        try
        {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(sec));
            wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frames));
            return true;
        }

        catch(Exception e)
        {
            throw new GenericExceptions("Frame " + frames.toString() + " not found");
        }
    }
}
