package Locators;

import WindowSwitching.BaseClass;
import framework.GenericExceptions;
import framework.constants.SeleniumActions;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class FramesConcepts extends BaseClass {

    @Test(description = "Handling Frames",priority = 1)
    public void handleFrames()
    {
        By btn_Explore = By.xpath("//button[@data-testid='explore-sections-button']");
        By btn_Close = By.xpath("//h3[text()='Start practicing']/following-sibling::button");
        By txt_SearchBox = By.xpath("//input[@placeholder='Search all sections…']");
        By btn_Frames = By.xpath("//button[text()='Frames & iFrames']");

        if (waitManager.waitForElementPresence(btn_Close, 10)) {

            seleniumUtils.performAction(SeleniumActions.CLICK, btn_Close)
                    .performAction(SeleniumActions.CLICK, btn_Explore)
                    .performAction(SeleniumActions.ENTER_DATA, txt_SearchBox, "Frames & iFrames")
                    .performAction(SeleniumActions.CLICK, btn_Frames);
        } else {
            throw new GenericExceptions("Element not found for close button");
        }

        //Throws NoSuchElementException as we have not switched inside a frame
        //elementUtils.findElement(By.cssSelector("#iframe-select"));

        //Syntax of switching to a frame:
        //driver.switchTo().frame(frameName);

        By ddl_FrameDropDown=By.cssSelector("#iframe-select");
        By txt_FrameTextBox=By.cssSelector("input[data-testid='iframe-text-input']");

        //we can pass id or name attribute values directly
        //driver.switchTo().frame("practice-iframe");
        if(waitManager.checkForPresenceForFrame("practice-iframe",10))
        {
            seleniumUtils.performAction(SeleniumActions.DROPDOWN, ddl_FrameDropDown)
                    .performAction(SeleniumActions.ENTER_DATA, txt_FrameTextBox, "Sample Data");
        }

        javascriptUtils.scrollToPage(400);

        By btn_FrameButton=By.cssSelector("button[data-testid='iframe-button']");
        By cbx_CheckBox=By.cssSelector("input[data-testid='iframe-checkbox']");

        seleniumUtils.performAction(SeleniumActions.CLICK, btn_FrameButton)
                     .performAction(SeleniumActions.CLICK, cbx_CheckBox);

        //driver.switchTo().defaultContent(); //To Switch out of all the frames
        seleniumUtils.switchOutOfAllFrames();

        javascriptUtils.scrollToPage(600);

        //driver.switchTo().frame("level1-frame");

        By txt_LevelOneInput=By.cssSelector("input[data-testid='level1-input']");
        By ddl_LevelOneDropDown=By.cssSelector("select[data-testid='level1-select']");

        if(waitManager.checkForPresenceForFrame("level1-frame",10)) {
            seleniumUtils.performAction(SeleniumActions.ENTER_DATA, txt_LevelOneInput, "Level 1")
                    .performAction(SeleniumActions.DROPDOWN, ddl_LevelOneDropDown);
        }

        waitManager.checkForPresenceForFrame(elementUtils.findElement(By.cssSelector("iframe[data-testid='level2-frame']")), 10);

        Assert.assertTrue(seleniumUtils.getElementText(By.tagName("body")).isBlank());

        seleniumUtils.switchToParentFrame()
                .switchOutOfAllFrames();

        waitManager.checkForPresenceForFrame("dynamic-iframe",10);

        By txt_DynamicFrame=By.cssSelector("input[data-testid='dynamic-iframe-input']");
        By btn_DynamicFrame=By.cssSelector("button[data-testid='dynamic-button']");

        seleniumUtils.performAction(SeleniumActions.ENTER_DATA,txt_DynamicFrame,"Dynamic Data")
                .performAction(SeleniumActions.CLICK, btn_DynamicFrame);

    }
}
