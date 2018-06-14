package com.stosik.parking.domain;

import org.springframework.data.repository.Repository;

public interface CarRepository extends Repository<Car, Long>
{
    Car save(Car reservation);
    
    Car findById(Long id);
    
}
