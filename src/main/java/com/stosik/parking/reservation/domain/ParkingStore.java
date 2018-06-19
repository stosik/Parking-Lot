package com.stosik.parking.reservation.domain;

import com.stosik.parking.reservation.domain.model.Reservation;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Repository
public class ParkingStore
{
    private ConcurrentHashMap<String, Reservation> parkingStore = new ConcurrentHashMap<>();
    
    public Reservation save(Reservation reservation)
    {
        parkingStore.put(reservation.getCarLicenseId(), reservation);
        return reservation;
    }
    
    public Optional<Reservation> findById(String title)
    {
        return Optional
            .of(parkingStore.get(title));
    }
    
    public List<Reservation> findAll()
    {
        return parkingStore
            .entrySet()
            .stream()
            .map(Map.Entry::getValue)
            .collect(Collectors.toList());
    }
}
