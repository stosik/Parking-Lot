package com.stosik.parking.domain.evaluator;

import com.stosik.parking.domain.Reservation;

public class PriceDefiner
{
    private Evaluator evaluator;
    
    public PriceDefiner(Evaluator evaluator)
    {
        this.evaluator = evaluator;
    }
    
    public double evaluateTimeToRate(Reservation reservation)
    {
        return evaluator.calculateReservationCost(reservation);
    }
}
