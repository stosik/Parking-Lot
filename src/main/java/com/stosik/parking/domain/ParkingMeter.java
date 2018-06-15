package com.stosik.parking.domain;

import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class ParkingMeter implements Meter
{
    public Reservation startReservation(Driver driver)
    {
        return Reservation
            .builder()
            .startTime(new Date())
            .driver(driver)
            .build();
    }
    
    public Reservation stopReservation(Reservation reservation)
    {
        reservation.setStopTime(new Date());
        
        return reservation;
    }
}
