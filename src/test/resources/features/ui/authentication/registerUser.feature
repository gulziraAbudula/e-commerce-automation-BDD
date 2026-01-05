Feature: User Registration
  As a new user of Automation Exercise
  I want to register a new account
  So that I can log in and access the website features


  @RegressionUITest @Smoke
  Scenario: TC1 - User should successfully register a new account
    Given User launches the browser
    And User navigates to 'http://automationexercise.com'
    Then User verifies that home page is visible successfully

    When User clicks on Signup Login button
    Then User verifies that New User Signup is visible "New User Signup!"

    When User enters the following signup details:
      | name  | email                 |
      | Johnaaaa  | john.doeaaaaa@example.com  |
    And User clicks Signup button
    Then User verifies that Enter Account Information is visible

    When User fills the following account information:
      | fieldName | value       |
      | title     | Mr.         |
      | password  | Password123 |
      | dob_day   | 15          |
      | dob_month | January     |
      | dob_year  | 1990        |
    And User selects checkbox 'Sign up for our newsletter!'
    And User selects checkbox 'Receive special offers from our partners!'

    When User fills the following address details:
      | fieldName    | value             |
      | firstName    | John              |
      | lastName     | Doe               |
      | company      | Tech Corp         |
      | address      | 123 Main Street   |
      | address2     | Suite 100         |
      | country      | United States     |
      | state        | California        |
      | city         | San Francisco     |
      | zipcode      | 94102             |
      | mobileNumber | +1-415-555-0123   |
    And User clicks Create Account button
    Then User verifies that Account Created is visible

    When User clicks Continue button
    Then User verifies that 'Johnaaaa' is visible

    When User clicks Delete Account button
    Then User verifies that Account Deleted is visible
    And User clicks Continue2 button


  @Skip @RegressionUITest
  Scenario: TC1 - User should not be able to register with existing email
    Given User launches the browser
    And User navigates to 'http://automationexercise.com'
    Then User verifies that home page is visible successfully

    When User clicks on 'Signup / Login' button
    Then User verifies 'New User Signup!' is visible

    When User enters the following signup details:
      | name      | email                        |
      | Jane      | existing.user@example.com    |
    And User clicks 'Signup' button
    Then User verifies the error message 'Email Address already exist!' is displayed


  @Skip @RegressionUITest
  Scenario: TC1 - User should not be able to register without providing required fields
    Given User launches the browser
    And User navigates to 'http://automationexercise.com'
    Then User verifies that home page is visible successfully

    When User clicks on 'Signup / Login' button
    Then User verifies 'New User Signup!' is visible

    When User enters the following signup details:
      | name | email |
      |      |       |
    And User clicks 'Signup' button
    Then User verifies the error message 'All fields are required!' is displayed


  @Skip @RegressionUITest
  Scenario: TC1 - User should not be able to register with invalid email format
    Given User launches the browser
    And User navigates to 'http://automationexercise.com'
    Then User verifies that home page is visible successfully

    When User clicks on 'Signup / Login' button
    Then User verifies 'New User Signup!' is visible

    When User enters the following signup details:
      | name  | email      |
      | Alice | invalidemail |
    And User clicks 'Signup' button
    Then User verifies the error message 'Invalid email address.' is displayed
