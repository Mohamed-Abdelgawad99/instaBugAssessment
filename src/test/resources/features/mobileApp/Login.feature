Feature: Mobile Login Functionality



  @ID001
  Scenario: Successful login with valid credentials
    Given user opens mobile application
    Then User is redirected to "login" page
    When User enters "username" as username
    And User enter "password123" as password
    And User clicks on "login" button
    Then User is redirected to "Home" page