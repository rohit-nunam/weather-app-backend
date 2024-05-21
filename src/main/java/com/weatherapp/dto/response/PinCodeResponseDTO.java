package com.weatherapp.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class PinCodeResponseDTO {

    private Double latitude;

    private double longitude;

}
