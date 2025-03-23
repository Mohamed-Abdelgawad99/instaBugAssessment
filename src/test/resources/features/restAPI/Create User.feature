Feature: API Testing

  @ID001
  Scenario: Create a new user and get the correct user data
    When I send a POST request to "/users" with:
      | name  | John Doe            |
      | email | johndoe@example.com |
      | id    | 555                 |
    Then the response status should be 202
    And the response body should contain:
      | name  | John Doe            |
      | email | johndoe@example.com |

#    to generate and run allure reports allure generate -o allure-report then allure serve