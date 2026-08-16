package Locators;

import WindowSwitching.BaseClass;
import framework.GenericExceptions;
import framework.constants.SeleniumActions;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.KeyInput;
import org.testng.annotations.Test;

import java.util.List;

public class DoubleClickConcepts extends BaseClass {

    @Test(description = "Understanding Double Click Concepts",priority = 1)
    public void performDoubleClick()
    {
        By btn_Explore = By.xpath("//button[@data-testid='explore-sections-button']");
        By btn_Close = By.xpath("//h3[text()='Start practicing']/following-sibling::button");
        By txt_SearchBox = By.xpath("//input[@placeholder='Search all sections…']");
        By btn_DoubleClick = By.xpath("//button[text()='Double Click']");

        if (waitManager.waitForElementPresence(btn_Close, 10)) {

            seleniumUtils.performAction(SeleniumActions.CLICK, btn_Close)
                    .performAction(SeleniumActions.CLICK, btn_Explore)
                    .performAction(SeleniumActions.ENTER_DATA, txt_SearchBox, "Double Click")
                    .performAction(SeleniumActions.CLICK, btn_DoubleClick);
        } else {
            throw new GenericExceptions("Element not found for close button");
        }

        Actions a1=new Actions(driver);
        List<WebElement> doubleClickAndFillData=elementUtils.findElements(By.xpath("//div[starts-with(@data-testid,'rename-item') and @title='Double-click to rename']"));

        doubleClickAndFillData.forEach(element -> {
            a1.pause(1000).doubleClick(element).build().perform();
            a1.pause(1000)
                    .click(elementUtils.findElement(
                            By.xpath("//input[starts-with(@data-testid,'rename-input') and @value]")
                    ))
                    .pause(1000)
                    .sendKeys(elementUtils.findElement(By.xpath("//input[starts-with(@data-testid,'rename-input') and @value]")),"Sample Informations")
                    .pause(1000)
                    .sendKeys(elementUtils.findElement(By.xpath("//input[starts-with(@data-testid,'rename-input') and @value]")),Keys.ENTER)
                    .build().perform();
        });


    }
}
