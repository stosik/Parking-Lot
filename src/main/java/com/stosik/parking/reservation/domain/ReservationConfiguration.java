package com.stosik.parking.reservation.domain;

import com.stosik.parking.reservation.domain.evaluator.PriceCalculator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class ReservationConfiguration
{
    @Bean
    ReservationFacade reservationFacade(ReservationRepository reservationRepository, ParkingStore parkingStore, Meter parkingMeter)
    {
        PriceCalculator priceCalculator = new PriceCalculator();
        ReservationDtoCreator reservationDtoCreator = new ReservationDtoCreator();
        
        return new ReservationFacade(reservationRepository, parkingStore, priceCalculator, parkingMeter, reservationDtoCreator);
    }
}
