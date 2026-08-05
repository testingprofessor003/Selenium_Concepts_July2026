package WindowSwitching;

import org.testng.annotations.Test;
import org.openqa.selenium.WindowType;

public class CreatingNewTabsAndWindows extends BaseClass {
    
    @Test(description = "Creating New Tabs",priority = 1)
    public void creatingNewTabs() throws InterruptedException
    {
        seleniumUtils.launchApplication("https://www.google.com");

        //Launch a new tab
        // driver.switchTo().newWindow(WindowType.TAB); //Selenium 4 onwards we have this method to launch a new tab
        // driver.get("https://www.gmail.com"); //Launch Gmail in the new tab
        seleniumUtils.switchToNewTab("https://www.gmail.com");

        // driver.switchTo().newWindow(WindowType.TAB);
        // driver.get("https://www.facebook.com"); //Launch Facebook in the new tab
        seleniumUtils.switchToNewTab("https://www.facebook.com");

        //For every tab or window that you have launched there will be an unique reference id that will be associated with it
        String facebookWindowId = driver.getWindowHandle();

        // driver.switchTo().newWindow(WindowType.TAB);
        // driver.get("https://www.amazon.com"); //Launch Amazon in the new tab
        seleniumUtils.switchToNewTab("https://www.amazon.com");

        // driver.switchTo().newWindow(WindowType.TAB);
        // driver.get("https://www.twitter.com"); //Launch Twitter in the new tab
        seleniumUtils.switchToNewTab("https://www.twitter.com");

        //Get the window id of the twitter tab
        String twitterWindowId = driver.getWindowHandle();

        // driver.switchTo().newWindow(WindowType.TAB);
        // driver.get("https://www.instagram.com"); //Launch Instagram in the new tab and selenium focus is in this new tab

        seleniumUtils.switchToNewTab("https://www.instagram.com");
        
        //Wait for 5 seconds
        Thread.sleep(5000);

        //Switch to the facebook tab and selenium focus is in this tab
        driver.switchTo().window(facebookWindowId);

        //Wait for 5 seconds
        Thread.sleep(5000);

        //Switch to the twitter tab and selenium focus is in this tab
        driver.switchTo().window(twitterWindowId);
    }
}
