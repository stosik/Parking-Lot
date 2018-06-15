package com.stosik.parking.reservation.domain;

import org.springframework.data.repository.Repository;

interface CarRepository extends Repository<Car, Long>
{
    Car save(Car reservation);
    
    Car findById(Long id);
}
