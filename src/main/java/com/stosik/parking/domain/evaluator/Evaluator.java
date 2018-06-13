package com.stosik.parking.domain.evaluator;

import com.stosik.parking.domain.Reservation;

import java.util.Date;

public interface Evaluator
{
    Double calculateReservationCost(Reservation reservation);
    
    default int hoursDifference(Date startTime, Date stopTime)
    {
        final int MILLI_TO_HOUR = 1000 * 60 * 60;
        return (int) (stopTime.getTime() - startTime.getTime()) / MILLI_TO_HOUR;
    }
}
