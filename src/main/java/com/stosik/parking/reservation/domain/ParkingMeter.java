package com.stosik.parking.reservation.domain;

import com.stosik.parking.reservation.domain.model.Driver;
import com.stosik.parking.reservation.domain.model.Reservation;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;

@Service
class ParkingMeter implements Meter
{
    public Reservation startReservation(Driver driver)
    {
        return Reservation
            .builder()
            .startTime(LocalDateTime.now())
            .driver(driver)
            .build();
    }
    
    public Reservation stopReservation(Reservation reservation)
    {
        reservation.setStopTime(LocalDateTime.now());
        
        return reservation;
    }
}
