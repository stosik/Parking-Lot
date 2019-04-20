package com.stosik.parking.reservation.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
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
    
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime startTime;
    
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime stopTime;
    
    private String carLicenseId;
    
    private BigDecimal cost;
}
