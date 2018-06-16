package com.stosik.parking.reservation.domain;

import com.stosik.parking.reservation.domain.model.Reservation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

import static java.util.Objects.requireNonNull;

class InMemoryReservationRepository implements ReservationRepository
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
    public List<Reservation> findByDate(Pageable pageable, int year, int month, int day)
    {
        return this
            .findAll(pageable)
            .getContent()
            .stream()
            .filter(this::hasEnded)
            .collect(Collectors.toList());
    }
    
    @Override
    public Page<Reservation> findAll(Pageable pageable)
    {
        return new PageImpl<>(new ArrayList<>(map.values()), pageable, map.size());
    }
    
    private boolean hasEnded(Reservation reservation)
    {
        return reservation.getStartTime() != null && reservation.getStopTime() != null;
    }
}
