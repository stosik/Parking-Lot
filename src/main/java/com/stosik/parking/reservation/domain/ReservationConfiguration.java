package com.stosik.parking.reservation.domain;

import com.stosik.parking.reservation.domain.evaluator.PriceCalculator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Collections;

@Configuration
class ReservationConfiguration
{
    @Bean
    ReservationFacade reservationFacade(ReservationRepository reservationRepository, CarRepository carRepository, Meter parkingMeter)
    {
        PriceCalculator priceCalculator = new PriceCalculator();
        
        return new ReservationFacade(reservationRepository, carRepository, priceCalculator, parkingMeter);
    }
}
