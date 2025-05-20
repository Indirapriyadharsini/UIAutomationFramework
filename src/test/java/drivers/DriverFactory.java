package drivers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import config.ConfigReader;

public class DriverFactory {
    private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public static WebDriver getDriver() {
        return driver.get();
    }

    public static void initializeDriver() {
        String browser = ConfigReader.get("browser");
        switch (browser.toLowerCase()) {
            case "chrome":
                driver.set(new ChromeDriver());
                break;
            case "firefox":
                driver.set(new FirefoxDriver());
                break;
            default:
                throw new RuntimeException("Unsupported browser: " + browser);
        }
        getDriver().manage().window().maximize();
    }

    public static void quitDriver() {
        getDriver().quit();
        driver.remove();
    }
}