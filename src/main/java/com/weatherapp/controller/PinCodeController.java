package com.weatherapp.controller;

import com.weatherapp.dto.response.PinCodeResponseDTO;
import com.weatherapp.dto.response.WeatherResponseDTO;
import com.weatherapp.service.PinCodeService;
import com.weatherapp.service.WeatherService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/v1")
@RestController
@Slf4j
public class PinCodeController {

    @Autowired
    private PinCodeService pinCodeService;

    @Autowired
    private WeatherService weatherService;

    @GetMapping("/weather")
    public ResponseEntity<WeatherResponseDTO> getWeatherByPinCode(@RequestParam(required = true) String pinCode) {
        log.info("Received request to find weather for pinCode: {}", pinCode);

        try {
            PinCodeResponseDTO coordinates = pinCodeService.findCoordinatesFromPinCode(pinCode);
            log.info("Successfully retrieved coordinates: {}", coordinates);

            WeatherResponseDTO weather = weatherService.getWeather(coordinates.getLatitude(), coordinates.getLongitude());
            log.info("Successfully retrieved weather: {}", weather);
            return new ResponseEntity<>(weather, HttpStatus.OK);

        } catch (Exception e) {
            log.error("Error occurred while retrieving weather for pinCode: {}", pinCode, e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

}
