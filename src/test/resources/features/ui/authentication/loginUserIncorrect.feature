Feature: User Login with Incorrect Credentials
  As a user of Automation Exercise
  I want to receive proper error messages when I login with incorrect credentials
  So that I know my login attempt failed and can retry with correct credentials


  @Skip @RegressionUITest @Smoke
  Scenario: TC3 - User should not be able to login with incorrect email and password
    Given User launches the browser
    And User navigates to 'http://automationexercise.com'
    Then User verifies that home page is visible successfully

    When User clicks on 'Signup / Login' button
    Then User verifies 'Login to your account' is visible

    When User enters the following login credentials:
      | email                  | password           |
      | incorrect@example.com  | IncorrectPassword  |
    And User clicks 'login' button
    Then User verifies the error message 'Your email or password is incorrect!' is displayed


  @Skip @RegressionUITest
  Scenario: TC3 - User should not be able to login with incorrect email only
    Given User launches the browser
    And User navigates to 'http://automationexercise.com'
    Then User verifies that home page is visible successfully

    When User clicks on 'Signup / Login' button
    Then User verifies 'Login to your account' is visible

    When User enters the following login credentials:
      | email                     | password    |
      | nonexistent@example.com   | Password123 |
    And User clicks 'login' button
    Then User verifies the error message 'Your email or password is incorrect!' is displayed


  @Skip @RegressionUITest
  Scenario: TC3 - User should not be able to login with incorrect password only
    Given User launches the browser
    And User navigates to 'http://automationexercise.com'
    Then User verifies that home page is visible successfully

    When User clicks on 'Signup / Login' button
    Then User verifies 'Login to your account' is visible

    When User enters the following login credentials:
      | email                | password          |
      | john.doe@example.com | IncorrectPassword |
    And User clicks 'login' button
    Then User verifies the error message 'Your email or password is incorrect!' is displayed


  @Skip @RegressionUITest
  Scenario: TC3 - User should not be able to login with case-sensitive incorrect password
    Given User launches the browser
    And User navigates to 'http://automationexercise.com'
    Then User verifies that home page is visible successfully

    When User clicks on 'Signup / Login' button
    Then User verifies 'Login to your account' is visible

    When User enters the following login credentials:
      | email                | password    |
      | john.doe@example.com | password123 |
    And User clicks 'login' button
    Then User verifies the error message 'Your email or password is incorrect!' is displayed


  @Skip @RegressionUITest
  Scenario: TC3 - User should not be able to login with special characters in password
    Given User launches the browser
    And User navigates to 'http://automationexercise.com'
    Then User verifies that home page is visible successfully

    When User clicks on 'Signup / Login' button
    Then User verifies 'Login to your account' is visible

    When User enters the following login credentials:
      | email                | password            |
      | john.doe@example.com | P@ssw0rd!@#$%^&*() |
    And User clicks 'login' button
    Then User verifies the error message 'Your email or password is incorrect!' is displayed


  @Skip @RegressionUITest
  Scenario: TC3 - User should not be able to login with spaces in email and password
    Given User launches the browser
    And User navigates to 'http://automationexercise.com'
    Then User verifies that home page is visible successfully

    When User clicks on 'Signup / Login' button
    Then User verifies 'Login to your account' is visible

    When User enters the following login credentials:
      | email                      | password         |
      |  john.doe@example.com      |  Password123     |
    And User clicks 'login' button
    Then User verifies the error message 'Your email or password is incorrect!' is displayed


  @Skip @RegressionUITest
  Scenario: TC3 - User should see error message on invalid login attempt and be able to retry
    Given User launches the browser
    And User navigates to 'http://automationexercise.com'
    Then User verifies that home page is visible successfully

    When User clicks on 'Signup / Login' button
    Then User verifies 'Login to your account' is visible

    When User enters the following login credentials:
      | email              | password       |
      | wrong@example.com  | WrongPassword  |
    And User clicks 'login' button
    Then User verifies the error message 'Your email or password is incorrect!' is displayed

    When User clears the email field
    And User clears the password field
    And User enters the following login credentials:
      | email                | password    |
      | john.doe@example.com | Password123 |
    And User clicks 'login' button
    Then User verifies that 'Logged in as john.doe' is visible