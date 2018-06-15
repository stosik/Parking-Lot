package com.stosik.parking.reservation.domain.evaluator;

import com.stosik.parking.reservation.domain.DriverType;
import com.stosik.parking.reservation.domain.Reservation;

public interface Evaluator
{
    boolean isAppropriateFor(DriverType driverType);
    
    double calculateReservationCost(Reservation reservation);
}
