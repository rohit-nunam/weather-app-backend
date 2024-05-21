package com.weatherapp.repository;

import com.weatherapp.entity.PinCodeInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PinCodeInfoRepository extends JpaRepository<PinCodeInfo, Long> {
}
