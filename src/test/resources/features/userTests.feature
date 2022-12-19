Feature: User tests

  @Test
  Scenario: Create new user
    Given user sets the base API request "base"
    And   user sets post data for creating an user
    When  user calls Post Customer API
    Then  validate response is 201

#    And   validate response status is OK