package Locators;

import WindowSwitching.BaseClass;
import framework.GenericExceptions;
import framework.constants.SeleniumActions;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

import java.util.List;
import java.util.stream.Collectors;

public class MouseHoverCascade extends BaseClass {

    @Test(description = "Understanding Mouse Hovers", priority = 1)
    public void understandingMouseHovers() {
        By btn_Explore = By.xpath("//button[@data-testid='explore-sections-button']");
        By btn_Close = By.xpath("//h3[text()='Start practicing']/following-sibling::button");
        By txt_SearchBox = By.xpath("//input[@placeholder='Search all sections…']");
        By btn_MouseHover = By.xpath("//button[text()='Mouse Hover']");

        if (waitManager.waitForElementPresence(btn_Close, 10)) {

            seleniumUtils.performAction(SeleniumActions.CLICK, btn_Close)
                    .performAction(SeleniumActions.CLICK, btn_Explore)
                    .performAction(SeleniumActions.ENTER_DATA, txt_SearchBox, "Mouse Hover")
                    .performAction(SeleniumActions.CLICK, btn_MouseHover);
        } else {
            throw new GenericExceptions("Element not found for close button");
        }

        //Scrolling down the web page
        ((JavascriptExecutor)driver).executeScript("window.scrollBy(0,200)");

        Actions a1=new Actions(driver);
        List<WebElement> cascadeHovers=elementUtils.findElements(By.xpath("//h4[text()='Cascading Hover']/following-sibling::div/div"));

        cascadeHovers.forEach(element-> {
            a1.pause(1000).moveToElement(element).build().perform();
        });

    }
}
