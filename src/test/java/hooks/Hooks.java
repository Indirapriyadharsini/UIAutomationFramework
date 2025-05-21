package hooks;

    import io.cucumber.java.After;
    import io.cucumber.java.Before;
    import io.cucumber.java.Scenario;
    import drivers.DriverFactory;
    import config.ConfigReader;

    public class Hooks {

        @Before
        public void setUp() {
            DriverFactory.getDriver().get(ConfigReader.get("baseUrl")); // Navigate to baseUrl
        }

        @After
        public void tearDown(Scenario scenario) {
            if (scenario.isFailed() || scenario.getSourceTagNames().contains("@CloseBrowser")) {
                DriverFactory.quitDriver();
            }
        }
    }