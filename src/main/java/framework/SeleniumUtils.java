package framework;

import framework.constants.AdvancedActions;
import framework.constants.BrowserTypes;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;

@Getter
@Accessors(fluent = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SeleniumUtils {

    WebDriver driver;
    Actions actions;
    ElementUtils elementUtils;

    public SeleniumUtils(BrowserTypes browserName) {
        this.driver = BrowserUtils.fetchDriver(browserName);
        this.elementUtils = new ElementUtils(driver);
        this.actions = new Actions(driver);
    }

    public interface PerformAdvancedActions {
        void performActions(AdvancedActions advancedActions, By... by);
    }

    @AllArgsConstructor
    @Getter
    static class AdvancedEvents implements PerformAdvancedActions {
        ElementUtils elementUtils;
        Actions actions;

        @Override
        public void performActions(AdvancedActions actions, By... by) {

            switch (actions) {
                case DRAG_AND_DROP -> {
                    this.actions().dragAndDrop(elementUtils.findElement(by[0]), elementUtils.findElement(by[1])).build().perform();
                }

                case MOUSE_HOVER ->  {
                    this.actions().moveToElement(elementUtils.findElement(by[0])).build().perform();
                }

                case DOUBLE_CLICK -> {
                    this.actions().doubleClick(elementUtils.findElement(by[0])).build().perform();
                }

                case RIGHT_CLICK -> {
                    this.actions().contextClick(elementUtils.findElement(by[0])).build().perform();
                }
            }
        }
    }

}
