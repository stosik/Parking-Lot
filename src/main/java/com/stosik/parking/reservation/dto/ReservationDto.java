package com.stosik.parking.reservation.dto;

import com.stosik.parking.reservation.domain.model.DriverType;
import lombok.Builder;
import lombok.Value;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Value
@Builder
public class ReservationDto
{
    private Long id;
    
    private DriverType driverType;

    private LocalDateTime startTime;
    
    private LocalDateTime stopTime;
    
    private String carLicenseId;
    
    private BigDecimal cost;
}
