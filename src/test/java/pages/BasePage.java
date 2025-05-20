package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import drivers.DriverFactory;
import config.ConfigReader;
import utils.WaitUtils;

public abstract class BasePage {
    public WebDriver driver;

    public BasePage() {
        this.driver = DriverFactory.getDriver();
        String baseUrl = ConfigReader.get("baseUrl");
        driver.get(baseUrl);
        PageFactory.initElements(driver, this);
    }

    protected void waitForElementToBeVisible(WebElement element) {
        WaitUtils.waitForVisibility(driver, element);
    }
    protected void clickWithWait(WebElement element) {
        waitForElementToBeVisible(element);
        element.click();
    }

    protected void enterTextWithWait(WebElement element, String text) {
        waitForElementToBeVisible(element);
        element.clear();
        element.sendKeys(text);
    }

    public boolean isDashboardPageDisplayed() {
        return driver.getCurrentUrl().contains("dashboard");
    }

}
