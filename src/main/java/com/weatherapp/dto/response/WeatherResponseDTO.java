package com.weatherapp.dto.response;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@Getter
@Builder
public class WeatherResponseDTO {

    private String description;

    private double temp;

    private double feelsLike;

    private double tempMin;

    private double tempMax;

    private int pressure;

    private int humidity;

    private int visibility;

    private double windSpeed;

    private String name;

}

