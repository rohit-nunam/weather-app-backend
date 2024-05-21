package com.weatherapp.controller;

import com.weatherapp.dto.response.PinCodeResponseDTO;
import com.weatherapp.service.PinCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/v1")
@RestController
public class PinCodeController {

    @Autowired
    private PinCodeService pinCodeService;

    @GetMapping("/coordinates")
    public ResponseEntity<PinCodeResponseDTO> findLatLongFromPinCode(@RequestParam(required = true) String pinCode){
        PinCodeResponseDTO coordinates = pinCodeService.findCoordinatesFromPinCode(pinCode);
        return new ResponseEntity<>(coordinates, HttpStatus.OK);
    }

}
