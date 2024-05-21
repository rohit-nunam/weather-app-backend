package com.weatherapp.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class GeocodingResponse {

    private List<Result> results;

    private String status;

}
