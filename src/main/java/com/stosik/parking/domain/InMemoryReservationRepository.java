package com.stosik.parking.domain;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;

import static java.util.Objects.requireNonNull;

public class InMemoryReservationRepository implements ReservationRepository
{
    private ConcurrentHashMap<Long, Reservation> map = new ConcurrentHashMap<>();
    
    @Override
    public Reservation save(Reservation reservation)
    {
        requireNonNull(reservation);
        map.put(reservation.getId(), reservation);
        return reservation;
    }
    
    @Override
    public Reservation findById(Long id)
    {
        return map.get(id);
    }
    
    @Override
    public Page<Reservation> findAll(Pageable pageable)
    {
        return new PageImpl<>(new ArrayList<>(map.values()), pageable, map.size());
    }
}
