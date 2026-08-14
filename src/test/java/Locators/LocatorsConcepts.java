package Locators;

import WindowSwitching.BaseClass;
import framework.GenericExceptions;
import framework.PathUtils;
import framework.constants.SeleniumActions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

public class LocatorsConcepts extends BaseClass {

    @Test(description = "Understanding Locator Concepts",priority = 1)
    public void performActions()
    {
        /*By btn_Explore= By.xpath("//button[@data-testid='explore-sections-button']");
        WebElement exploreButton=driver.findElement(btn_Explore);

        exploreButton.click();

        WebElement btn_Close=driver.findElement(By.xpath("//h3[text()='Start practicing']/following-sibling::button"));
        btn_Close.click();

        PathUtils.applySleep(5);

        WebElement txt_Search=driver.findElement(By.xpath("//input[@placeholder='Search all sections…']"));
        txt_Search.sendKeys("JS Alerts");

        PathUtils.applySleep(5);

        WebElement btn_JSAlerts=driver.findElement(By.xpath("//button[text()='JS Alerts']"));
        btn_JSAlerts.click();*/

        By btn_Explore= By.xpath("//button[@data-testid='explore-sections-button']");
        By btn_Close=By.xpath("//h3[text()='Start practicing']/following-sibling::button");
        By txt_SearchBox=By.xpath("//input[@placeholder='Search all sections…']");
        By btn_JSAlerts=By.xpath("//button[text()='JS Alerts']");

        if(waitManager.waitForElementPresence(btn_Close,10)) {

            seleniumUtils.performAction(SeleniumActions.CLICK, btn_Close)
                    .performAction(SeleniumActions.CLICK,btn_Explore)
                    .performAction(SeleniumActions.ENTER_DATA, txt_SearchBox, "JS Alerts")
                    .performAction(SeleniumActions.CLICK, btn_JSAlerts);
        }

        else
        {
            throw new GenericExceptions("Element not found for close button");
        }

        By btn_ShowAlert=By.xpath("//button[text()='Show Alert']");
        By btn_ShowConfirm=By.xpath("//button[text()='Show Confirm']");
        By btn_ShowPrompt=By.xpath("//button[text()='Show Prompt']");

        seleniumUtils.performAction(SeleniumActions.CLICK, btn_ShowAlert)
                .acceptAlert()
                .performAction(SeleniumActions.CLICK,btn_ShowConfirm)
                .dismissAlert()
                .performAction(SeleniumActions.CLICK,btn_ShowPrompt)
                .enterDataInsideAlertBox("Sample Data");

        By btn_ShowAlertSequence=By.xpath("//button[text()='Show Alert Sequence']");
        seleniumUtils.performAction(SeleniumActions.CLICK, btn_ShowAlertSequence)
                .acceptAlert()
                .acceptAlert()
                .acceptAlert();
    }

}
