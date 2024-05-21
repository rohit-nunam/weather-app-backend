package com.weatherapp.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "address_info")
@Entity
@Getter
@Setter
@NoArgsConstructor
public class PinCodeInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String pinCode;

    private Double latitude;

    private Double longitude;

    public PinCodeInfo(String pinCode, Double latitude, Double longitude) {
        this.pinCode = pinCode;
        this.latitude = latitude;
        this.longitude = longitude;
    }
}
