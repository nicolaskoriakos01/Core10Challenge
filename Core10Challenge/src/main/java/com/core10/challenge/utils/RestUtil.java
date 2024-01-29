package com.core10.challenge.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

/**
 * Utility class for making REST API requests and logging responses.
 */
public class RestUtil {

    // Logger for logging information related to REST requests and responses
    private static final Logger LOG = LoggerFactory.getLogger(RestUtil.class);

    /**
     * Send a REST API request and log the response.
     *
     * @param request The RequestSpecification for the API request.
     * @param endPoint The endpoint of the API.
     * @param method The HTTP method (e.g., GET, POST, PUT, DELETE).
     * @return The Response object containing the API response.
     */
    public static Response getResponse(RequestSpecification request, String endPoint, RequestMethod method) {
        // Initialize the response variable
        Response response = null;

        // Log information about the API request being made
        LOG.info("Requesting a {} method to endpoint {}.", method.toString(), endPoint);

        // Perform the API request based on the specified HTTP method
        switch (method) {
            case POST:
                response = request.when().post(endPoint).then().extract().response();
                break;
            case GET:
                response = request.when().get(endPoint).then().extract().response();
                break;
            case DELETE:
                response = request.when().delete(endPoint).then().extract().response();
                break;
            case PUT:
                response = request.when().put(endPoint).then().extract().response();
                break;
            case PATCH:
                response = request.when().patch(endPoint).then().extract().response();
                break;
        }

        // Log information about the API response, including the status code
        LOG.info("Response Status Code: {}.", response.getStatusCode());

        // Return the API response
        return response;
    }
}
