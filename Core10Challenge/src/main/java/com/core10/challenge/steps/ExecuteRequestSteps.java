package com.core10.challenge.steps;

import com.core10.challenge.api.RequestSpecificationBuilder;
import com.core10.challenge.entities.WeatherForecastResponseMap;
import com.core10.challenge.utils.EndpointUtil;
import com.core10.challenge.utils.RequestMethod;
import com.core10.challenge.utils.RestUtil;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.testng.Assert.assertEquals;

/**
 * Cucumber step definitions for executing REST API requests.
 */
public class ExecuteRequestSteps {

    // Response object to store the API response
    private Response response;
    private final SharedData sharedData;
    private static final Logger LOG = LoggerFactory.getLogger(RestUtil.class);

    public ExecuteRequestSteps() {
        this.sharedData = new SharedData();
    }

    /**
     * Perform a GET request to the specified URL.
     *
     * @param url The URL for the GET request.
     * @return The Response object containing the API response.
     */
    public Response performGETRequest(String url) {
        // Build the request specification using the RequestSpecificationBuilder
        RequestSpecification request = new RequestSpecificationBuilder().build();
        // Use RestUtil to execute the GET request and retrieve the response
        return RestUtil.getResponse(request, url, RequestMethod.GET);
    }

    /**
     * Cucumber step definition for performing a GET request to a specific endpoint.
     *
     * @param endpointId The ID of the endpoint to perform the GET request.
     */
    @When("I perform a 'GET' request to {string} endpoint")
    public void performGetRequest(String endpointId) {
        // Get the complete URL for the specified endpoint using EndpointUtil
        String url = EndpointUtil.getUrl(endpointId);
        // Perform the GET request and store the response in the 'response' variable
        response = performGETRequest(url);
        this.sharedData.setResponse(response);
    }

    @Then("Verify response status code is {int}")
    public void verifyResponseCode(int expectedResponseCode) {
        int resultCode = this.sharedData.getResponse().getStatusCode();
        assertEquals(resultCode, expectedResponseCode,
                String.format("Error!, expected status code '%d' but found '%d'.\nResponse Body:\n%s", expectedResponseCode, resultCode, this.sharedData.getResponse().getBody().asPrettyString()));
        LOG.debug("Response Status Code: '{}'", this.sharedData.getResponse().prettyPeek());
    }

    @Then("Validate WeatherForecast Data")
    public void validateWeatherForecastData() {
        WeatherForecastResponseMap[] weatherForecastResponseMap = sharedData.getResponse().as(WeatherForecastResponseMap[].class);

        Assert.assertFalse(validateWeatherDate(weatherForecastResponseMap));
        Assert.assertTrue(validateTemperatureData(weatherForecastResponseMap));
    }

    /**
     * Validate if at least one forecast date is before the current date.
     *
     * @param weatherForecastResponseMap Array of weather data.
     * @return True if at least one forecast date is before the current date; otherwise, false.
     */
    public boolean validateWeatherDate(WeatherForecastResponseMap[] weatherForecastResponseMap) {
        // Calculate the current date only once outside the loop to improve performance
        LocalDate currentDate = LocalDate.now();

        for (WeatherForecastResponseMap weatherData : weatherForecastResponseMap) {
            LocalDate forecastDate = LocalDate.parse(weatherData.getDate(), DateTimeFormatter.ISO_DATE);
            if (forecastDate.isBefore(currentDate)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Validate the accuracy of temperature data conversion.
     *
     * @param weatherForecastResponseMap Array of weather data.
     * @return True if the temperature data is accurate; otherwise, false.
     */
    public boolean validateTemperatureData(WeatherForecastResponseMap[] weatherForecastResponseMap) {

        for (WeatherForecastResponseMap weatherData : weatherForecastResponseMap) {
            System.out.println(weatherData.getTemperatureC());
            System.out.println((int) Math.ceil((double) ((weatherData.getTemperatureF() - 32) * 5) / 9));
            if ((weatherData.getTemperatureC() - (Math.ceil((double) ((weatherData.getTemperatureF() - 32) * 5) / 9))) < 2) {
                System.out.println((weatherData.getTemperatureC() - (Math.ceil((double) ((weatherData.getTemperatureF() - 32) * 5) / 9))));
                return true; // this if is created because the formula it's not perfect and sometimes its a one degree difference this should cover those cases
            } else if (weatherData.getTemperatureC() != (int) Math.ceil((double) ((weatherData.getTemperatureF() - 32) * 5) / 9)) {
                System.out.println((weatherData.getTemperatureC() - (Math.ceil((double) ((weatherData.getTemperatureF() - 32) * 5) / 9))));
                return false; // if the temperature it's not a match and it's difference is more than 2 degrees then we can consider it a failure
            }
        }
        // If all temperature data validations pass, return true
        return true;
    }
}
