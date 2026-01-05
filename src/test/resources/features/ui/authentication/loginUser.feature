Feature: User Login with Correct Credentials
  As a registered user of Automation Exercise
  I want to log in with my correct credentials
  So that I can access my account and perform activities on the website


  @Skip @RegressionUITest @Smoke
  Scenario: TC2 - User should successfully login with correct email and password
    Given User launches the browser
    And User navigates to 'http://automationexercise.com'
    Then User verifies that home page is visible successfully

    When User clicks on 'Signup / Login' button
    Then User verifies 'Login to your account' is visible

    When User enters the following login credentials:
      | email                    | password    |
      | john.doe@example.com     | Password123 |
    And User clicks 'login' button
    Then User verifies that 'Logged in as john.doe' is visible

    When User clicks 'Delete Account' button
    Then User verifies that 'ACCOUNT DELETED!' is visible


  @Skip @RegressionUITest
  Scenario: TC2 - User should not be able to login with incorrect password
    Given User launches the browser
    And User navigates to 'http://automationexercise.com'
    Then User verifies that home page is visible successfully

    When User clicks on 'Signup / Login' button
    Then User verifies 'Login to your account' is visible

    When User enters the following login credentials:
      | email                | password       |
      | john.doe@example.com | WrongPassword  |
    And User clicks 'login' button
    Then User verifies the error message 'Your email or password is incorrect.' is displayed


  @Skip @RegressionUITest
  Scenario: TC2 - User should not be able to login with incorrect email
    Given User launches the browser
    And User navigates to 'http://automationexercise.com'
    Then User verifies that home page is visible successfully

    When User clicks on 'Signup / Login' button
    Then User verifies 'Login to your account' is visible

    When User enters the following login credentials:
      | email                 | password    |
      | wrong.email@test.com  | Password123 |
    And User clicks 'login' button
    Then User verifies the error message 'Your email or password is incorrect.' is displayed


  @Skip @RegressionUITest
  Scenario: TC2 - User should not be able to login without providing email and password
    Given User launches the browser
    And User navigates to 'http://automationexercise.com'
    Then User verifies that home page is visible successfully

    When User clicks on 'Signup / Login' button
    Then User verifies 'Login to your account' is visible

    When User enters the following login credentials:
      | email | password |
      |       |          |
    And User clicks 'login' button
    Then User verifies the error message 'All fields are required!' is displayed


  @Skip @RegressionUITest
  Scenario: TC2 - User should not be able to login with invalid email format
    Given User launches the browser
    And User navigates to 'http://automationexercise.com'
    Then User verifies that home page is visible successfully

    When User clicks on 'Signup / Login' button
    Then User verifies 'Login to your account' is visible

    When User enters the following login credentials:
      | email        | password    |
      | invalidemail | Password123 |
    And User clicks 'login' button
    Then User verifies the error message 'Invalid email address.' is displayed


  @Skip @RegressionUITest
  Scenario: TC2 - User should be able to logout after login
    Given User launches the browser
    And User navigates to 'http://automationexercise.com'
    Then User verifies that home page is visible successfully

    When User clicks on 'Signup / Login' button
    Then User verifies 'Login to your account' is visible

    When User enters the following login credentials:
      | email                | password    |
      | john.doe@example.com | Password123 |
    And User clicks 'login' button
    Then User verifies that 'Logged in as john.doe' is visible

    When User clicks 'Logout' button
    Then User verifies that home page is visible successfully
    And User verifies 'Login to your account' is no longer visible