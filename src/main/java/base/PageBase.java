package base;


import connectors.DriverFactory;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.time.Duration;
import java.util.Locale;
import java.util.Properties;
import java.util.Random;
import java.util.ResourceBundle;

public class PageBase {
    public WebDriver driver;
    protected Actions actions;

    public PageBase(WebDriver driver) {
        this.driver = driver;
    }

    protected void clickButton(WebElement element) {
        element.click();
    }

    protected void addText(WebElement element, String word) {
        element.sendKeys(word);
    }

    protected static void clearField(WebElement button) {
        button.clear();
    }

    protected static String getText(WebElement element) {
       return element.getText();
    }

    public void forceClickElement(WebElement element) {
        actions = new Actions(DriverFactory.getDriver());
        actions.moveToElement(element).click(element).build().perform();
    }

    public void scrollToViewElement(WebElement element) {
        /*((JavascriptExecutor) DriverFactory.getDriver()).executeScript(
                "arguments[0].scrollIntoView(true);", element);*/
    }

    public void waitForPageToLoad() {
        WebDriverWait wait = new WebDriverWait(DriverFactory.getDriver(), Duration.ofSeconds(60));
        wait.until((ExpectedCondition<Boolean>) webDriver -> ((JavascriptExecutor) DriverFactory.getDriver()).
                executeScript("return document.readyState").equals("complete"));
    }

}
