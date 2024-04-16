Feature: Zadanie2

  Scenario Outline: Currency exchange rates
    Given I have the API endpoint to get exchange rates
    When I make a GET request to get exchange rates
    Then I should see the response code <expectedStatusCode>
    And I should display exchange rate for currency with code <currencyCode>
    And I should display exchange rate for currency with name <currencyName>
    And I should display currencies with exchange rate over: <minValue>
    And I should display currencies with exchange rate under: <maxValue>

    Examples:
      | expectedStatusCode | currencyCode | currencyName       | minValue | maxValue |
      | 200                | USD          | dolar amerykański  | 5        | 3        |