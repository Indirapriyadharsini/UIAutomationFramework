package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ElementUtils {
    public static void waitForElementToBeVisible(WebDriver driver, WebElement element) {
        WaitUtils.waitForVisibility(driver, element);
    }

    public static void clickWithWait(WebDriver driver, WebElement element) {
        waitForElementToBeVisible(driver, element);
        element.click();
    }

    public static void enterTextWithWait(WebDriver driver, WebElement element, String text) {
        waitForElementToBeVisible(driver, element);
        element.clear();
        element.sendKeys(text);
    }
}