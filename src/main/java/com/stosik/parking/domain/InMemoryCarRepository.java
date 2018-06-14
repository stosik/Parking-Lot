package com.stosik.parking.domain;

import java.util.concurrent.ConcurrentHashMap;

import static java.util.Objects.requireNonNull;

public class InMemoryCarRepository implements CarRepository
{
    private ConcurrentHashMap<Long, Car> map = new ConcurrentHashMap<>();
    
    @Override
    public Car save(Car car)
    {
        requireNonNull(car);
        map.put(car.getPlateId(), car);
        
        return car;
    }
    
    @Override
    public Car findById(Long id)
    {
        return map.get(id);
    }
}
