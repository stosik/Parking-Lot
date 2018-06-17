package com.stosik.parking.reservation.domain;

import com.stosik.parking.reservation.domain.model.Reservation;
import com.stosik.parking.reservation.dto.CreateReservationCommand;

interface Meter
{
    Reservation startReservation(CreateReservationCommand createReservationCommand);
    
    Reservation stopReservation(Reservation reservation);
}
