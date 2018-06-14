package com.stosik.parking.domain;

import com.stosik.parking.dto.ReservationNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.Repository;

import java.util.Date;
import java.util.List;

interface ReservationRepository extends Repository<Reservation, Long>
{
    Reservation save(Reservation film);
    
    Reservation findById(Long id);
    
    List<Reservation> findWithParticularDayAndMonth(Pageable pageable, Date day);
    
    Page<Reservation> findAll(Pageable pageable);
    
    default Reservation findOneOrThrow(Long id)
    {
        Reservation film = findById(id);
        if(film == null)
        {
            throw new ReservationNotFoundException(id);
        }
        return film;
    }
}
