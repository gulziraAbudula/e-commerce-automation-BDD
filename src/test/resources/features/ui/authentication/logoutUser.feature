Feature: User Logout
  As a logged-in user of Automation Exercise
  I want to be able to logout from my account
  So that my session is closed and I can ensure my account is secure


  @Skip @RegressionUITest @Smoke
  Scenario: TC4 - User should successfully logout from their account
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
    Then User verifies that user is navigated to login page
    And User verifies 'Login to your account' is visible


  @Skip @RegressionUITest
  Scenario: TC4 - Logged out user should not have access to account information
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
    Then User verifies that user is navigated to login page
    And User verifies that user cannot access account page without logging in


  @Skip @RegressionUITest
  Scenario: TC4 - User should be able to login again after logout
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
    Then User verifies that user is navigated to login page

    When User enters the following login credentials:
      | email                | password    |
      | john.doe@example.com | Password123 |
    And User clicks 'login' button
    Then User verifies that 'Logged in as john.doe' is visible


  @Skip @RegressionUITest
  Scenario: TC4 - Logout button should be visible only when user is logged in
    Given User launches the browser
    And User navigates to 'http://automationexercise.com'
    Then User verifies that home page is visible successfully
    And User verifies that 'Logout' button is not visible

    When User clicks on 'Signup / Login' button
    Then User verifies 'Login to your account' is visible

    When User enters the following login credentials:
      | email                | password    |
      | john.doe@example.com | Password123 |
    And User clicks 'login' button
    Then User verifies that 'Logged in as john.doe' is visible
    And User verifies that 'Logout' button is visible

    When User clicks 'Logout' button
    Then User verifies that user is navigated to login page
    And User verifies that 'Logout' button is not visible


  @Skip @RegressionUITest
  Scenario: TC4 - User logout from home page
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

    When User navigates to home page
    And User clicks 'Logout' button
    Then User verifies that user is navigated to login page
    And User verifies 'Login to your account' is visible


  @Skip @RegressionUITest
  Scenario: TC4 - User session should be cleared after logout
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
    Then User verifies that user is navigated to login page

    When User navigates back in browser history
    Then User should not be able to access previously logged-in pages
    And User should be redirected to login page


  @Skip @RegressionUITest
  Scenario: TC4 - User logout and verify cart is accessible but empty
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
    Then User verifies that user is navigated to login page

    When User navigates to 'Cart' page
    Then User verifies that cart page is visible successfully
    And User verifies that cart is empty