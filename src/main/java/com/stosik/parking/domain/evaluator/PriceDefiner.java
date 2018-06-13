package com.stosik.parking.domain.evaluator;

import com.stosik.parking.domain.Reservation;

public class PriceDefiner
{
    private Evaluator evaluator;
    
    public Double evaluateTimeToRate(Reservation reservation)
    {
        return evaluator.calculateReservationCost(reservation);
    }
}
