package com.stosik.parking.domain;

import com.stosik.parking.domain.evaluator.Evaluator;
import com.stosik.parking.domain.evaluator.RegularEvaluator;
import com.stosik.parking.domain.evaluator.VipEvaluator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Configuration
class ReservationConfiguration
{
    @Bean
    ReservationFacade reservationFacade(ReservationRepository filmRepository, CarRepository carRepository, Meter parkingMeter)
    {
        Set<Evaluator> evaluators = new HashSet<>(Arrays.asList(new VipEvaluator(), new RegularEvaluator()));
        
        return new ReservationFacade(filmRepository, carRepository, Collections.unmodifiableSet(evaluators), parkingMeter);
    }
}
