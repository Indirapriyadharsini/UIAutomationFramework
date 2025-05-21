package pages;

    import org.openqa.selenium.WebElement;
    import org.openqa.selenium.support.FindBy;
    import utils.ElementUtils;

    public class LoginPage extends BasePage {

        @FindBy(xpath = "//*[@name='username']")
        private WebElement usernameInput;

        @FindBy(xpath = "//*[@name='password']")
        private WebElement passwordInput;

        @FindBy(xpath = "//*[@type='submit']")
        private WebElement loginButton;

        @FindBy(xpath = "//p[text()='Invalid credentials']")
        public WebElement errorMessageElement;

        @FindBy(xpath = "//h6[text()='Dashboard']")
        public WebElement dashboardPageElement;

        public void waitForLoginPageToLoad() {
            ElementUtils.waitForElementToBeVisible(driver, usernameInput);
        }

        public void login(String username, String password) {
            ElementUtils.enterTextWithWait(driver, usernameInput, username);
            ElementUtils.enterTextWithWait(driver, passwordInput, password);
        }

        public void clickLoginButton() {
            ElementUtils.clickWithWait(driver, loginButton);
        }

        public String getErrorMessage() {
            return errorMessageElement.getText();
        }

        public boolean isInvalidCredentialsMessageVisible() {
            return errorMessageElement.isDisplayed();
        }
        public void waitForDashboardPageToLoad() {
            ElementUtils.waitForElementToBeVisible(driver, dashboardPageElement);
        }
        public boolean isDashboardPageDisplayed() {
            return driver.getCurrentUrl().contains("dashboard");
        }

    }