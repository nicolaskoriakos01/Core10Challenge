package com.core10.challenge.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class WeatherForecastResponseMap {
    private String date;
    private int temperatureC;
    private int temperatureF;
    private String summary;
}
