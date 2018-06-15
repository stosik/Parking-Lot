package com.stosik.parking.domain;

import com.stosik.parking.domain.evaluator.Evaluator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Collections;
import java.util.Set;

@Configuration
class ReservationConfiguration
{
    
    @Bean
    ReservationFacade reservationFacade(ReservationRepository filmRepository, CarRepository carRepository, Set<Evaluator> evaluators, Meter parkingMeter)
    {
        return new ReservationFacade(filmRepository, carRepository, Collections.unmodifiableSet(evaluators), parkingMeter);
    }
}
