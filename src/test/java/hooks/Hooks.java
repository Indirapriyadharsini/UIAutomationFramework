package hooks;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import drivers.DriverFactory;

public class Hooks {

    @Before
    public void setUp() {
        DriverFactory.initializeDriver();
    }

    @After
    public void tearDown(Scenario scenario) {
        // Check if the scenario is failed or has specific tags
        if (scenario.isFailed() || scenario.getSourceTagNames().contains("@CloseBrowser")) {
            DriverFactory.quitDriver();
        }
    }
}