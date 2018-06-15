package com.stosik.parking.reservation.domain;

import java.util.concurrent.ConcurrentHashMap;

import static java.util.Objects.requireNonNull;

class InMemoryCarRepository implements CarRepository
{
    private ConcurrentHashMap<Long, Car> map = new ConcurrentHashMap<>();
    
    @Override
    public Car save(Car car)
    {
        requireNonNull(car);
        map.put(car.getId(), car);
        
        return car;
    }
    
    @Override
    public Car findById(Long id)
    {
        return map.get(id);
    }
}
