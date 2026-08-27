package framework;
import framework.constants.SeleniumActions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Sleeper;

public class SeleniumUtils {
    
    private WebDriver driver;
    private ElementUtils elementUtils;
    private WaitManager waitManager;

    public SeleniumUtils(WebDriver driver, ElementUtils elementUtils, WaitManager waitManager) {
        this.driver = driver;
        this.elementUtils = elementUtils;
        this.waitManager = waitManager;
    }

    public SeleniumUtils launchApplication(String url) {

        //Optional.ofNullable(url).orElseThrow(() -> new GenericExceptions("URL is null"));

        if(url == null)
        {
            throw new GenericExceptions("URL is null");
        }

        if(!url.startsWith("http"))
        {
            throw new GenericExceptions("Invalid URL: " + url);
        }

        driver.get(url);
        driver.manage().window().maximize();
        return this; //this keyword is used to return the current object
    }

    public SeleniumUtils switchToNewTab(String url)
    {
        driver.switchTo().newWindow(WindowType.TAB);
        launchApplication(url);
        return this;
    }

    public String getRequiredInformationFromDriver(String attribute)
    {
        return switch (attribute.toUpperCase()) {
            case "URL" -> driver.getCurrentUrl();
            case "TITLE" -> driver.getTitle();
            case "PAGE SOURCE" -> driver.getPageSource();
            case "WINDOW HANDLE" -> driver.getWindowHandle();
            default -> null;
        };
    }

    public SeleniumUtils pageNavigations(String navigation)
    {
        switch (navigation.toUpperCase())
        {
            case "BACK" -> driver.navigate().back();
            case "FORWARD" -> driver.navigate().forward();
            case "REFRESH" -> driver.navigate().refresh();
        }
        return this;
    }

    public SeleniumUtils performAction(SeleniumActions actions, By by, String... data)
    {
        switch (actions) {
            case CLICK -> click(by);
            case ENTER_DATA -> enterData(by,data[0]);
            case DROPDOWN -> selectOptionFromDropDown(by,data);
        }
        return this;
    }

    public SeleniumUtils performAction(SeleniumActions actions, WebElement element, String... data)
    {
        switch (actions) {
            case CLICK -> click(element);
            case ENTER_DATA -> enterData(element,data[0]);
            case DROPDOWN -> selectOptionFromDropDown(element,data);
        }
        return this;
    }

    private SeleniumUtils click(By by)
    {
        if(waitManager.waitForElementClickable(by,10))
        {
            elementUtils.findElement(by).click();
        }

        else
        {
            throw new GenericExceptions("Element is not clickable for: "+by);
        }

        return this;
    }

    private SeleniumUtils enterData(By by, String data)
    {
        elementUtils.findElement(by).sendKeys(data);
        return this;
    }

    private SeleniumUtils click(WebElement element)
    {
        if(waitManager.waitForElementClickable(element,10))
        {
            element.click();
        }

        else
        {
            throw new GenericExceptions("Element is not clickable for: "+element);
        }

        return this;
    }

    private SeleniumUtils enterData(WebElement element, String data)
    {
        element.sendKeys(data);
        return this;
    }
    /*
        Accepting the Browser Based Alerts
     */
    public SeleniumUtils acceptAlert()
    {
        PathUtils.applySleep(3);
        driver.switchTo().alert().accept();
        return this;
    }

    /*
        Dismissing the Alerts
     */
    public SeleniumUtils dismissAlert()
    {
        PathUtils.applySleep(3);
        driver.switchTo().alert().dismiss();
        return this;
    }

    /*
        Fetching the text from the alerts
     */
    public String getAlertText()
    {
        return driver.switchTo().alert().getText();
    }

    /*
        Entering the data inside the pop up
     */
    public SeleniumUtils enterDataInsideAlertBox(String data)
    {
        driver.switchTo().alert().sendKeys(data);
        PathUtils.applySleep(3);
        acceptAlert();
        return this;
    }

    private SeleniumUtils selectOptionFromDropDown(By by,String... option)
    {
        Select s1=new Select(elementUtils.findElement(by));

        if(option.length==0) //Selects a random option from the drop-down
        {
            List<WebElement> elements=s1.getOptions();

            int number= ThreadLocalRandom.current().nextInt(0,elements.size()-1);

            s1.selectByIndex(number);
        }

        else
        {
            Arrays.asList(option).forEach(options-> {
                if (s1.getOptions().stream().map(s -> s.getText()).filter(s -> s.equalsIgnoreCase(options))
                        .findAny().isPresent()) {
                    s1.selectByVisibleText(options);
                } else if (s1.getOptions().stream().map(s -> s.getText()).filter(s -> s.contains(options)).findAny().isPresent()) {
                    s1.selectByContainsVisibleText(options);
                } else if (s1.getOptions().stream().map(s -> s.getAttribute("value")).filter(s -> s.equalsIgnoreCase(options)).findAny().isPresent()) {
                    s1.selectByValue(options);
                } else {
                    s1.selectByIndex(Integer.parseInt(options));
                }
            });

        }

        return this;
    }

    private SeleniumUtils selectOptionFromDropDown(WebElement element,String... option)
    {
        Select s1=new Select(element);

        if(option.length==0) //Selects a random option from the drop-down
        {
            List<WebElement> elements=s1.getOptions();

            int number= ThreadLocalRandom.current().nextInt(0,elements.size()-1);

            s1.selectByIndex(number);
        }

        else
        {
            Arrays.asList(option).forEach(options-> {
                if (s1.getOptions().stream().map(s -> s.getText()).filter(s -> s.equalsIgnoreCase(options))
                        .findAny().isPresent()) {
                    s1.selectByVisibleText(options);
                } else if (s1.getOptions().stream().map(s -> s.getText()).filter(s -> s.contains(options)).findAny().isPresent()) {
                    s1.selectByContainsVisibleText(options);
                } else if (s1.getOptions().stream().map(s -> s.getAttribute("value")).filter(s -> s.equalsIgnoreCase(options)).findAny().isPresent()) {
                    s1.selectByValue(options);
                } else {
                    s1.selectByIndex(Integer.parseInt(options));
                }
            });

        }

        return this;
    }



}
