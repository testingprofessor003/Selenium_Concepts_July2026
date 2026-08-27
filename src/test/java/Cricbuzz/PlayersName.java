package Cricbuzz;

import WindowSwitching.BaseClass;
import framework.ElementUtils;
import framework.PathUtils;
import framework.constants.SeleniumActions;
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
        seleniumUtils.performAction(SeleniumActions.CLICK, live_Score_Menu);

        By lnk_Recent=By.xpath("//a[text()='Recent']");
        seleniumUtils.performAction(SeleniumActions.CLICK, lnk_Recent);

        By live_Score = By.xpath("//a[@title='India tour of Sri Lanka 2026']/following-sibling::div/div/span/a/span[text()='Live Score']");

//        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
//        wait.until(ExpectedConditions.presenceOfElementLocated(live_Score));
        seleniumUtils.performAction(SeleniumActions.CLICK, live_Score);

        PathUtils.applySleep(3);

        By squad = By.xpath("//a[@title='Squads - SL - IND']");
        seleniumUtils.performAction(SeleniumActions.CLICK, squad);

        String teamName = elementUtils.findElement(By.xpath("//h1[contains(@class,'ml-2')]")).getText();

        By listofPlayers = By.xpath("//h1[text()='playing XI']/following-sibling::div//div[contains(@class,'justify-start')]/span[not(@class)]");
        //List<WebElement> players=elementUtils.findElements(listofPlayers);

        File f = new File(System.getProperty("user.dir") + "//Screenshots//" + teamName);
        f.mkdirs();
//        f.mkdir();
//        File f1 = new File(f.getPath() + "//India");
//        f1.mkdir();

        for (int i = 0; i < elementUtils.findElements(listofPlayers).size(); i++) {

            WebElement element = elementUtils.findElements(listofPlayers).get(i);
            String playerName = element.getText();

            seleniumUtils.performAction(SeleniumActions.CLICK,element);

            reports.captureScreenshot(f.getPath() + "//" + playerName + ".png");
//            try {
//                Thread.sleep(10);
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            }

            PathUtils.applySleep(1);
            driver.navigate().back();
        }


    }


}


