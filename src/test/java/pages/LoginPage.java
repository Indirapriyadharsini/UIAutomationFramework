package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.WaitUtils;

public class LoginPage extends BasePage {

    @FindBy(xpath = "//*[@name='username']")
    private WebElement usernameInput;

    @FindBy(xpath = "//*[@name='password']")
    private WebElement passwordInput;

    @FindBy(xpath = "//*[@type='submit']")
    private WebElement loginButton;

    @FindBy(xpath = "//p[text()='Invalid credentials']")
    //@FindBy(xpath = "//div[contains(@class, 'oxd-alert--error')]//p[contains(text(), 'Invalid credentials')]")
    public
    WebElement errorMessageElement;

    public void waitForLoginPageToLoad()
    {
        WaitUtils.waitForVisibility(driver, usernameInput);
    }
    /*public void login(String username, String password) {
        enterText(usernameInput, username);
        enterText(passwordInput, password);
    }
    public void clickLoginButton() {
        click(loginButton);
    }*/

    public void login(String username, String password) {
        enterTextWithWait(usernameInput, username);
        enterTextWithWait(passwordInput, password);
    }
    public void clickLoginButton() {
        clickWithWait(loginButton);
    }

    public String getErrorMessage() {
        return errorMessageElement.getText();
    }

    public boolean isInvalidCredentialsMessageVisible() {
        return errorMessageElement.isDisplayed();
    }

}