package com.stosik.parking.reservation.domain;

import com.stosik.parking.reservation.domain.model.Reservation;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Repository
class ParkingStore
{
    private ConcurrentHashMap<String, Reservation> store = new ConcurrentHashMap<>();
    
    public Reservation save(Reservation reservation)
    {
        store.put(reservation.getCarLicenseId(), reservation);
        return reservation;
    }
    
    public Optional<Reservation> findById(String title)
    {
        return Optional
            .of(store.get(title));
    }
    
    public Reservation delete(Reservation reservation)
    {
        return store.remove(reservation.getCarLicenseId());
    }
    
    public List<Reservation> findAll()
    {
        return store
            .entrySet()
            .stream()
            .map(Map.Entry::getValue)
            .collect(Collectors.toList());
    }
}
