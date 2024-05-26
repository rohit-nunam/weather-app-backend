package com.weatherapp.service;

import com.weatherapp.dto.response.WeatherResponseDTO;
import com.weatherapp.exception.DataNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@Slf4j
@Service
public class WeatherService {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${openweathermap.api.key}")
    private String openWeatherMapApiKey;

    public WeatherResponseDTO getWeather(double latitude, double longitude) {
        log.info("Fetching weather data for latitude: {}, longitude: {}", latitude, longitude);

        String apiUrl = "https://api.openweathermap.org/data/2.5/weather?lat={lat}&lon={lon}&appid={apiKey}&units=metric";
        Map<String, Object> response = restTemplate.getForObject(apiUrl, Map.class, latitude, longitude, openWeatherMapApiKey);

        if (Objects.isNull(response)) {
            log.error("Failed to fetch weather data: Response is null");
            throw new DataNotFoundException("Failed to fetch weather data");
        }

        Map<String, Object> main = (Map<String, Object>) response.get("main");
        Map<String, Object> wind = (Map<String, Object>) response.get("wind");
        List<Map<String, Object>> weatherList = (List<Map<String, Object>>) response.get("weather");

        if (Objects.isNull(main) || Objects.isNull(wind) || Objects.isNull(weatherList) || weatherList.isEmpty()) {
            log.error("Failed to fetch weather data: Incomplete data in response");
            throw new DataNotFoundException("Failed to fetch complete weather data");
        }

        Map<String, Object> weather = weatherList.get(0);

        WeatherResponseDTO weatherResponse = WeatherResponseDTO.builder()
                .description((String) weather.get("description"))
                .temp(getDoubleValue(main.get("temp")))
                .feelsLike(getDoubleValue(main.get("feels_like")))
                .tempMin(getDoubleValue(main.get("temp_min")))
                .tempMax(getDoubleValue(main.get("temp_max")))
                .pressure(getIntegerValue(main.get("pressure")))
                .humidity(getIntegerValue(main.get("humidity")))
                .visibility(getIntegerValue(response.get("visibility")))
                .windSpeed(getDoubleValue(wind.get("speed")))
                .name((String) response.get("name"))
                .build();

        log.info("Successfully fetched weather data: {}", weatherResponse);
        return weatherResponse;
    }

    private Double getDoubleValue(Object value) {
        return value instanceof Number ? ((Number) value).doubleValue() : null;
    }

    private Integer getIntegerValue(Object value) {
        return value instanceof Number ? ((Number) value).intValue() : null;
    }
}
