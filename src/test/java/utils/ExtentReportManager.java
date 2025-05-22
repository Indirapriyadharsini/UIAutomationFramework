// src/test/java/utils/ReportManager.java
package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import config.ConfigReader;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ExtentReportManager {
    private static ExtentReports extent;
    private static ThreadLocal<ExtentTest> testThread = new ThreadLocal<>();

    static {
        String timestamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        String reportPath = "test-output/Report/ExtentReport" + "_" + timestamp + ".html";
        ExtentSparkReporter sparkReporter = new ExtentSparkReporter(reportPath);
        sparkReporter.config().setDocumentTitle("UI Automation Report");
        sparkReporter.config().setReportName("UI Tests");
        sparkReporter.config().setTheme(Theme.STANDARD);


        extent = new ExtentReports();
        extent.attachReporter(sparkReporter);
        extent.setSystemInfo("Tester", ConfigReader.get("testerName"));
        extent.setSystemInfo("Environment", ConfigReader.get("environment"));
        extent.setSystemInfo("Browser", ConfigReader.get("browser"));
    }

    public static ExtentTest createTest(String testName) {
        ExtentTest test = extent.createTest(testName);
        testThread.set(test);
        return test;
    }

    public static ExtentTest getTest() {
        return testThread.get();
    }

    public static void flush() {
        extent.flush();
    }
}