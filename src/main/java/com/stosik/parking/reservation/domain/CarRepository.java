package com.stosik.parking.reservation.domain;

import com.stosik.parking.reservation.domain.model.Car;
import org.springframework.data.repository.Repository;

interface CarRepository extends Repository<Car, Long>
{
    Car save(Car reservation);
    
    Car findById(Long id);
}
