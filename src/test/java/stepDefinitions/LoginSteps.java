package stepDefinitions;

import com.aventstack.extentreports.Status;
import drivers.DriverFactory;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.Assert;
import pages.LoginPage;
import pages.BasePage;
import utils.ElementUtils;
import utils.ScreenshotUtils;

import static utils.ExtentReportManager.getTest;

public class LoginSteps {

    private final LoginPage loginPage = new LoginPage();

    @Given("I am on the OrangeHRM login page")
    public void i_am_on_the_orangehrm_login_page() {
        loginPage.waitForLoginPageToLoad();
        getTest().log(Status.INFO, "User navigated to the login page");
    }

    @When("I enter username {string} and password {string}")
    public void i_enter_username_and_password(String username, String password) {
        loginPage.login(username, password);
        getTest().log(Status.INFO, "User entered username: " + username + " and password: " + password);
    }

    @And("I click on the login button")
    public void i_click_on_the_login_button() {
        loginPage.clickLoginButton();
    }

   @Then("I should be redirected to the dashboard page")
    public void i_should_be_redirected_to_the_dashboard_page() {
        ElementUtils.waitForElementToBeVisible(loginPage.getDriver(), loginPage.dashboardPageElement);
        Assert.assertTrue(loginPage.isDashboardPageDisplayed(), "Dashboard page is not displayed");
        getTest().log(Status.PASS, "User successfully navigated to the dashboard");
        ScreenshotUtils.captureScreenshot(DriverFactory.getDriver(), "dashboard_page");
    }

    @Then("I should see an error message {string}")
    public void i_should_see_an_error_message(String errorMessage) {
        ElementUtils.waitForElementToBeVisible(loginPage.getDriver(), loginPage.errorMessageElement);
        //Assert.assertEquals(loginPage.getErrorMessage(), errorMessage, "Error message does not match");
        Assert.assertTrue(loginPage.isInvalidCredentialsMessageVisible(), "Error message is not displayed");
        getTest().log(Status.INFO, "Error message was displayed for invalid login attempt");
        ScreenshotUtils.captureScreenshot(DriverFactory.getDriver(), "Login_page_Invalid_Credentials");
    }
}