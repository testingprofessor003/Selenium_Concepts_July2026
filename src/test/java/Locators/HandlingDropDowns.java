package Locators;

import WindowSwitching.BaseClass;
import framework.GenericExceptions;
import framework.PathUtils;
import framework.constants.SeleniumActions;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

public class HandlingDropDowns extends BaseClass {

    @Test(description = "Handling Drop Downs",priority = 1)
    public void handlingDropDowns(){

        By btn_Explore = By.xpath("//button[@data-testid='explore-sections-button']");
        By btn_Close = By.xpath("//h3[text()='Start practicing']/following-sibling::button");
        By txt_SearchBox = By.xpath("//input[@placeholder='Search all sections…']");
        By btn_DoubleClick = By.xpath("//button[text()='Dropdowns']");

        if (waitManager.waitForElementPresence(btn_Close, 10)) {

            seleniumUtils.performAction(SeleniumActions.CLICK, btn_Close)
                    .performAction(SeleniumActions.CLICK, btn_Explore)
                    .performAction(SeleniumActions.ENTER_DATA, txt_SearchBox, "Dropdowns")
                    .performAction(SeleniumActions.CLICK, btn_DoubleClick);
        } else {
            throw new GenericExceptions("Element not found for close button");
        }

        //In order to handle dropdowns with <Select> tag we need to follow the below approach:

//        Select s1=new Select(elementUtils.findElement(By.cssSelector("#native-select")));
//
//        s1.selectByVisibleText("Lemon");
//
//        //s1.getFirstSelectedOption().getText() --> Fetches the selected option from the dropdown
//        Assert.assertEquals(s1.getFirstSelectedOption().getText(), "Lemon");
//
//        PathUtils.applySleep(3);
//
//        s1.selectByValue("mango");
//
//        PathUtils.applySleep(3);
//
//        s1.selectByIndex(3);
//
//        //Prints the complete list of options in the dropdown
//        s1.getOptions().stream().map(s-> s.getText()).forEach(s-> IO.println(s));
//
//        //Checks if the dropdown allows multi selection
//        //Returns true if the above condition is satisfied else false
//        IO.println(s1.isMultiple());
//
//        //Selects the option from the dropdown based on the partial text
//        s1.selectByContainsVisibleText("Elder");

        seleniumUtils.performAction(SeleniumActions.DROPDOWN,By.cssSelector("#native-multi-select"),"Cherry");
    }
}
