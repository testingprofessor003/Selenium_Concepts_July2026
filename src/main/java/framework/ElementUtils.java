package framework;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ElementUtils {

    WebDriver driver;

    public WebElement findElement(By by) {
        return driver.findElement(by);
    }
}
