package com.stosik.parking.reservation.domain;

import com.stosik.parking.reservation.domain.model.Reservation;
import com.stosik.parking.reservation.dto.CreateReservationCommand;
import org.springframework.stereotype.Service;

import java.time.Clock;
import java.time.LocalDateTime;

@Service
class ParkingMeter implements Meter
{
    public Reservation startReservation(CreateReservationCommand driver)
    {
        return Reservation
            .builder()
            .startTime(LocalDateTime.now(Clock.systemDefaultZone()))
            .car(driver.getCar())
            .driverType(driver.getDriverType())
            .build();
    }
    
    public Reservation stopReservation(Reservation reservation)
    {
        reservation.setStopTime(LocalDateTime.now(Clock.systemDefaultZone()));
        
        return reservation;
    }
}
