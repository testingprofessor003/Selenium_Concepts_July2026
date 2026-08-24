package framework;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.Set;

public class JavascriptUtils {

    WebDriver driver;
    JavascriptExecutor js;

    public JavascriptUtils(WebDriver driver) {
        this.driver = driver;
        this.js = (JavascriptExecutor) driver;
    }

    // ---------- Scrolling ----------

    public JavascriptUtils scrollToPage(int pixels) {
        js.executeScript("window.scrollTo(0," + pixels + ")");
        return this;
    }

    public JavascriptUtils scrollBy(int xOffset, int yOffset) {
        js.executeScript("window.scrollBy(arguments[0], arguments[1]);", xOffset, yOffset);
        return this;
    }

    public JavascriptUtils scrollToTop() {
        js.executeScript("window.scrollTo(0, 0);");
        return this;
    }

    public JavascriptUtils scrollToBottom() {
        js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
        return this;
    }

    public JavascriptUtils scrollToElement(By by) {
        js.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(by));
        return this;
    }

    public JavascriptUtils scrollToElement(WebElement element) {
        js.executeScript("arguments[0].scrollIntoView(true);", element);
        return this;
    }

    public JavascriptUtils scrollToElementCentered(WebElement element) {
        js.executeScript("arguments[0].scrollIntoView({block: 'center', inline: 'center'});", element);
        return this;
    }

    // ---------- Click / Interaction ----------

    public JavascriptUtils clickOnElement(By by) {
        js.executeScript("arguments[0].click();", driver.findElement(by));
        return this;
    }

    public JavascriptUtils clickOnElement(WebElement element) {
        js.executeScript("arguments[0].click();", element);
        return this;
    }

    public JavascriptUtils doubleClickOnElement(WebElement element) {
        js.executeScript("var event = new MouseEvent('dblclick', {bubbles: true}); arguments[0].dispatchEvent(event);", element);
        return this;
    }

    public JavascriptUtils mouseHoverOnElement(WebElement element) {
        js.executeScript("var event = new MouseEvent('mouseover', {bubbles: true}); arguments[0].dispatchEvent(event);", element);
        return this;
    }

    public JavascriptUtils submitForm(WebElement formElement) {
        js.executeScript("arguments[0].submit();", formElement);
        return this;
    }

    // ---------- Text / Value ----------

    public JavascriptUtils enterText(WebElement element, String text) {
        js.executeScript("arguments[0].value = arguments[1];", element, text);
        return this;
    }

    public JavascriptUtils enterTextAndTriggerEvent(WebElement element, String text) {
        js.executeScript(
                "arguments[0].value = arguments[1];" +
                        "arguments[0].dispatchEvent(new Event('input', { bubbles: true }));" +
                        "arguments[0].dispatchEvent(new Event('change', { bubbles: true }));",
                element, text);
        return this;
    }

    public JavascriptUtils clearText(WebElement element) {
        js.executeScript("arguments[0].value = '';", element);
        return this;
    }

    public String getValue(WebElement element) {
        return (String) js.executeScript("return arguments[0].value;", element);
    }

    public String getText(WebElement element) {
        return (String) js.executeScript("return arguments[0].textContent;", element);
    }

    public String getInnerHTML(WebElement element) {
        return (String) js.executeScript("return arguments[0].innerHTML;", element);
    }

    public String getOuterHTML(WebElement element) {
        return (String) js.executeScript("return arguments[0].outerHTML;", element);
    }

    // ---------- Attributes / Styles ----------

    public JavascriptUtils setAttribute(WebElement element, String attribute, String value) {
        js.executeScript("arguments[0].setAttribute(arguments[1], arguments[2]);", element, attribute, value);
        return this;
    }

    public String getAttribute(WebElement element, String attribute) {
        return (String) js.executeScript("return arguments[0].getAttribute(arguments[1]);", element, attribute);
    }

    public JavascriptUtils removeAttribute(WebElement element, String attribute) {
        js.executeScript("arguments[0].removeAttribute(arguments[1]);", element, attribute);
        return this;
    }

    public JavascriptUtils setStyle(WebElement element, String styleProperty, String value) {
        js.executeScript("arguments[0].style[arguments[1]] = arguments[2];", element, styleProperty, value);
        return this;
    }

    public JavascriptUtils highlightElement(WebElement element) {
        js.executeScript("arguments[0].style.border='3px solid red';", element);
        return this;
    }

    public JavascriptUtils flashElement(WebElement element, int times) {
        String originalBorder = getAttribute(element, "style");
        for (int i = 0; i < times; i++) {
            setStyle(element, "border", "3px solid red");
            setStyle(element, "border", "3px solid yellow");
        }
        js.executeScript("arguments[0].setAttribute('style', arguments[1]);", element, originalBorder == null ? "" : originalBorder);
        return this;
    }

    // ---------- Checkbox / Select ----------

    public JavascriptUtils checkCheckbox(WebElement element) {
        js.executeScript("if(!arguments[0].checked){arguments[0].click();}", element);
        return this;
    }

    public JavascriptUtils uncheckCheckbox(WebElement element) {
        js.executeScript("if(arguments[0].checked){arguments[0].click();}", element);
        return this;
    }

    public boolean isChecked(WebElement element) {
        return (boolean) js.executeScript("return arguments[0].checked;", element);
    }

    // ---------- Element State ----------

    public boolean isElementVisible(WebElement element) {
        return (boolean) js.executeScript(
                "var rect = arguments[0].getBoundingClientRect();" +
                        "return (rect.top >= 0 && rect.left >= 0 && rect.bottom <= window.innerHeight && rect.right <= window.innerWidth);",
                element);
    }

    public JavascriptUtils removeElement(WebElement element) {
        js.executeScript("arguments[0].remove();", element);
        return this;
    }

    public JavascriptUtils disableElement(WebElement element) {
        js.executeScript("arguments[0].setAttribute('disabled','true');", element);
        return this;
    }

    public JavascriptUtils enableElement(WebElement element) {
        js.executeScript("arguments[0].removeAttribute('disabled');", element);
        return this;
    }

    // ---------- Page Level ----------

    public JavascriptUtils refreshPage() {
        js.executeScript("history.go(0);");
        return this;
    }

    public JavascriptUtils navigateBack() {
        js.executeScript("window.history.go(-1);");
        return this;
    }

    public JavascriptUtils navigateForward() {
        js.executeScript("window.history.go(1);");
        return this;
    }

    public String getPageTitle() {
        return (String) js.executeScript("return document.title;");
    }

    public String getPageUrl() {
        return (String) js.executeScript("return document.URL;");
    }

    public String getDomain() {
        return (String) js.executeScript("return document.domain;");
    }

    public boolean isPageLoaded() {
        return js.executeScript("return document.readyState;").toString().equals("complete");
    }

    public JavascriptUtils generateAlert(String message) {
        js.executeScript("alert(arguments[0]);", message);
        return this;
    }

    public String getInnerText() {
        return (String) js.executeScript("return document.documentElement.innerText;");
    }

    public String getBrowserUserAgent() {
        return (String) js.executeScript("return navigator.userAgent;");
    }

    public JavascriptUtils zoomPage(String zoomLevel) {
        js.executeScript("document.body.style.zoom=arguments[0];", zoomLevel);
        return this;
    }

    public JavascriptUtils openNewTab(String url) {
        js.executeScript("window.open(arguments[0]);", url);
        return this;
    }

    public void switchToWindowHandle(String handle) {
        driver.switchTo().window(handle);
    }

    public Set<String> getAllWindowHandles() {
        return driver.getWindowHandles();
    }

    @SuppressWarnings("unchecked")
    public List<String> getAllLinks() {
        return (List<String>) js.executeScript(
                "return Array.from(document.querySelectorAll('a')).map(a => a.href);");
    }

    // ---------- Async / Scripts ----------

    public Object executeScript(String script, Object... args) {
        return js.executeScript(script, args);
    }

    public Object executeAsyncScript(String script, Object... args) {
        return js.executeAsyncScript(script, args);
    }
}
