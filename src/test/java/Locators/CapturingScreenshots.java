package Locators;

import WindowSwitching.BaseClass;
import com.google.common.io.Files;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;

public class CapturingScreenshots extends BaseClass {

    @Test(description = "Capturing Screenshots",priority = 1)
    public void captureScreenshots() throws IOException {
        seleniumUtils.launchApplication("https://www.cricbuzz.com");

        //Capture a Screenshot:
        //(TakesScreenshot) --> Captures the screenshot
        //(TakesScreenshot)driver --> Captures the screenshot of the driver launched
        //((TakesScreenshot)driver).getScreenshotAs() --> How do you want to store the screenshot that has been captured

        //In Selenium, we can store the screenshots in the form of .png file, BASE64 and BYTE Array

        //Screenshot will be captured in the form of .png file
//        File src=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
//        File dest=new File("SampleImages.png");
//
//        Files.copy(src,dest);

        reports.captureScreenshot("SampleImages.png");
    }
}
