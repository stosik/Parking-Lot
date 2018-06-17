package com.stosik.parking.reservation.domain.evaluator;

import com.stosik.parking.reservation.domain.model.Reservation;
import com.stosik.util.DateUtils;

import java.math.BigDecimal;

class RegularEvaluator implements RateEvaluatorStrategy
{
    private static final Double FIRST_HOUR_COST = 1.0;
    private static final Double SECOND_HOUR_COST = 2.0;
    private static final Double CONVERSION_RATE = 1.5;
    
    @Override
    public BigDecimal calculateReservationCost(Reservation reservation)
    {
        int hours = DateUtils.hoursDifference(reservation.getStartTime(), reservation.getStopTime()) + 1;
        
        switch(hours)
        {
            case 1:
                return BigDecimal.valueOf(FIRST_HOUR_COST);
            case 2:
                return BigDecimal.valueOf(FIRST_HOUR_COST + SECOND_HOUR_COST);
            default:
                return BigDecimal.valueOf(FIRST_HOUR_COST +
                                          SECOND_HOUR_COST +
                                          countEachNextHour(SECOND_HOUR_COST, 2, hours));
        }
    }
    
    private double countEachNextHour(double result, int actualHour, int reservationHours)
    {
        return actualHour == reservationHours ? result : countEachNextHour(CONVERSION_RATE * result, ++actualHour, reservationHours);
    }
}
