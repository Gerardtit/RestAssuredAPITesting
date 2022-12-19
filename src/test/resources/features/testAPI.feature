# new feature
# Tags: optional

Feature: Tests API

  Scenario Outline: User is able to validate the Github API Response
    Given user sets the base API request "<URL>"
    And   user authenticates the API request with <token>
    When  user sends the API request to get all repositories
    Then  user validates the response status code is <code>
    Examples:
      |URL                        | token      | code|
      |  https://api.github.com   | 12333      | 401 |

  @Test
  Scenario: Validate get request from sample book
    Given user sets the base API request "base"
    When  user calls get API status
    Then  validate response is 200
    And   validate response status is OK

