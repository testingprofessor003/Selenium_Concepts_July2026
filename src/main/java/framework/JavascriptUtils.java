package framework;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

public class JavascriptUtils {

    WebDriver driver;

    public JavascriptUtils(WebDriver driver) {
        this.driver = driver;
    }

    public JavascriptUtils scrollToPage(int pixels)
    {
        ((JavascriptExecutor)driver).executeScript("window.scrollTo(0," + pixels + ")");
        return this;
    }

    public JavascriptUtils scrollToElement(By by)
    {
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", driver.findElement(by));
        return this;
    }
}
