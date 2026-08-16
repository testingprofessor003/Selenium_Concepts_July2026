package framework;

import com.google.common.io.Files;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;

public class Reports {

    WebDriver driver;

    public Reports(WebDriver driver) {
        this.driver=driver;
    }

    public Reports captureScreenshot(String path)
    {
        try {
            File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            File dest = new File(path);

            Files.copy(src, dest);
        }

        catch (Exception e)
        {
            e.printStackTrace();
        }

        return this;
    }
}
