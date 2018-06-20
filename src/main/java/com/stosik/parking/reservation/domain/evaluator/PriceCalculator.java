package com.stosik.parking.reservation.domain.evaluator;

import com.stosik.parking.reservation.domain.model.DriverType;
import com.stosik.parking.reservation.domain.model.Reservation;

import java.math.BigDecimal;

public class PriceCalculator
{
    private final RateEvaluatorFactory rateEvaluatorFactory = new RateEvaluatorFactory();
    
    public BigDecimal calculatePrice(Reservation reservation)
    {
        DriverType driverType = reservation.getDriverType();
        RateEvaluatorStrategy rateEvaluatorStrategy = rateEvaluatorFactory.retrieveAppropriateStrategy(driverType);
        
        return rateEvaluatorStrategy.calculateReservationCost(reservation);
    }
}
