// src/test/java/hooks/ExtentHooks.java
package hooks;

import io.cucumber.java.*;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.ExtentReportManager;
import utils.ScreenshotUtils;
import drivers.DriverFactory;

import java.time.Duration;
import java.util.Set;

public class ExtentHooks {

    @Before
    public void beforeScenario(Scenario scenario) {
        ExtentReportManager.createTest(scenario.getName());
    }

    @AfterStep
    public void afterStep(Scenario scenario) {
        if (scenario.isFailed()) {
            ExtentReportManager.getTest().fail("Step failed");
            try {
                // Capture the screenshot
                String screenshotPath = ScreenshotUtils.captureScreenshot(DriverFactory.getDriver(), "failure");
                ExtentReportManager.getTest().addScreenCaptureFromPath(screenshotPath);
            } catch (Exception e) {
                ExtentReportManager.getTest().fail("Failed to capture screenshot: " + e.getMessage());
            }
        } else {
            ExtentReportManager.getTest().pass("Step passed");
        }
        ExtentReportManager.flush();
    }

    /*@After
    public void afterScenario(Scenario scenario) throws InterruptedException {
        if (scenario.isFailed()) {
            try {
                // Ensure the page is fully loaded
                new WebDriverWait(DriverFactory.getDriver(), Duration.ofSeconds(60))
                        .until(webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));

                // Switch to the correct window
                Set<String> windowHandles = DriverFactory.getDriver().getWindowHandles();
                for (String handle : windowHandles) {
                    DriverFactory.getDriver().switchTo().window(handle);
                }

                // Capture the screenshot
                String screenshotPath = ScreenshotUtils.captureScreenshot(DriverFactory.getDriver(), "failure");
                ExtentReportManager.getTest().fail("Scenario Failed: " + scenario.getName())
                        .addScreenCaptureFromPath(screenshotPath);
            } catch (Exception e) {
                ExtentReportManager.getTest().fail("Failed to capture screenshot: " + e.getMessage());
            }
        }

    }*/
}