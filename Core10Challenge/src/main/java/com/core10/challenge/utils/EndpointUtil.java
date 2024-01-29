package com.core10.challenge.utils;

import lombok.Getter;

/**
 * Utility class for handling API endpoints.
 */
public class EndpointUtil {

    // Base URL for the API
    private static final String baseUrl = "http://localhost:8080";

    /**
     * Get the complete URL for a specific endpoint based on its ID.
     *
     * @param id The ID of the endpoint.
     * @return The complete URL for the specified endpoint.
     */
    public static String getUrl(String id) {
        // Delegate to the Endpoint enum to get the URL for the specified ID
        return Endpoint.getEndpoint(id).getUrl();
    }

    /**
     * Enum representing different API endpoints along with their IDs and URLs.
     */
    @Getter
    enum Endpoint {
        // Define API endpoints along with their IDs and URLs
        WEATHER_FORECAST("weather-forecast", baseUrl.concat("/WeatherForecast"));

        // Instance variables for ID and URL
        private final String id;
        private final String url;

        /**
         * Constructor to initialize an Endpoint with an ID and URL.
         *
         * @param id  The ID of the endpoint.
         * @param url The complete URL for the endpoint.
         */
        Endpoint(String id, String url) {
            this.id = id;
            this.url = url;
        }

        /**
         * Get the Endpoint enum corresponding to a specific ID.
         *
         * @param id The ID of the endpoint to retrieve.
         * @return The Endpoint enum corresponding to the given ID.
         */
        public static Endpoint getEndpoint(String id) {
            // Iterate through all enum values to find the matching endpoint
            for (Endpoint e : values()) {
                if (id.equalsIgnoreCase(e.getId())) {
                    return e;
                }
            }
            // Return null if no matching endpoint is found
            return null;
        }
    }
}
