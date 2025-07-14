Feature: Validating Place APIs

  @AddPlace
  Scenario Outline: Verify Place is added using AddPlaceAPI
    Given AddPlace payload with "<name>"
    When User calls "AddPlaceAPI" using "POST" http method
    Then the API call is successful with 200 status code
    And Status attribute on response body is OK

  Examples:
    |name        |
    |Queen Palace|

  @GetPlace
  Scenario Outline: Verify place is created using GetPlaceAPI
    Given Place_id from AddPlaceAPI
    When User calls "GetPlaceAPI" using "GET" http method
    Then the API call is successful with 200 status code
    And "<name>" attribute on response body matches name attribute from AddPlaceAPI

    Examples:
      | name         |
      | Queen Palace |

  @DeletePlace
  Scenario: Verify place is deleted using DeletePlaceAPI
    Given DeletePlace payload
    When User calls "DeletePlaceAPI" using "POST" http method
    Then the API call is successful with 200 status code
    And Status attribute on response body is OK
