package com.weatherapp.service;

import com.weatherapp.dto.GeocodingResponse;
import com.weatherapp.dto.Location;
import com.weatherapp.dto.response.PinCodeResponseDTO;
import com.weatherapp.entity.PinCodeInfo;
import com.weatherapp.repository.PinCodeInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;

@Service
public class PinCodeService {

    @Value("${google.maps.api.key}")
    private String apiKey;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private PinCodeInfoRepository pinCodeInfoRepository;

    public PinCodeResponseDTO findCoordinatesFromPinCode(String pinCode) {

        String apiUrl = "https://maps.googleapis.com/maps/api/geocode/json?address={pinCode}&key={apiKey}";

        GeocodingResponse response = restTemplate.getForObject(apiUrl, GeocodingResponse.class, pinCode, apiKey);

        if (response != null && "OK".equals(response.getStatus()) && !response.getResults().isEmpty()) {
            Location location = response.getResults().get(0).getGeometry().getLocation();

            if (Objects.nonNull(location)) {
                PinCodeInfo pinCodeInfo = new PinCodeInfo(pinCode, location.getLat(), location.getLng());
                pinCodeInfoRepository.save(pinCodeInfo);

                return PinCodeResponseDTO.builder()
                        .latitude(location.getLat())
                        .longitude(location.getLng())
                        .build();
            }
        }
        throw new RuntimeException("Failed to retrieve coordinates for the given pinCode: " + pinCode);
    }

}
