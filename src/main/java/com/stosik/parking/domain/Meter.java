package com.stosik.parking.domain;

public interface Meter
{
    Reservation startReservation(Driver driver);
    
    Reservation stopReservation(Reservation reservation);
}
