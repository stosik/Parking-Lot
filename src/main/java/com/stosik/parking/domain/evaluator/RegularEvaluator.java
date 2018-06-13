package com.stosik.parking.domain.evaluator;

import com.stosik.parking.domain.Reservation;

public class RegularEvaluator implements Evaluator
{
    private static final Double FIRST_HOUR_COST = 1.0;
    private static final Double SECOND_HOUR_COST = 2.0;
    private static final Double CONVERSION_RATE = 1.5;
    
    @Override
    public Double calculateReservationCost(Reservation reservation)
    {
        return 0.0;
    }
}
