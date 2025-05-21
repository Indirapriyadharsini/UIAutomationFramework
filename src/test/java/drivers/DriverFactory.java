package drivers;

            import org.openqa.selenium.WebDriver;
            import org.openqa.selenium.chrome.ChromeDriver;
            import org.openqa.selenium.firefox.FirefoxDriver;
            import config.ConfigReader;

            public class DriverFactory {
                private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

                public static WebDriver getDriver() {
                    if (driver.get() == null) {
                        initializeDriver();
                    }
                    return driver.get();
                }

                private static void initializeDriver() {
                    String browser = ConfigReader.get("browser").toLowerCase();
                    switch (browser) {
                        case "chrome":
                            driver.set(new ChromeDriver());
                            break;
                        case "firefox":
                            driver.set(new FirefoxDriver());
                            break;
                        default:
                            throw new RuntimeException("Unsupported browser: " + browser);
                    }
                    driver.get().manage().window().maximize();
                }

                public static void quitDriver() {
                    if (driver.get() != null) {
                        driver.get().quit();
                        driver.remove();
                    }
                }
            }