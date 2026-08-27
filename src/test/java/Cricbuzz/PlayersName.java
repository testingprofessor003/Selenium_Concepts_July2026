package Cricbuzz;

import WindowSwitching.BaseClass;
import framework.ElementUtils;
import framework.constants.SeleniumActions;
import org.apache.commons.io.file.PathUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.io.File;
import java.time.Duration;
import java.util.List;

public class PlayersName extends BaseClass {

    @Test(description = "Understanding Locator Concepts", priority = 1)
    public void liveScores() {
        seleniumUtils.launchApplication("https://www.cricbuzz.com/");

        //a[text()='Live Scores']
        By live_Score_Menu = By.xpath("//a[text()='Live Scores']");

        elementUtils.findElement(live_Score_Menu);
        seleniumUtils.performAction(SeleniumActions.CLICK,live_Score_Menu);

        By live_Score=By.xpath("//a[@title='India tour of Sri Lanka 2026']/following-sibling::div/div/span/a/span[text()='Live Score']");

        WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.presenceOfElementLocated(live_Score));
        elementUtils.findElement(live_Score);
        seleniumUtils.performAction(SeleniumActions.CLICK,live_Score);
        try {
            Thread.sleep(3);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        By squad=By.xpath("//a[@title='Squads - SL - IND']");
        elementUtils.findElement(squad);
        seleniumUtils.performAction(SeleniumActions.CLICK,squad);

        By listofPlayers=By.xpath("//h1[text()='playing XI']/following-sibling::div//div[contains(@class,'justify-start')]/span[not(@class)]");
        List<WebElement> players=elementUtils.findElements(listofPlayers);
        File f = new File(System.getProperty("user.dir") + "//Screenshots");
        f.mkdir();
        File f1 = new File(f.getPath() + "//India");
        f1.mkdir();

        for(int i=0;i< players.size();i++) {

            wait.until(ExpectedConditions.elementToBeClickable(players.get(i)));
            players.get(i).click();


            reports.captureScreenshot(f1.getPath() + players.get(i).getText()+".png");
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            driver.navigate().forward();
        }



        }







    }


