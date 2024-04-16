Feature: Zadanie2

  Scenario Outline: Currency exchange rates
    Given API endpoint to get exchange rates is known
    When GET request to get exchange rates is made
    Then The response code <expectedStatusCode> should be displayed
    And Exchange rate for currency with code <currencyCode> should be displayed
    And Exchange rate for currency with name <currencyName> should be displayed
    And Currencies with exchange rate over: <minValue> should be displayed
    And Currencies with exchange rate under: <maxValue> should be displayed

    Examples:
      | expectedStatusCode | currencyCode | currencyName       | minValue | maxValue |
      | 200                | USD          | dolar amerykański  | 5        | 3        |