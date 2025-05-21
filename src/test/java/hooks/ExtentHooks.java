// src/test/java/hooks/ExtentHooks.java
package hooks;

import io.cucumber.java.*;
import utils.ExtentReportManager;
import utils.ScreenshotUtils;
import drivers.DriverFactory;

public class ExtentHooks {

    @Before
    public void beforeScenario(Scenario scenario) {
        ExtentReportManager.createTest(scenario.getName());
    }

    @AfterStep
    public void afterStep(Scenario scenario) {
        if (scenario.isFailed()) {
            ExtentReportManager.getTest().fail("Step failed");
        } else {
            ExtentReportManager.getTest().pass("Step passed");
        }
    }

    @After
    public void afterScenario(Scenario scenario) {
        if (scenario.isFailed()) {
            String screenshotPath = ScreenshotUtils.captureScreenshot(DriverFactory.getDriver(), "failure");
            ExtentReportManager.getTest().fail("Scenario Failed: " + scenario.getName())
                    .addScreenCaptureFromPath(screenshotPath);
        }
        ExtentReportManager.flush();
    }
}