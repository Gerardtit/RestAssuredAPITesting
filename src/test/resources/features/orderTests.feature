# new feature
# Tags: optional

Feature: Orders tests

  @Test @Order
  Scenario Outline: Order a book
    When  user calls Post order a book by id "<bookId>"
    Then  validate response is 201
    And   validate response status is Created
    And   user saves orderId
    Examples:
      |bookId      |
      |1           |

  @Test
  Scenario: Get all orders
    When  user calls Get all books orders
    Then  validate response is 200
    And   validate response status is OK

  @Test
  Scenario: Get order by id
    When  user calls Get order by id
    Then  validate response is 200
    And   validate response status is OK

  @Test
  Scenario: Update order user name by id
    When  user calls Update order user name by id
    Then  validate response is 204
    And   validate response status is No Content
    And   user calls Get order by id
    And   validate updated user name

  @Test
  Scenario: Delete an order by id
    When  user calls Delete an order by id
    Then  validate response is 204
    And   validate response status is No Content
    And   user calls Get order by id
    And   validate response is 404
    And   validate error message for no existing order by id
