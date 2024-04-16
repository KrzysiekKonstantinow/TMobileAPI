package tmobileui.stepdef;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.en.And;
import io.restassured.RestAssured;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;
import static org.junit.Assert.*;
public class CurrencyExchangeRates {

    private Response response;
    private JsonPath jsonPath;

    @Given("^API endpoint to get exchange rates is known$")
    public void api_endpoint_to_get_exchange_rates_is_known() {
        RestAssured.baseURI = "http://api.nbp.pl";
        RestAssured.basePath = "/api/exchangerates/tables/A";
    }

    @When("^GET request to get exchange rates is made$")
    public void get_request_to_get_exchange_rates_is_made() {
        response = get();
        jsonPath = response.jsonPath();
    }

    @Then("^The response code (\\d+) should be displayed$")
    public void the_response_code_should_be_displayed(int expectedStatusCode) {
        assertEquals(expectedStatusCode, response.getStatusCode());
        System.out.println("Response code is: " + response.getStatusCode());
    }

    @Then("^Exchange rate for currency with code (\\w+) should be displayed$")
    public void exchange_rate_for_currency_with_code_should_be_displayed(String currencyCode) {

        String exchangeRate = jsonPath.getString("[0].rates.find{it.code == '" + currencyCode + "'}.mid");
        assertNotNull(exchangeRate);
        System.out.println("Exchange rate for " + currencyCode + " is: " + exchangeRate);
    }

    @Then("^Exchange rate for currency with name (.*) should be displayed$")
    public void exchange_rate_for_currency_with_name_should_be_displayed(String currencyName) {
        String exchangeRate = jsonPath.getString("[0].rates.find {it.currency == '" + currencyName + "' }.mid");
        assertNotNull(exchangeRate);
        System.out.println("Exchange rate for " + currencyName + ": " + exchangeRate);
    }

    @Then("^Currencies with exchange rate over: (\\d+) should be displayed$")
    public void currencies_with_exchange_rate_over_should_be_displayed(int minValue) {
        System.out.println("Currencies with exchange rate over " + minValue + ":");
        jsonPath.getString("[0].rates.findAll { it.mid > " + minValue + " }.each { println it.currency }");
    }

    @Then("^Currencies with exchange rate under: (\\d+) should be displayed$")
    public void currencies_with_exchange_rate_under_should_be_displayed(int maxValue) {
        System.out.println("Currencies with exchange rate under " + maxValue + ":");
        jsonPath.getString("[0].rates.findAll { it.mid < " + maxValue + " }.each { println it.currency }");
    }
}
