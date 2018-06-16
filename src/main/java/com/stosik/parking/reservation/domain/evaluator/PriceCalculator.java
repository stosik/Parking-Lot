package com.stosik.parking.reservation.domain.evaluator;

import com.stosik.parking.reservation.domain.DriverType;
import com.stosik.parking.reservation.domain.Reservation;

public class PriceCalculator
{
    private final RateEvaluatorFactory rateEvaluatorFactory = new RateEvaluatorFactory();
    
    public double calculatePrice(Reservation reservation)
    {
        DriverType driverType = reservation.getDriver().getType();
        RateEvaluatorStrategy rateEvaluatorStrategy = rateEvaluatorFactory.retrieveAppropriateStrategy(driverType);
        
        return rateEvaluatorStrategy.calculateReservationCost(reservation);
    }
}
