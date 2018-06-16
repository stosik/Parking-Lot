package com.stosik.parking.reservation.domain;

import com.stosik.parking.reservation.domain.model.Driver;
import com.stosik.parking.reservation.domain.model.Reservation;

interface Meter
{
    Reservation startReservation(Driver driver);
    
    Reservation stopReservation(Reservation reservation);
}
