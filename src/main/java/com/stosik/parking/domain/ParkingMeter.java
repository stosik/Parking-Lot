package com.stosik.parking.domain;

import java.util.Date;

public class ParkingMeter implements Meter
{
    ReservationRepository reservationRepository;
    
    public Reservation startReservation(Reservation reservation)
    {
        reservation.setId(1L);
        reservation.setStartTime(new Date());
    
        return reservation;
    }
    
    public Reservation stopReservation(Reservation reservation)
    {
        reservation.setId(1L);
        reservation.setStopTime(new Date());
        
        return reservation;
    }
}
