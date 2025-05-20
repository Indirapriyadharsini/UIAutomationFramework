package hooks;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.aventstack.extentreports.Status;
import io.cucumber.java.*;
import utils.ExtentReportManager;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import drivers.DriverFactory;
import utils.ScreenshotUtils;

public class ExtentHooks {

    private static ExtentReports extent = ExtentReportManager.getReporter();
    private static ThreadLocal<ExtentTest> testThread = new ThreadLocal<>();

    @BeforeAll
    public static void setupReport() {
        ExtentSparkReporter sparkReporter = new ExtentSparkReporter("test-output/ExtentReport.html");
        sparkReporter.config().setDocumentTitle("UI Automation Report");
        sparkReporter.config().setReportName("Login Tests");
        sparkReporter.config().setTheme(Theme.STANDARD);

        extent = new ExtentReports();
        extent.attachReporter(sparkReporter);
        extent.setSystemInfo("Tester", "Indirapriyadharsini");
    }
    @Before
    public void beforeScenario(Scenario scenario) {
        ExtentTest test = extent.createTest(scenario.getName());
        testThread.set(test);
    }

    @AfterStep
    public void afterStep(Scenario scenario) {
        if (scenario.isFailed()) {
            testThread.get().log(Status.FAIL, "Step failed");
        } else {
            testThread.get().log(Status.PASS, "Step passed");
        }
    }

   @After
    public void afterScenario(Scenario scenario) {
        if (scenario.isFailed()) {
            WebDriver driver = DriverFactory.getDriver();
            byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png", scenario.getName());

           /* String base64Screenshot = "data:image/png;base64," +
                    ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
            getTest().addScreenCaptureFromBase64String(base64Screenshot);*/
            String screenshotPath = ScreenshotUtils.captureScreenshot(DriverFactory.getDriver(), "failure");
            getTest().fail("Scenario Failed: " + scenario.getName())
                    .addScreenCaptureFromPath(screenshotPath);
        }
        extent.flush();
    }

    public static ExtentTest getTest() {
        return testThread.get();
    }
}
