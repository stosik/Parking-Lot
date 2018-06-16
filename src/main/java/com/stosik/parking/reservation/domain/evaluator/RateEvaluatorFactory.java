package com.stosik.parking.reservation.domain.evaluator;

import com.stosik.parking.reservation.domain.DriverType;

import java.util.HashMap;
import java.util.Map;

class RateEvaluatorFactory
{
    private static final Map<DriverType, RateEvaluatorStrategy> cases = new HashMap<>();
    
    static
    {
        cases.put(DriverType.REGULAR, new RegularEvaluator());
        cases.put(DriverType.VIP, new VipEvaluator());
    }
    
    public RateEvaluatorStrategy retrieveAppropriateStrategy(DriverType driverType)
    {
        return cases.get(driverType);
    }
}
