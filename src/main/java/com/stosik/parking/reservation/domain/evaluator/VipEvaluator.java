package com.stosik.parking.reservation.domain.evaluator;

import com.stosik.parking.reservation.domain.model.Reservation;
import com.stosik.util.DateUtils;

import java.math.BigDecimal;

public class VipEvaluator implements RateEvaluatorStrategy
{
    private static final BigDecimal FIRST_HOUR_COST = BigDecimal.valueOf(0.0);
    private static final BigDecimal SECOND_HOUR_COST = BigDecimal.valueOf(2.0);
    private static final BigDecimal CONVERSION_RATE = BigDecimal.valueOf(1.2);
    private static final int ALREADY_STARTED_HOUR = 1;
    
    @Override
    public BigDecimal calculateReservationCost(Reservation reservation)
    {
        int hours = DateUtils.hoursDifference(reservation.getStartTime(), reservation.getStopTime()) + ALREADY_STARTED_HOUR;
        
        switch(hours)
        {
            case 1:
                return FIRST_HOUR_COST;
            case 2:
                return FIRST_HOUR_COST.add(SECOND_HOUR_COST);
            default:
                return FIRST_HOUR_COST.add(SECOND_HOUR_COST).add(countEachNextHourCost(SECOND_HOUR_COST, 2, hours));
        }
    }
    
    private BigDecimal countEachNextHourCost(BigDecimal result, int hour, int reservationHours)
    {
        return hour == reservationHours ? result : countEachNextHourCost(CONVERSION_RATE.multiply(result), ++hour, reservationHours);
    }
}
