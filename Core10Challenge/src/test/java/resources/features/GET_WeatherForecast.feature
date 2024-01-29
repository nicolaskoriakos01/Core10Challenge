Feature: Weather Forecast

  @T001 @Regression @WeatherServices @WeatherForecast
  Scenario: Validate GET Happy Path weather-forecast
    When I perform a 'GET' request to 'weather-forecast' endpoint
    Then Verify response status code is 200
    And Validate WeatherForecast Data
