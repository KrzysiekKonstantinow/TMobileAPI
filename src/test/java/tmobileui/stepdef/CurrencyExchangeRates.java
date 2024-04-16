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

    @Given("^I have the API endpoint to get exchange rates$")
    public void i_have_the_API_endpoint_to_get_exchange_rates() {
        RestAssured.baseURI = "http://api.nbp.pl";
        RestAssured.basePath = "/api/exchangerates/tables/A";
    }

    @When("^I make a GET request to get exchange rates$")
    public void i_make_a_GET_request_to_get_exchange_rates() {
        response = get();
        jsonPath = response.jsonPath();
    }

    @Then("^I should see the response code (\\d+)$")
    public void i_should_see_the_response_code(int expectedStatusCode) {
        assertEquals(expectedStatusCode, response.getStatusCode());
        System.out.println("Response code is: " + response.getStatusCode());
    }

    @Then("^I should display exchange rate for currency with code (\\w+)$")
    public void i_should_display_exchange_rate_for_currency_with_code(String currencyCode) {

        String exchangeRate = jsonPath.getString("[0].rates.find{it.code == '" + currencyCode + "'}.mid");
        assertNotNull(exchangeRate);
        System.out.println("Exchange rate for " + currencyCode + " is: " + exchangeRate);
    }

    @Then("^I should display exchange rate for currency with name (.*)$")
    public void i_should_display_exchange_rate_for_currency_with_name(String currencyName) {
        String exchangeRate = jsonPath.getString("[0].rates.find {it.currency == '" + currencyName + "' }.mid");
        assertNotNull(exchangeRate);
        System.out.println("Exchange rate for " + currencyName + ": " + exchangeRate);
    }

    @Then("^I should display currencies with exchange rate over: (\\d+)$")
    public void i_should_display_currencies_with_exchange_rate_over(int minValue) {
        System.out.println("Currencies with exchange rate over " + minValue + ":");
        jsonPath.getString("[0].rates.findAll { it.mid > " + minValue + " }.each { println it.currency }");
    }

    @Then("^I should display currencies with exchange rate under: (\\d+)$")
    public void i_should_display_currencies_with_exchange_rate_under(int maxValue) {
        System.out.println("Currencies with exchange rate under " + maxValue + ":");
        jsonPath.getString("[0].rates.findAll { it.mid < " + maxValue + " }.each { println it.currency }");
    }
}
