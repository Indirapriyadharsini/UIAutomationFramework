package stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import org.slf4j.ILoggerFactory;
import org.testng.Assert;
import pages.LoginPage;
import pages.BasePage;
import utils.WaitUtils;

public class LoginSteps {

    private final LoginPage loginPage = new LoginPage();
    private final BasePage basePage = new BasePage() {
    };

    @Given("I am on the OrangeHRM login page")
    public void i_am_on_the_orangehrm_login_page() {
        loginPage.waitForLoginPageToLoad();
    }

    @When("I enter username {string} and password {string}")
    public void i_enter_username_and_password(String username, String password) {
        loginPage.login(username, password);
    }

    @And("I click on the login button")
    public void i_click_on_the_login_button() {
        loginPage.clickLoginButton();
    }

    @Then("I should be redirected to the dashboard page")
    public void i_should_be_redirected_to_the_dashboard_page() {
        Assert.assertTrue(basePage.isDashboardPageDisplayed(), "Dashboard page is not displayed");
        System.out.println("Dashboard page is displayed");
    }

    @Then("I should see an error message {string}")
    public void i_should_see_an_error_message(String errorMessage) {
        // Wait for the error message to be visible
        WaitUtils.waitForVisibility(loginPage.driver, loginPage.errorMessageElement);
        Assert.assertEquals(loginPage.getErrorMessage(), errorMessage, "Error message does not match");
        System.out.println("Error message is displayed: " + errorMessage);
    }
}