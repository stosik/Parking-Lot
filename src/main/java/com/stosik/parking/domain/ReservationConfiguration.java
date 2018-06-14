package com.stosik.parking.domain;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class ReservationConfiguration
{
    ReservationFacade reservationFacade()
    {
        return reservationFacade(new InMemoryReservationRepository(), new InMemoryCarRepository());
    }
    
    @Bean
    private ReservationFacade reservationFacade(ReservationRepository filmRepository, CarRepository carRepository)
    {
        return new ReservationFacade(filmRepository, carRepository);
    }
}
