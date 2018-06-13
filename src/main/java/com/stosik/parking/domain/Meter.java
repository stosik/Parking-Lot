package com.stosik.parking.domain;

public interface Meter
{
    void startReservation(Reservation reservation);
    
    void stopReservation(Reservation reservation);
}
