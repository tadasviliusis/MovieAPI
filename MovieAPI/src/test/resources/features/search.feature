Feature: I want to get a list of movies

  Scenario: User makes a GET call to get a list of movies from their search word
    Given Client sends a Get request with a search word
    When Sends HTTP GET request
    Then User receives a list of movies