# new feature
# Tags: optional

Feature: Tests for books

  Scenario: List of books response is 200
    Given user sets the base API request "base"
    When  user calls Gets List of books type "non-fiction"
    Then  validate response is 200
    And   validate response status is OK

  @Test
  Scenario Outline: List of books response has a book available
    Given user sets the base API request "base"
    When  user calls Gets List of books type "<type>"
    And   user filter books which are available "<available>"
    Then  validate response is 200
    And   validate response status is OK
    And   validate book title is "<title>"
    And   validate book type is "<type>"
  Examples:
  |type            | title      | available |
  |non-fiction     | Untamed    | true      |
  |fiction         | The Russian| true      |

  Scenario: Get single book by id
    Given user sets the base API request "base"
    When  user calls Gets Single of book by id "1"
    Then  validate response is 200
    And   validate response status is OK
    And   validate book has stock