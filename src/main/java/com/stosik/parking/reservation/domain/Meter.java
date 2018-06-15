package com.stosik.parking.reservation.domain;

interface Meter
{
    Reservation startReservation(Driver driver);
    
    Reservation stopReservation(Reservation reservation);
}
