Feature: API Testing

  @ID001
  Scenario: Create a new user and get the correct user data
    When I send a POST request to "/users" with:
      | name  | John Doe            |
      | email | johndoe@example.com |
    Then the response status should be 201
    And the response body should contain:
      | name  | John Doe            |
      | email | johndoe@example.com |
