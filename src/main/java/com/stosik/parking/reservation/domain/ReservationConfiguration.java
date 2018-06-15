package com.stosik.parking.reservation.domain;

import com.stosik.parking.reservation.domain.evaluator.Evaluator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Collections;
import java.util.Set;

@Configuration
class ReservationConfiguration
{
    @Bean
    ReservationFacade reservationFacade(ReservationRepository reservationRepository, CarRepository carRepository, Set<Evaluator> evaluators, Meter parkingMeter)
    {
        return new ReservationFacade(reservationRepository, carRepository, Collections.unmodifiableSet(evaluators), parkingMeter);
    }
}
