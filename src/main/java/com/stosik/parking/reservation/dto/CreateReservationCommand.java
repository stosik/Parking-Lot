package com.stosik.parking.reservation.dto;

import com.stosik.parking.reservation.domain.model.DriverType;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

@Data
@Builder
public class CreateReservationCommand
{
    @NonNull
    String carLicenseId;
    
    @NonNull
    DriverType driverType;
}
