Feature: E-Commerce Product Search Functionality


  @ID001
  Scenario: Search for laptop products on the E-commerce website
    Given user opens the E-commerce web application
    Then user is redirected to homepage
    When user search for "Laptop" product
    Then search results page loads
    And check search result page has at least 1 results

