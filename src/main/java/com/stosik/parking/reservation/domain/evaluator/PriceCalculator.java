package com.stosik.parking.reservation.domain.evaluator;

import com.stosik.parking.reservation.domain.model.DriverType;
import com.stosik.parking.reservation.domain.model.Reservation;

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
