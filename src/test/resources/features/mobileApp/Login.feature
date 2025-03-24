Feature: Mobile Login Functionality



  @ID001
  Scenario: Successful login with valid credentials
    Given user opens mobile application
    Then User is redirected to "login" page
    When User enters "username" as username
    And User enter "password123" as password
    And User clicks on "login" button
    Then User is redirected to "Home" page
#---------------------------- Sample testcase coverage for the login functionality ------------------------------
  @ID002
  Scenario: Login with invalid username
    Given user opens mobile application
    Then User is redirected to "login" page
    When User enters "wrongUser" as username
    And User enter "validPassword123" as password
    And User clicks on "login" button
    Then Error message "Invalid username or password" is displayed

  @ID003
  Scenario: Login with invalid password
    Given user opens mobile application
    Then User is redirected to "login" page
    When User enters "validUser" as username
    And User enter "wrongPassword" as password
    And User clicks on "login" button
    Then Error message "Invalid username or password" is displayed

  @ID004
  Scenario: Login with both username and password invalid
    Given user opens mobile application
    Then User is redirected to "login" page
    When User enters "invalidUser" as username
    And User enter "invalidPassword" as password
    And User clicks on "login" button
    Then Error message "Invalid username or password" is displayed

  @ID005
  Scenario: Attempt to login with empty username
    Given user opens mobile application
    Then User is redirected to "login" page
    When User enters "" as username
    And User enter "somePassword" as password
    And User clicks on "login" button
    Then Error message "Username is required" is displayed

  @ID006
  Scenario: Attempt to login with empty password
    Given user opens mobile application
    Then User is redirected to "login" page
    When User enters "someUser" as username
    And User enter "" as password
    And User clicks on "login" button
    Then Error message "Password is required" is displayed

  @ID007
  Scenario: Attempt to login with both fields empty
    Given user opens mobile application
    Then User is redirected to "login" page
    When User enters "" as username
    And User enter "" as password
    And User clicks on "login" button
    Then Error banner with text "Invalid username or password" is visible

  @ID008
  Scenario: Login with special characters in username
    Given user opens mobile application
    Then User is redirected to "login" page
    When User enters "user@#%!" as username
    And User enter "validPassword123" as password
    And User clicks on "login" button
    Then Error message "Invalid characters in username" is displayed

  @ID009
  Scenario: Login with very long credentials
    Given user opens mobile application
    Then User is redirected to "login" page
    When User enters "verylongusernamethatiswaytoolongforthefield" as username
    And User enter "averylongpasswordinputthatmayoverflow" as password
    And User clicks on "login" button
    Then Error message "Input exceeds maximum length" is displayed

  @ID010
  Scenario: Login button disabled when fields are empty
    Given user opens mobile application
    Then User is redirected to "login" page
    And Login button is disabled by default

  @ID011
  Scenario: Login button becomes enabled after valid input
    Given user opens mobile application
    Then User is redirected to "login" page
    When User enters "validUser" as username
    And User enter "validPassword123" as password
    Then Login button becomes enabled