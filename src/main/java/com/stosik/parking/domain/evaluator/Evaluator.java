package com.stosik.parking.domain.evaluator;

import com.stosik.parking.domain.DriverType;
import com.stosik.parking.domain.Reservation;

public interface Evaluator
{
    boolean isAppropriateFor(DriverType driverType);
    
    double calculateReservationCost(Reservation reservation);
}
