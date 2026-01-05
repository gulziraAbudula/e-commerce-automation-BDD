Feature: Login User API using Karate
  As an API consumer
  I want to authenticate users via API endpoints
  So that users can access the application with valid credentials


  Background:
    * url 'http://automationexercise.com/api'
    * header Content-Type = 'application/json'


  @Smoke @API
  Scenario: API 7 - POST to verify login with valid email and password
    Given request { email: 'john.doe@example.com', password: 'Password123' }
    When method post
    And path '/login'
    Then status 200
    And match response.user_id != null
    And match response.token != null
    And match response.message == 'Login successful'


  @RegressionAPI
  Scenario: API 8 - POST to verify login without email parameter
    Given request { password: 'Password123' }
    When method post
    And path '/login'
    Then status 400
    And match response.message contains 'email parameter is required'


  @RegressionAPI
  Scenario: API 8 - POST to verify login without password parameter
    Given request { email: 'john.doe@example.com' }
    When method post
    And path '/login'
    Then status 400
    And match response.message contains 'password parameter is required'


  @RegressionAPI
  Scenario: API 8 - POST to verify login without both parameters
    Given request {}
    When method post
    And path '/login'
    Then status 400
    And match response.message contains 'email and password parameters are required'


  @RegressionAPI
  Scenario: API 10 - POST to verify login with invalid email and password
    Given request { email: 'incorrect@example.com', password: 'IncorrectPassword' }
    When method post
    And path '/login'
    Then status 401
    And match response.message contains 'Invalid email or password'


  @RegressionAPI
  Scenario: API 10 - POST to verify login with incorrect email only
    Given request { email: 'nonexistent@example.com', password: 'Password123' }
    When method post
    And path '/login'
    Then status 401
    And match response.message contains 'Invalid email or password'


  @RegressionAPI
  Scenario: API 10 - POST to verify login with incorrect password only
    Given request { email: 'john.doe@example.com', password: 'IncorrectPassword' }
    When method post
    And path '/login'
    Then status 401
    And match response.message contains 'Invalid email or password'


  @RegressionAPI
  Scenario: API 10 - POST to verify login with case-sensitive incorrect password
    Given request { email: 'john.doe@example.com', password: 'password123' }
    When method post
    And path '/login'
    Then status 401
    And match response.message contains 'Invalid email or password'


  @RegressionAPI
  Scenario: API 10 - POST to verify login with invalid email format
    Given request { email: 'invalidemail', password: 'Password123' }
    When method post
    And path '/login'
    Then status 400
    And match response.message contains 'Invalid email format'


  @RegressionAPI
  Scenario: API 10 - POST to verify login with spaces in credentials
    Given request { email: '  john.doe@example.com  ', password: '  Password123  ' }
    When method post
    And path '/login'
    Then status 401
    And match response.message contains 'Invalid email or password'


  @RegressionAPI
  Scenario: API 9 - DELETE to logout with valid authentication token
    # First, login to get the token
    Given request { email: 'john.doe@example.com', password: 'Password123' }
    When method post
    And path '/login'
    Then status 200
    * def token = response.token

    # Now logout using the token
    Given header Authorization = 'Bearer ' + token
    When method delete
    And path '/login'
    Then status 200
    And match response.message == 'Logged out successfully'


  @RegressionAPI
  Scenario: API 9 - DELETE to logout without authentication token
    When method delete
    And path '/login'
    Then status 401
    And match response.message contains 'Authentication required'


  @RegressionAPI
  Scenario: API 9 - DELETE to logout with invalid authentication token
    Given header Authorization = 'Bearer invalid_token_12345'
    When method delete
    And path '/login'
    Then status 401
    And match response.message contains 'Invalid or expired token'


  @RegressionAPI
  Scenario: API 7 - Verify login response structure and fields
    Given request { email: 'john.doe@example.com', password: 'Password123' }
    When method post
    And path '/login'
    Then status 200
    And match response == { user_id: '#number', email: '#string', name: '#string', token: '#string', token_expiry: '#string' }
    * def tokenRegex = /^[A-Za-z0-9\-._~+/]+=*$/
    And match response.token =~ tokenRegex


  @RegressionAPI
  Scenario: API 7 - POST to verify login with special characters in password
    Given request { email: 'john.doe@example.com', password: 'P@ssw0rd!@#$%^&*()' }
    When method post
    And path '/login'
    Then status 200
    And match response.token != null


  @RegressionAPI
  Scenario: API 7 - Verify login response does not contain sensitive data
    Given request { email: 'john.doe@example.com', password: 'Password123' }
    When method post
    And path '/login'
    Then status 200
    And match response.user_id != null
    And match response.email == 'john.doe@example.com'
    And match response.user_status == 'active'
    # Ensure password is NOT in response
    And match response !contains 'password'