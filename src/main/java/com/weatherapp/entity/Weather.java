package com.weatherapp.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Weather {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "pinCodeInfo_id", referencedColumnName = "id")
    private PinCodeInfo pinCodeInfo;

    private String weatherData;

}
