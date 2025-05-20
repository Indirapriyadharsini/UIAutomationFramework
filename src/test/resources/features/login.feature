Feature: User Login

  As a registered user,
  I want to log in to the OrangeHRM application
  So that I can access my dashboard securely

  Background:
    Given I am on the OrangeHRM login page

  @Positive
  Scenario Outline: Successful login with valid credentials
    When I enter username "<username>" and password "<password>"
    And I click on the login button
    Then I should be redirected to the dashboard page

    Examples:
      | username | password  |
      | Admin    | admin123  |
      | admin    | admin123  |

  @Negative
  Scenario Outline: Login fails with invalid credentials
    When I enter username "<username>" and password "<password>"
    And I click on the login button
    Then I should see an error message "<error_message>"

    Examples:
      | username | password   | error_message                        |
      | Admin    | wrongpass  | Invalid credentials                  |
      | wronguser| admin123   | Invalid credentials                  |

