package com.stosik.parking.domain;

public interface Meter
{
    Reservation startReservation(Reservation reservation);
    
    Reservation stopReservation(Reservation reservation);
}
